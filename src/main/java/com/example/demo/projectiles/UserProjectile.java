package com.example.demo.projectiles;

/**
 * The {@code UserProjectile} class represents a projectile fired by the user's plane.
 * It extends {@link Projectile} and provides methods for updating its position and handling its behavior.
 */
public class UserProjectile extends Projectile {
    private static final String IMAGE_NAME = "userfire.png";
    private static final int IMAGE_HEIGHT = 6;
    private static final int HORIZONTAL_VELOCITY = 15;

    /**
     * Constructs a {@code UserProjectile} with the specified initial x and y positions.
     *
     * @param initialXPos the initial x-coordinate of the user projectile.
     * @param initialYPos the initial y-coordinate of the user projectile.
     */
    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the user projectile.
     * Moves the projectile horizontally based on its velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the user projectile.
     * This method is called in each frame to update the projectile's behavior.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
