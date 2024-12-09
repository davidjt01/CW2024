package com.example.demo.planes;

import com.example.demo.levelviews.LevelViewLevelTwo;

public class SecondBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "secondbossplane.png";
    private static final int IMAGE_HEIGHT = 70;

    public SecondBossPlane(LevelViewLevelTwo levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
    }
}
