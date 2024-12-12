package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.RandomMovingBossProjectile;

/**
 * The {@code SecondBossPlane} class represents the second boss plane in the game.
 * It extends {@link BossPlane} and provides additional functionalities such as firing random moving projectiles.
 */
public class SecondBossPlane extends BossPlane {
    private static final String IMAGE_NAME = "secondbossplane.png";
    private static final int IMAGE_HEIGHT = 70;
    private static final double SHIELD_OFFSET_X = 40.0;
    private static final double SHIELD_OFFSET_Y = -40.0;
    private static final double HEALTH_BAR_OFFSET_X = 35.0;
    private static final double HEALTH_BAR_OFFSET_Y = -40.0;
    private final AudioPlayer bossFireAudioPlayer;

    /**
     * Constructs a {@code SecondBossPlane} with the specified level view.
     *
     * @param levelView the level view associated with the second boss plane.
     */
    public SecondBossPlane(BossLevelView levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, levelView);
        bossFireAudioPlayer = AudioPlayer.createAudioPlayer();
        bossFireAudioPlayer.loadAudio("/com/example/demo/audio/bossfire.wav");
    }

    /**
     * Fires a random moving projectile from the second boss plane.
     * Plays the firing audio and returns the fired projectile.
     *
     * @return the fired projectile, or {@code null} if the plane does not fire in the current frame.
     */
    @Override
    public DestructibleEntity fireProjectile() {
        if (bossFiresInCurrentFrame()) {
            bossFireAudioPlayer.play();
            return new RandomMovingBossProjectile(getProjectileInitialPosition());
        }
        return null;
    }

    /**
     * Updates the UI elements associated with the second boss plane.
     * This includes the shield and health bar positions.
     */
    @Override
    protected void updateUIElements() {
        double x = getLayoutX() + getTranslateX();
        double y = getLayoutY() + getTranslateY();
        levelView.setShieldPosition(x + SHIELD_OFFSET_X, y + SHIELD_OFFSET_Y);
        levelView.setHealthBarPosition(x + HEALTH_BAR_OFFSET_X, y + HEALTH_BAR_OFFSET_Y);
    }
}
