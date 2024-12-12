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

/**
 * The {@code LevelParent} class serves as the base class for all game levels.
 * It manages the game flow, including initializing units, handling collisions, and updating the game state.
 */
public abstract class LevelParent extends Observable implements Controller {
    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    private static final long MINIMUM_SPAWN_DELAY = 500; // minimum delay in milliseconds
    private static final long FIRE_COOLDOWN = 200; // cooldown period in milliseconds
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
    private long lastFireTime = 0;

    /**
     * Constructs a {@code LevelParent} with the specified parameters.
     *
     * @param gameController the game controller managing the level.
     * @param backgroundImageName the name of the background image file.
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     * @param playerInitialHealth the initial health of the player.
     */
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

    /**
     * Checks if an enemy can be spawned based on the minimum spawn delay.
     *
     * @return {@code true} if an enemy can be spawned, {@code false} otherwise.
     */
    protected boolean canSpawnEnemy() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime >= MINIMUM_SPAWN_DELAY) {
            lastSpawnTime = currentTime;
            return true;
        }
        return false;
    }

    /**
     * Initializes the friendly units in the level.
     * This method should be implemented by subclasses to define how friendly units are initialized.
     */
    protected abstract void initializeFriendlyUnits();

    /**
     * Checks if the game is over.
     * This method should be implemented by subclasses to define the game over conditions.
     */
    protected abstract void checkIfGameOver();

    /**
     * Spawns enemy units in the level.
     * This method should be implemented by subclasses to define how enemy units are spawned.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Instantiates the level view.
     * This method should be implemented by subclasses to define the level view.
     *
     * @return the instantiated level view.
     */
    protected abstract LevelView instantiateLevelView();

    /**
     * Initializes the scene for the level.
     *
     * @return the initialized scene.
     */
    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    /**
     * Starts the game by requesting focus for the background and playing the timeline.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    /**
     * Transitions to the next level.
     *
     * @param levelName the name of the next level.
     */
    public void goToNextLevel(String levelName) {
        timeline.stop();
        setChanged();
        notifyObservers(levelName);
    }

    /**
     * Updates the scene by spawning enemies, updating actors, handling collisions, and checking game over conditions.
     */
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

    /**
     * Initializes the timeline for the game loop.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Initializes the background for the level.
     */
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

    /**
     * Fires a projectile from the user's plane if the fire cooldown period has elapsed.
     */
    private void fireProjectile() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFireTime >= FIRE_COOLDOWN) {
            DestructibleEntity projectile = user.fireProjectile();
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
            lastFireTime = currentTime;
        }
    }

    /**
     * Generates enemy fire by spawning projectiles from enemy planes.
     */
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

    /**
     * Updates the state of all actors in the level.
     */
    private void updateActors() {
        friendlyUnits.forEach(DestructibleEntity::updateActor);
        enemyUnits.forEach(DestructibleEntity::updateActor);
        userProjectiles.forEach(DestructibleEntity::updateActor);
        enemyProjectiles.forEach(DestructibleEntity::updateActor);
    }

    /**
     * Removes all destroyed actors from the level.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from the specified list.
     *
     * @param actors the list of actors to remove destroyed actors from.
     */
    private void removeDestroyedActors(List<DestructibleEntity> actors) {
        List<DestructibleEntity> destroyedActors = actors.stream().filter(DestructibleEntity::isDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between friendly and enemy planes.
     */
    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     */
    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles collisions between two lists of destructible entities.
     *
     * @param actors1 the first list of destructible entities.
     * @param actors2 the second list of destructible entities.
     */
    private void handleCollisions(List<DestructibleEntity> actors1, List<DestructibleEntity> actors2) {
        for (DestructibleEntity actor : actors2) {
            for (DestructibleEntity otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles the event when an enemy penetrates the user's defenses.
     */
    private void handleEnemyPenetration() {
        for (DestructibleEntity enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
                penetratedEnemiesCount++;
            }
        }
    }

    /**
     * Updates the level view to reflect the current state of the game.
     */
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    /**
     * Updates the kill count for the user.
     */
    private void updateKillCount() {
        int destroyedEnemies = currentNumberOfEnemies - enemyUnits.size();
        int adjustedKillCount = destroyedEnemies - penetratedEnemiesCount;
        for (int i = 0; i < adjustedKillCount; i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks if an enemy has penetrated the user's defenses.
     *
     * @param enemy the enemy to check.
     * @return {@code true} if the enemy has penetrated the defenses, {@code false} otherwise.
     */
    private boolean enemyHasPenetratedDefenses(DestructibleEntity enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * Ends the game with a win.
     */
    protected void winGame() {
        timeline.stop();
        WinMenu winMenu = new WinMenu((Stage) scene.getWindow(), this);
        winMenu.show();
    }

    /**
     * Ends the game with a loss.
     */
    protected void loseGame() {
        timeline.stop();
        GameOverMenu gameOverMenu = new GameOverMenu((Stage) scene.getWindow(), this);
        gameOverMenu.setLevelName(this.getClass().getName());
        gameOverMenu.show();
    }

    /**
     * Gets the user's plane.
     *
     * @return the user's plane.
     */
    protected UserPlane getUser() {
        return user;
    }

    /**
     * Gets the root group of the scene.
     *
     * @return the root group.
     */
    protected Group getRoot() {
        return root;
    }

    /**
     * Gets the current number of enemies.
     *
     * @return the current number of enemies.
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds an enemy unit to the level.
     *
     * @param enemy the enemy unit to add.
     */
    protected void addEnemyUnit(DestructibleEntity enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Gets the maximum Y position for enemies.
     *
     * @return the maximum Y position for enemies.
     */
    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    /**
     * Gets the width of the screen.
     *
     * @return the screen width.
     */
    protected double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Checks if the user's plane is destroyed.
     *
     * @return {@code true} if the user's plane is destroyed, {@code false} otherwise.
     */
    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    /**
     * Updates the number of enemies in the level.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        timeline.pause();
        WritableImage snapshot = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
        scene.snapshot(snapshot);
        PauseMenu pauseMenu = new PauseMenu((Stage) scene.getWindow(), this);
        pauseMenu.saveGameScene(scene);
        pauseMenu.setBackgroundImage(snapshot);
        pauseMenu.show();
    }

    /**
     * Resumes the game.
     */
    public void resumeGame() {
        timeline.play();
    }

    /**
     * Checks if a projectile is out of the screen.
     *
     * @param projectile the projectile to check.
     * @return {@code true} if the projectile is out of the screen, {@code false} otherwise.
     */
    private boolean isOutOfScreen(DestructibleEntity projectile) {
        double x = projectile.getLayoutX() + projectile.getTranslateX();
        double y = projectile.getLayoutY() + projectile.getTranslateY();
        return x < 0 || x > screenWidth || y < 0 || y > screenHeight;
    }

    /**
     * Removes projectiles that are out of the screen.
     *
     * @param projectiles the list of projectiles to check.
     */
    private void removeOutOfScreenProjectiles(List<DestructibleEntity> projectiles) {
        List<DestructibleEntity> outOfScreenProjectiles = projectiles.stream()
                .filter(this::isOutOfScreen)
                .toList();
        root.getChildren().removeAll(outOfScreenProjectiles);
        projectiles.removeAll(outOfScreenProjectiles);
    }

    /**
     * Handles the event when a level is selected.
     *
     * @param level the name of the selected level.
     */
    @Override
    public void onLevelSelected(String level) {
        timeline.stop();
        gameController.onLevelSelected(level);
    }

    /**
     * Handles the event when the main menu is selected.
     */
    @Override
    public void onMainMenuSelected() {
        System.out.println("on HOME pressed");
        timeline.stop();
        gameController.onMainMenuSelected();
    }

    /**
     * Handles the event when the level menu is selected.
     */
    @Override
    public void onLevelMenuSelected() {
        System.out.println("on HOME pressed");
        timeline.stop();
        gameController.onLevelMenuSelected();
    }

    /**
     * Handles the event when the continue option is selected.
     */
    @Override
    public void onContinueSelected() {
        resumeGame();
    }

    /**
     * Handles the event when the settings menu is selected.
     */
    @Override
    public void onSettingsMenuSelected() {
        timeline.stop();
        gameController.onSettingsMenuSelected();
    }
}
