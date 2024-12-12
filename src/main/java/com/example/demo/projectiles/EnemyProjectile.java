package com.example.demo.projectiles;

/**
 * The {@code EnemyProjectile} class represents a projectile fired by enemy planes in the game.
 * It extends {@link Projectile} and provides methods for updating its position and handling its behavior.
 */
public class EnemyProjectile extends Projectile {
    private static final String IMAGE_NAME = "enemyFire.png";
    private static final int IMAGE_HEIGHT = 32;
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * Constructs an {@code EnemyProjectile} with the specified initial x and y positions.
     *
     * @param initialXPos the initial x-coordinate of the enemy projectile.
     * @param initialYPos the initial y-coordinate of the enemy projectile.
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the enemy projectile.
     * Moves the projectile horizontally based on its velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the enemy projectile.
     * This method is called in each frame to update the projectile's behavior.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
