package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.LevelView;
import com.example.demo.planes.AdvancedEnemyPlane;
import com.example.demo.planes.EnemyPlane;

/**
 * The {@code LevelThree} class represents the third level of the game.
 * It extends {@link LevelParent} and includes a mix of basic and advanced enemy planes.
 */
public class LevelThree extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFour";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 15;
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private int enemyCounter = 0; // Counter to alternate between enemy types

    /**
     * Constructs a {@code LevelThree} instance with the specified parameters.
     *
     * @param gameController the game controller managing the level.
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     */
    public LevelThree(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the game is over.
     * Ends the game with a loss if the user's plane is destroyed.
     * Advances to the next level if the user has reached the kill target.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
        }
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
     * Spawns enemy units in the level.
     * Alternates between basic and advanced enemy planes based on the enemy counter.
     * Spawns additional enemies based on the spawn probability.
     */
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
                if (canSpawnEnemy()) {
                    addEnemyUnit(newEnemy);
                }
                enemyCounter++;
            }
        }
    }

    /**
     * Instantiates the level view for the third level.
     *
     * @return the instantiated level view.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return LevelView.createLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the user has reached the kill target to advance to the next level.
     *
     * @return {@code true} if the user has reached the kill target, {@code false} otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
