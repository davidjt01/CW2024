package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.levelviews.LevelView;
import com.example.demo.planes.EnemyPlane;
import com.example.demo.planes.SecondBossPlane;

/**
 * The {@code LevelFour} class represents the fourth level of the game.
 * It extends {@link LevelParent} and includes a boss fight with the second boss plane.
 */
public class LevelFour extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFive";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 5;
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
    private final SecondBossPlane secondBossPlane;
    private BossLevelView levelView;

    /**
     * Constructs a {@code LevelFour} instance with the specified parameters.
     *
     * @param gameController the game controller managing the level.
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     */
    public LevelFour(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        secondBossPlane = new SecondBossPlane(levelView);
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
     * Advances to the next level if the second boss plane is destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (secondBossPlane.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    /**
     * Spawns enemy units in the level.
     * Adds the second boss plane if no enemies are present.
     * Spawns additional enemies based on the spawn probability.
     */
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

    /**
     * Instantiates the level view for the fourth level.
     *
     * @return the instantiated level view.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = BossLevelView.createBossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
