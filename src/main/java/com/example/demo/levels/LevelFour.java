package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.levelviews.LevelView;
import com.example.demo.planes.EnemyPlane;
import com.example.demo.planes.SecondBossPlane;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFive";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 5;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private final SecondBossPlane secondBossPlane;
    private BossLevelView levelView;

    public LevelFour(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        secondBossPlane = new SecondBossPlane(levelView);
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (secondBossPlane.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(secondBossPlane);
        }

        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                DestructibleEntity newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                if (canSpawnEnemy()) {
                    addEnemyUnit(newEnemy);
                }
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new BossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
