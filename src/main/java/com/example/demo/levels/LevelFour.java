package com.example.demo.levels;

import com.example.demo.planes.SecondBossPlane;
import com.example.demo.levelui.LevelUI;
import com.example.demo.levelui.SecondBossLevelUI;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFive";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final SecondBossPlane bossPlane1;
    private final SecondBossPlane bossPlane2;
    private SecondBossLevelUI levelView;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossPlane1 = new SecondBossPlane(levelView);
        bossPlane2 = new SecondBossPlane(levelView);
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
    protected LevelUI instantiateLevelView() {
        levelView = new SecondBossLevelUI(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

}
