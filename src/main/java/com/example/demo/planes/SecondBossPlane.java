package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.RandomMovingBossProjectile;

public class SecondBossPlane extends BossPlane {

    private static final String IMAGE_NAME = "secondbossplane.png";
    private static final int IMAGE_HEIGHT = 70;
    private static final double SHIELD_OFFSET_X = 40.0;
    private static final double SHIELD_OFFSET_Y = -40.0;
    private static final double HEALTH_BAR_OFFSET_X = 35.0;
    private static final double HEALTH_BAR_OFFSET_Y = -40.0;

    private final AudioPlayer bossFireAudioPlayer;

    public SecondBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
        bossFireAudioPlayer = AudioPlayer.createAudioPlayer();
        bossFireAudioPlayer.loadAudio("/com/example/demo/audio/bossfire.wav");
    }

    @Override
    public DestructibleEntity fireProjectile() {
        if (bossFiresInCurrentFrame()) {
            bossFireAudioPlayer.play();
            return new RandomMovingBossProjectile(getProjectileInitialPosition());
        }
        return null;
    }

    @Override
    protected void updateUIElements() {
        double x = getLayoutX() + getTranslateX();
        double y = getLayoutY() + getTranslateY();
        levelView.setShieldPosition(x + SHIELD_OFFSET_X, y + SHIELD_OFFSET_Y);
        levelView.setHealthBarPosition(x + HEALTH_BAR_OFFSET_X, y + HEALTH_BAR_OFFSET_Y);
    }
}
