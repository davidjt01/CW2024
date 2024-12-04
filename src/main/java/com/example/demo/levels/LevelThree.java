package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelui.LevelUI;
import com.example.demo.planes.AdvancedEnemyPlane;
import com.example.demo.planes.EnemyPlane;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFour";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 20;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    private boolean spawnEnemyPlaneNext = true;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
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
                if (spawnEnemyPlaneNext) {
                    newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, getRoot());
                } else {
                    newEnemy = new AdvancedEnemyPlane(getScreenWidth(), newEnemyInitialYPosition, getRoot());
                }

                spawnEnemyPlaneNext = !spawnEnemyPlaneNext;

                addEnemyUnit(newEnemy);
            }
        }
    }

    @Override
    protected LevelUI instantiateLevelView() {
        return new LevelUI(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
