package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.AngledBossProjectile;

import java.util.ArrayList;
import java.util.List;

public class FinalBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "finalbossplane.png";
    private static final int IMAGE_HEIGHT = 89;
    private static final double SHIELD_OFFSET_X = 40.0;
    private static final double SHIELD_OFFSET_Y = -30.0;
    private static final double HEALTH_BAR_OFFSET_X = 35.0;
    private static final double HEALTH_BAR_OFFSET_Y = -40.0;

    private final AudioPlayer bossFireAudioPlayer;

    public FinalBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
        bossFireAudioPlayer = new AudioPlayer();
        bossFireAudioPlayer.loadAudio("/com/example/demo/audio/bossfire.wav");
    }

    @Override
    public List<DestructibleEntity> fireProjectiles() {
        List<DestructibleEntity> projectiles = new ArrayList<>();
        if (bossFiresInCurrentFrame()) {
            bossFireAudioPlayer.play();
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), -20));
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), 0));
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), 20));
        }
        return projectiles;
    }

    @Override
    protected void updateUIElements() {
        double x = getLayoutX() + getTranslateX();
        double y = getLayoutY() + getTranslateY();
        levelView.setShieldPosition(x + SHIELD_OFFSET_X, y + SHIELD_OFFSET_Y);
        levelView.setHealthBarPosition(x + HEALTH_BAR_OFFSET_X, y + HEALTH_BAR_OFFSET_Y);
    }
}
