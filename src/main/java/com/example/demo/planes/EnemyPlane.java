package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.EnemyProjectile;

/**
 * The {@code EnemyPlane} class represents an enemy plane in the game.
 * It extends {@link Plane} and provides methods for updating its position,
 * firing projectiles, and handling its behavior.
 */
public class EnemyPlane extends Plane {
    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 54;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = 0.01;
    private final AudioPlayer enemyFireAudioPlayer;

    /**
     * Constructs an {@code EnemyPlane} with the specified image name, initial x and y positions.
     *
     * @param imageName the name of the image file.
     * @param initialXPos the initial x-coordinate of the enemy plane.
     * @param initialYPos the initial y-coordinate of the enemy plane.
     */
    public EnemyPlane(String imageName, double initialXPos, double initialYPos) {
        super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        enemyFireAudioPlayer = AudioPlayer.createAudioPlayer();
        enemyFireAudioPlayer.loadAudio("/com/example/demo/audio/enemyfire.wav");
    }

    /**
     * Constructs an {@code EnemyPlane} with the default image name and specified initial x and y positions.
     *
     * @param initialXPos the initial x-coordinate of the enemy plane.
     * @param initialYPos the initial y-coordinate of the enemy plane.
     */
    public EnemyPlane(double initialXPos, double initialYPos) {
        this(IMAGE_NAME, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the enemy plane.
     * Moves the plane horizontally based on its velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile from the enemy plane.
     * Plays the firing audio and returns the fired projectile.
     *
     * @return the fired projectile, or {@code null} if the plane does not fire in the current frame.
     */
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

    /**
     * Updates the state of the enemy plane.
     * This method is called in each frame to update the plane's behavior.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
