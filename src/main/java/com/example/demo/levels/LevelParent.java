package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelui.LevelUI;
import com.example.demo.planes.Plane;
import com.example.demo.planes.UserPlane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class LevelParent extends Observable {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;
    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;

    private final List<DestructibleEntity> friendlyUnits;
    private final List<DestructibleEntity> enemyUnits;
    private final List<DestructibleEntity> userProjectiles;
    private final List<DestructibleEntity> enemyProjectiles;
    private final LevelUI levelUI;
    private final BackgroundManager backgroundManager;
    private int currentNumberOfEnemies;

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.backgroundManager = new BackgroundManager(backgroundImageName, screenHeight, screenWidth);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelUI = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    protected abstract void initializeFriendlyUnits();

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits();

    protected abstract LevelUI instantiateLevelView();

    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelUI.showHeartDisplay();
        return scene;
    }

    public void startGame() {
        backgroundManager.getBackground().requestFocus();
        timeline.play();
    }

    public void goToNextLevel(String levelName) {
        timeline.stop();
        timeline.getKeyFrames().clear();

        // Reset key event handlers
        backgroundManager.getBackground().setOnKeyPressed(null);
        backgroundManager.getBackground().setOnKeyReleased(null);

        root.getChildren().clear();

        friendlyUnits.clear();
        enemyUnits.clear();
        userProjectiles.clear();
        enemyProjectiles.clear();
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
        backgroundManager.configureKeyEvents(user, this::fireProjectile);
        root.getChildren().add(backgroundManager.getBackground());
    }

    private void fireProjectile() {
        DestructibleEntity projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
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
            }
        }
    }

    private void updateLevelView() {
        levelUI.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    private boolean enemyHasPenetratedDefenses(DestructibleEntity enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    protected void winGame() {
        timeline.stop();
        levelUI.showWinImage();
    }

    protected void loseGame() {
        timeline.stop();
        levelUI.showGameOverImage();
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

}
