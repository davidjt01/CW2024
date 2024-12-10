package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.levelviews.LevelView;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.planes.SecondBossPlane;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFive";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final SecondBossPlane secondBossPlane1;
    private final SecondBossPlane secondBossPlane2;
    private BossLevelView levelView;

    public LevelFour(Controller gameController, double screenHeight, double screenWidth) {
        super(gameController, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        secondBossPlane1 = new SecondBossPlane(levelView);
        secondBossPlane2 = new SecondBossPlane(levelView);
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
        else if (secondBossPlane1.isDestroyed() && secondBossPlane2.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(secondBossPlane1);
            addEnemyUnit(secondBossPlane2);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new BossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
