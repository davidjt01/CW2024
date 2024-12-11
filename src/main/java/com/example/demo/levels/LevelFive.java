package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.levelviews.LevelView;
import com.example.demo.planes.AdvancedEnemyPlane;
import com.example.demo.planes.EnemyPlane;
import com.example.demo.planes.FinalBossPlane;

/**
 * The {@code LevelFive} class represents the fifth level of the game.
 * It extends {@link LevelParent} and includes the final boss fight.
 */
public class LevelFive extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 5;
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
    private final FinalBossPlane finalBossPlane;
    private BossLevelView levelView;
    private int enemyCounter = 0;

    /**
     * Constructs a {@code LevelFive} instance with the specified parameters.
     *
     * @param gameController the game controller managing the level.
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     */
    public LevelFive(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        finalBossPlane = new FinalBossPlane(levelView);
    }

    /**
     * Initializes the friendly units in the level.
     * Adds the user's plane to the root group.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over.
     * Ends the game with a loss if the user's plane is destroyed.
     * Ends the game with a win if the final boss plane is destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (finalBossPlane.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Spawns enemy units in the level.
     * Adds the final boss plane if no enemies are present.
     * Spawns additional enemies based on the spawn probability.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(finalBossPlane);
        }
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
                if (canSpawnEnemy()) {
                    addEnemyUnit(newEnemy);
                }
                enemyCounter++;
            }
        }
    }

    /**
     * Instantiates the level view for the fifth level.
     *
     * @return the instantiated level view.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = BossLevelView.createBossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
