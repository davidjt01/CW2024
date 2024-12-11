package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.levelviews.LevelView;
import com.example.demo.planes.BossPlane;

/**
 * The {@code LevelTwo} class represents the second level of the game.
 * It extends {@link LevelParent} and includes a boss fight with the boss plane.
 */
public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final BossPlane bossPlane;
    private BossLevelView levelView;

    /**
     * Constructs a {@code LevelTwo} instance with the specified parameters.
     *
     * @param gameController the game controller managing the level.
     * @param screenHeight the height of the screen.
     * @param screenWidth the width of the screen.
     */
    public LevelTwo(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossPlane = new BossPlane(levelView);
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
     * Advances to the next level if the boss plane is destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (bossPlane.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    /**
     * Spawns enemy units in the level.
     * Adds the boss plane if no enemies are present.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossPlane);
        }
    }

    /**
     * Instantiates the level view for the second level.
     *
     * @return the instantiated level view.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = BossLevelView.createBossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
