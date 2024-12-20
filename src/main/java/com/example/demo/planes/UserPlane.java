package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.UserProjectile;

/**
 * The {@code UserPlane} class represents the user's plane in the game.
 * It extends {@link Plane} and provides methods for updating its position,
 * firing projectiles, and handling its behavior.
 */
public class UserPlane extends Plane {
    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = 10;
    private static final double Y_LOWER_BOUND = 660.0;
    private static final double X_LEFT_BOUND = 0.0;
    private static final double X_RIGHT_BOUND = 800.0; // to be adjusted later
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 39;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 8;
    private static final int PROJECTILE_X_POSITION_OFFSET = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private final AudioPlayer fireAudioPlayer;
    private final AudioPlayer damageAudioPlayer;
    private int verticalVelocityMultiplier;
    private int horizontalVelocityMultiplier;
    private int numberOfKills;

    /**
     * Constructs a {@code UserPlane} with the specified initial health.
     *
     * @param initialHealth the initial health of the user's plane.
     */
    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
        fireAudioPlayer = AudioPlayer.createAudioPlayer();
        fireAudioPlayer.loadAudio("/com/example/demo/audio/userfire.wav");
        damageAudioPlayer = AudioPlayer.createAudioPlayer();
        damageAudioPlayer.loadAudio("/com/example/demo/audio/usertakedamage.wav");
    }

    /**
     * Updates the position of the user's plane.
     * Moves the plane based on its current velocity multipliers.
     */
    @Override
    public void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            double initialTranslateX = getTranslateX();
            this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
            this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newPositionY = getLayoutY() + getTranslateY();
            double newPositionX = getLayoutX() + getTranslateX();
            if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
            if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
                this.setTranslateX(initialTranslateX);
            }
        }
    }

    /**
     * Updates the state of the user's plane.
     * This method is called in each frame to update the plane's behavior.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the user's plane.
     * Plays the firing audio and returns the fired projectile.
     *
     * @return the fired projectile.
     */
    @Override
    public DestructibleEntity fireProjectile() {
        fireAudioPlayer.play();
        return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    /**
     * Inflicts damage on the user's plane.
     * Plays the damage audio when the plane takes damage.
     */
    @Override
    public void takeDamage() {
        super.takeDamage();
        damageAudioPlayer.play();
    }

    /**
     * Checks if the user's plane is moving vertically.
     *
     * @return {@code true} if the plane is moving vertically, {@code false} otherwise.
     */
    private boolean isMovingVertically() {
        return verticalVelocityMultiplier != 0;
    }

    /**
     * Checks if the user's plane is moving horizontally.
     *
     * @return {@code true} if the plane is moving horizontally, {@code false} otherwise.
     */
    private boolean isMovingHorizontally() {
        return horizontalVelocityMultiplier != 0;
    }

    /**
     * Checks if the user's plane is moving.
     *
     * @return {@code true} if the plane is moving, {@code false} otherwise.
     */
    private boolean isMoving() {
        return isMovingVertically() || isMovingHorizontally();
    }

    /**
     * Moves the user's plane up.
     */
    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    /**
     * Moves the user's plane down.
     */
    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }

    /**
     * Moves the user's plane left.
     */
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    /**
     * Moves the user's plane right.
     */
    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    /**
     * Stops the vertical movement of the user's plane.
     */
    public void stopVerticalVelocity() {
        verticalVelocityMultiplier = 0;
    }

    /**
     * Stops the horizontal movement of the user's plane.
     */
    public void stopHorizontalVelocity() {
        horizontalVelocityMultiplier = 0;
    }

    /**
     * Gets the number of kills by the user's plane.
     *
     * @return the number of kills.
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count for the user's plane.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }
}
