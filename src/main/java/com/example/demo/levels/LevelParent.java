package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levels.EntityManager;
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
    private final UserPlane user;
    private final Scene scene;
    private final EntityManager entityManager;
    private final LevelUI levelUI;
    private final BackgroundManager backgroundManager;
    private final GameLoopManager gameLoopManager;
    private int currentNumberOfEnemies;
    private final CollisionManager collisionManager;

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.user = new UserPlane(playerInitialHealth);
        this.entityManager = new EntityManager();
        this.backgroundManager = new BackgroundManager(backgroundImageName, screenHeight, screenWidth);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelUI = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        this.gameLoopManager = new GameLoopManager(this::updateScene);
        this.collisionManager = new CollisionManager(user);
        entityManager.addFriendlyUnit(user);
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
        entityManager.clearAll();
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
        entityManager.addUserProjectile(projectile);
    }

    private void generateEnemyFire() {
        for (DestructibleEntity enemy : entityManager.getEnemyUnits()) {
            if (enemy instanceof Plane) {
                List<DestructibleEntity> projectiles = ((Plane) enemy).fireProjectiles();
                for (DestructibleEntity projectile : projectiles) {
                    if (projectile != null) {
                        root.getChildren().add(projectile);
                        entityManager.addEnemyProjectile(projectile);
                    }
                }
            }
        }
    }

    private void updateActors() {
        entityManager.getFriendlyUnits().forEach(DestructibleEntity::updateActor);
        entityManager.getEnemyUnits().forEach(DestructibleEntity::updateActor);
        entityManager.getUserProjectiles().forEach(DestructibleEntity::updateActor);
        entityManager.getEnemyProjectiles().forEach(DestructibleEntity::updateActor);
    }

    private void removeAllDestroyedActors() {
        removeDestroyedActors(entityManager.getFriendlyUnits());
        removeDestroyedActors(entityManager.getEnemyUnits());
        removeDestroyedActors(entityManager.getUserProjectiles());
        removeDestroyedActors(entityManager.getEnemyProjectiles());
    }

    private void removeDestroyedActors(List<DestructibleEntity> actors) {
        List<DestructibleEntity> destroyedActors = actors.stream().filter(DestructibleEntity::isDestroyed).toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private void handlePlaneCollisions() {
        collisionManager.handlePlaneCollisions(entityManager.getFriendlyUnits(), entityManager.getEnemyUnits());
    }

    private void handleUserProjectileCollisions() {
        collisionManager.handleUserProjectileCollisions(entityManager.getUserProjectiles(), entityManager.getEnemyUnits());
    }

    private void handleEnemyProjectileCollisions() {
        collisionManager.handleEnemyProjectileCollisions(entityManager.getEnemyProjectiles(), entityManager.getFriendlyUnits());
    }

    private void handleEnemyPenetration() {
        collisionManager.handleEnemyPenetration(entityManager.getEnemyUnits(), screenWidth);
    }

    private void updateLevelView() {
        levelUI.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - entityManager.getEnemyUnits().size(); i++) {
            user.incrementKillCount();
        }
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
        return entityManager.getEnemyUnits().size();
    }

    protected void addEnemyUnit(DestructibleEntity enemy) {
        entityManager.addEnemyUnit(enemy);
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
        currentNumberOfEnemies = entityManager.getEnemyUnits().size();
    }

}
