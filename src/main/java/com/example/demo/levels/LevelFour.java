package com.example.demo.levels;

import com.example.demo.levelviews.LevelView;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.planes.SecondBossPlane;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFive";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final SecondBossPlane secondBossPlane;
    private BossLevelView levelView;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
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
        }
        else if (secondBossPlane.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(secondBossPlane);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new BossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

}
