package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelui.LevelUI;
import com.example.demo.planes.Plane;
import com.example.demo.planes.UserPlane;
import javafx.scene.Group;
import javafx.scene.Scene;

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
    //private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;

    private final List<DestructibleEntity> friendlyUnits;
    private final List<DestructibleEntity> enemyUnits;
    private final List<DestructibleEntity> userProjectiles;
    private final List<DestructibleEntity> enemyProjectiles;
    private final LevelUI levelUI;
    private final BackgroundManager backgroundManager;
    private final GameLoopManager gameLoopManager;

    private int currentNumberOfEnemies;

    private final CollisionManager collisionManager;

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
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

        this.gameLoopManager = new GameLoopManager(this::updateScene);
        this.collisionManager = new CollisionManager(user);
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
        gameLoopManager.startGame();
    }

    public void goToNextLevel(String levelName) {
        gameLoopManager.stopGame();
        gameLoopManager.clearGameLoop();

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
        collisionManager.handlePlaneCollisions(friendlyUnits, enemyUnits);
    }

    private void handleUserProjectileCollisions() {
        collisionManager.handleUserProjectileCollisions(userProjectiles, enemyUnits);
    }

    private void handleEnemyProjectileCollisions() {
        collisionManager.handleEnemyProjectileCollisions(enemyProjectiles, friendlyUnits);
    }

    private void handleEnemyPenetration() {
        collisionManager.handleEnemyPenetration(enemyUnits, screenWidth);
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
        gameLoopManager.stopGame();
        levelUI.showWinImage();
    }

    protected void loseGame() {
        gameLoopManager.stopGame();
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
