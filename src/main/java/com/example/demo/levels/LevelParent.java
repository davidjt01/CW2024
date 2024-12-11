package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.LevelView;
import com.example.demo.menus.GameOverMenu;
import com.example.demo.menus.PauseMenu;
import com.example.demo.menus.WinMenu;
import com.example.demo.planes.Plane;
import com.example.demo.planes.UserPlane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public abstract class LevelParent extends Observable implements Controller {
    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    private static final long MINIMUM_SPAWN_DELAY = 500; // minimum delay in milliseconds
    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;
    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;
    private final List<DestructibleEntity> friendlyUnits;
    private final List<DestructibleEntity> enemyUnits;
    private final List<DestructibleEntity> userProjectiles;
    private final List<DestructibleEntity> enemyProjectiles;
    private final LevelView levelView;
    private final Controller gameController;
    private int currentNumberOfEnemies;
    private long lastSpawnTime = 0;
    private int penetratedEnemiesCount = 0;
    private static final long FIRE_COOLDOWN = 200; // cooldown period in milliseconds
    private long lastFireTime = 0;

    public LevelParent(Controller gameController, String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.gameController = gameController;
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    protected boolean canSpawnEnemy() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime >= MINIMUM_SPAWN_DELAY) {
            lastSpawnTime = currentTime;
            return true;
        }
        return false;
    }

    protected abstract void initializeFriendlyUnits();

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits();

    protected abstract LevelView instantiateLevelView();

    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    public void goToNextLevel(String levelName) {
        timeline.stop();
        setChanged();
        notifyObservers(levelName);
    }

    private void updateScene() {
        spawnEnemyUnits();
        updateActors();
        generateEnemyFire();
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        removeAllDestroyedActors();
        removeOutOfScreenProjectiles(userProjectiles);
        removeOutOfScreenProjectiles(enemyProjectiles);
        updateKillCount();
        updateLevelView();
        checkIfGameOver();
    }

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP || kc == KeyCode.W) user.moveUp();
            if (kc == KeyCode.DOWN || kc == KeyCode.S) user.moveDown();
            if (kc == KeyCode.LEFT || kc == KeyCode.A) user.moveLeft();
            if (kc == KeyCode.RIGHT || kc == KeyCode.D) user.moveRight();
            if (kc == KeyCode.SPACE) fireProjectile();
            if (kc == KeyCode.P || kc == KeyCode.ESCAPE) {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    pauseGame();
                } else {
                    resumeGame();
                }
            }
        });
        background.setOnKeyReleased(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP || kc == KeyCode.W || kc == KeyCode.DOWN || kc == KeyCode.S)
                user.stopVerticalVelocity();
            if (kc == KeyCode.LEFT || kc == KeyCode.A || kc == KeyCode.RIGHT || kc == KeyCode.D)
                user.stopHorizontalVelocity();
        });
        root.getChildren().add(background);
    }

    private void fireProjectile() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFireTime >= FIRE_COOLDOWN) {
            DestructibleEntity projectile = user.fireProjectile();
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
            lastFireTime = currentTime;
        }
    }

    private void generateEnemyFire() {
        for (DestructibleEntity enemy : enemyUnits) {
            if (enemy instanceof Plane) {
                List<DestructibleEntity> projectiles = ((Plane) enemy).fireProjectiles();
                for (DestructibleEntity projectile : projectiles) {
                    if (projectile != null) {
                        root.getChildren().add(projectile);
                        enemyProjectiles.add(projectile);
                    }
                }
            }
        }
    }

    private void spawnEnemyProjectile(DestructibleEntity projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    private void updateActors() {
        friendlyUnits.forEach(DestructibleEntity::updateActor);
        enemyUnits.forEach(DestructibleEntity::updateActor);
        userProjectiles.forEach(DestructibleEntity::updateActor);
        enemyProjectiles.forEach(DestructibleEntity::updateActor);
    }

    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<DestructibleEntity> actors) {
        List<DestructibleEntity> destroyedActors = actors.stream().filter(DestructibleEntity::isDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    private void handleCollisions(List<DestructibleEntity> actors1,
                                  List<DestructibleEntity> actors2) {
        for (DestructibleEntity actor : actors2) {
            for (DestructibleEntity otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    private void handleEnemyPenetration() {
        for (DestructibleEntity enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
                penetratedEnemiesCount++;
            }
        }
    }

    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        int destroyedEnemies = currentNumberOfEnemies - enemyUnits.size();
        int adjustedKillCount = destroyedEnemies - penetratedEnemiesCount;
        for (int i = 0; i < adjustedKillCount; i++) {
            user.incrementKillCount();
        }
    }

    private boolean enemyHasPenetratedDefenses(DestructibleEntity enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    protected void winGame() {
        timeline.stop();
        //levelView.showWinImage();
        WinMenu winMenu = new WinMenu((Stage) scene.getWindow(), this);
        winMenu.show();
    }

    protected void loseGame() {
        timeline.stop();
        //levelView.showGameOverImage();
        GameOverMenu gameOverMenu = new GameOverMenu((Stage) scene.getWindow(), this);
        gameOverMenu.setLevelName(this.getClass().getName());
        gameOverMenu.show();

    }

    protected UserPlane getUser() {
        return user;
    }

    protected Group getRoot() {
        return root;
    }

    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    protected void addEnemyUnit(DestructibleEntity enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    protected double getScreenWidth() {
        return screenWidth;
    }

    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    public void pauseGame() {
        timeline.pause();

        // Capture the current game scene as an image
        WritableImage snapshot = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
        scene.snapshot(snapshot);

        PauseMenu pauseMenu = new PauseMenu((Stage) scene.getWindow(), this);
        pauseMenu.saveGameScene(scene);
        pauseMenu.setBackgroundImage(snapshot); // Pass the captured image to the pause menu
        pauseMenu.show();
    }

    public void resumeGame() {
        timeline.play();
    }

    private boolean isOutOfScreen(DestructibleEntity projectile) {
        double x = projectile.getLayoutX() + projectile.getTranslateX();
        double y = projectile.getLayoutY() + projectile.getTranslateY();
        return x < 0 || x > screenWidth || y < 0 || y > screenHeight;
    }

    private void removeOutOfScreenProjectiles(List<DestructibleEntity> projectiles) {
        List<DestructibleEntity> outOfScreenProjectiles = projectiles.stream()
                .filter(this::isOutOfScreen)
                .toList();
        root.getChildren().removeAll(outOfScreenProjectiles);
        projectiles.removeAll(outOfScreenProjectiles);
    }

    @Override
    public void onLevelSelected(String level) {
        timeline.stop();
        gameController.onLevelSelected(level);
    }

    @Override
    public void onMainMenuSelected() {
        System.out.println("on HOME pressed");
        timeline.stop();
        gameController.onMainMenuSelected();
    }

    @Override
    public void onLevelMenuSelected() {
        System.out.println("on HOME pressed");
        timeline.stop();
        gameController.onLevelMenuSelected();
    }

    @Override
    public void onPauseSelected() {

    }

    @Override
    public void onContinueSelected() {
        resumeGame();
    }

    @Override
    public void onSettingsMenuSelected() {
        timeline.stop();
        gameController.onSettingsMenuSelected();
    }

}
