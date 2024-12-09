package com.example.demo.planes;

import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.RandomMovingBossProjectile;
import com.example.demo.entities.DestructibleEntity;

public class SecondBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "secondbossplane.png";
    private static final int IMAGE_HEIGHT = 70;

    public SecondBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
    }

    @Override
    public DestructibleEntity fireProjectile() {
        return bossFiresInCurrentFrame() ? new RandomMovingBossProjectile(getProjectileInitialPosition()) : null;
    }
}
