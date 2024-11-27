package com.example.demo;

import javafx.scene.media.AudioClip;

public class EnemyPlane extends FighterPlane {

    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 54;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    private final AudioClip explosionSound;

    public EnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        explosionSound = new AudioClip(getClass()
                .getResource("/com/example/demo/audio/explosion.wav")
                .toExternalForm());
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public void destroy() {
        super.destroy();
        if (explosionSound != null) {
            explosionSound.play();
        }
    }
}
