package com.example.demo.planes;

import com.example.demo.levelviews.BossLevelView;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.AngledBossProjectile;

import java.util.ArrayList;
import java.util.List;

public class FinalBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "finalbossplane.png";
    private static final int IMAGE_HEIGHT = 89;

    public FinalBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
    }

    @Override
    public List<DestructibleEntity> fireProjectiles() {
        List<DestructibleEntity> projectiles = new ArrayList<>();
        if (bossFiresInCurrentFrame()) {
            //fireBallAudio.play();
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), -20));
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), 0));
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), 20));
        }
        return projectiles;
    }
}
