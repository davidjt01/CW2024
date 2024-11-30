package com.example.demo.levels;

import com.example.demo.planes.BossPlane;
import com.example.demo.levelviews.LevelView;
import com.example.demo.levelviews.LevelViewBoss;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFive";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final BossPlane bossPlane1;
    private final BossPlane bossPlane2;
    private LevelViewBoss levelView;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossPlane1 = new BossPlane(levelView);
        bossPlane2 = new BossPlane(levelView);
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (bossPlane1.isDestroyed() && bossPlane2.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossPlane1);
            addEnemyUnit(bossPlane2);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewBoss(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

}
