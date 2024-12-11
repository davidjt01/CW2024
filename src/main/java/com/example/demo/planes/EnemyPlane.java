package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.EnemyProjectile;

public class EnemyPlane extends Plane {

    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 54;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    private final AudioPlayer enemyFireAudioPlayer;

    public EnemyPlane(String imageName, double initialXPos, double initialYPos) {
        super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        enemyFireAudioPlayer = AudioPlayer.createAudioPlayer();
        enemyFireAudioPlayer.loadAudio("/com/example/demo/audio/enemyfire.wav");
    }

    public EnemyPlane(double initialXPos, double initialYPos) {
        this(IMAGE_NAME, initialXPos, initialYPos);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public DestructibleEntity fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            enemyFireAudioPlayer.play();
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
}
