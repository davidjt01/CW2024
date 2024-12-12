package com.example.demo.projectiles;

/**
 * The {@code BossProjectile} class represents a projectile fired by boss planes in the game.
 * It extends {@link Projectile} and provides methods for updating its position and handling its behavior.
 */
public class BossProjectile extends Projectile {
    protected static final int HORIZONTAL_VELOCITY = -15;
    private static final String IMAGE_NAME = "fireball.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a {@code BossProjectile} with the specified initial y position.
     *
     * @param initialYPos the initial y-coordinate of the boss projectile.
     */
    public BossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the position of the boss projectile.
     * Moves the projectile horizontally based on its velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the boss projectile.
     * This method is called in each frame to update the projectile's behavior.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
