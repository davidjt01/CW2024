package com.example.demo.planes;

import com.example.demo.levelviews.BossLevelView;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.TripleDirectionBossProjectile;

public class FinalBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "finalbossplane.png";
    private static final int IMAGE_HEIGHT = 89;

    public FinalBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
    }

    @Override
    public DestructibleEntity fireProjectile() {
        return bossFiresInCurrentFrame() ? new TripleDirectionBossProjectile(getProjectileInitialPosition()) : null;
    }
}
