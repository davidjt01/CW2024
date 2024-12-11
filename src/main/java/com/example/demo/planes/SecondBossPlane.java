package com.example.demo.planes;

import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.RandomMovingBossProjectile;
import com.example.demo.entities.DestructibleEntity;

public class SecondBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "secondbossplane.png";
    private static final int IMAGE_HEIGHT = 70;
    private static final double SHIELD_OFFSET_X = 40.0;
    private static final double SHIELD_OFFSET_Y = -40.0;
    private static final double HEALTH_BAR_OFFSET_X = 35.0;
    private static final double HEALTH_BAR_OFFSET_Y = -40.0;

    public SecondBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
    }

    @Override
    public DestructibleEntity fireProjectile() {
        return bossFiresInCurrentFrame() ? new RandomMovingBossProjectile(getProjectileInitialPosition()) : null;
    }

    @Override
    protected void updateUIElements() {
        double x = getLayoutX() + getTranslateX();
        double y = getLayoutY() + getTranslateY();
        levelView.setShieldPosition(x + SHIELD_OFFSET_X, y + SHIELD_OFFSET_Y);
        levelView.setHealthBarPosition(x + HEALTH_BAR_OFFSET_X, y + HEALTH_BAR_OFFSET_Y);
    }
}
