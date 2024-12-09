package com.example.demo.levels;

import com.example.demo.levelviews.LevelView;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.planes.FinalBossPlane;

public class LevelFive extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final FinalBossPlane finalBossPlane;
    private BossLevelView levelView;

    public LevelFive(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        finalBossPlane = new FinalBossPlane(levelView);
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
        else if (finalBossPlane.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(finalBossPlane);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new BossLevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

}
