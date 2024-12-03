package com.example.demo.levels;

import com.example.demo.planes.BossPlane;
import com.example.demo.levelui.LevelUI;
import com.example.demo.levelui.BossLevelUI;

public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final BossPlane bossPlane;
    private BossLevelUI levelView;

    public LevelTwo(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossPlane = new BossPlane(levelView, getRoot());
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (bossPlane.isDestroyed()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossPlane);
        }
    }

    @Override
    protected LevelUI instantiateLevelView() {
        levelView = new BossLevelUI(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

}
