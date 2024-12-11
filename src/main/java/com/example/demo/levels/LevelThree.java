package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.LevelView;
import com.example.demo.planes.AdvancedEnemyPlane;
import com.example.demo.planes.EnemyPlane;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFour";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 20;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private int enemyCounter = 0; // Counter to alternate between enemy types

    public LevelThree(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                DestructibleEntity newEnemy;
                if (enemyCounter % 2 == 0) {
                    newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                } else {
                    newEnemy = new AdvancedEnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                }
                addEnemyUnit(newEnemy);
                enemyCounter++;
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

}
