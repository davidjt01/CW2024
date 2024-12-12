package com.example.demo.projectiles;

import java.util.Random;

/**
 * The {@code RandomMovingBossProjectile} class represents a projectile fired by boss planes that moves randomly.
 * It extends {@link BossProjectile} and provides methods for updating its position with random vertical movements.
 */
public class RandomMovingBossProjectile extends BossProjectile {
    private static final int VERTICAL_VELOCITY = 5;
    private static final int FRAMES_BEFORE_DIRECTION_CHANGE = 30;
    private final Random random;
    private int currentDirection;
    private int framesInCurrentDirection;

    /**
     * Constructs a {@code RandomMovingBossProjectile} with the specified initial y position.
     *
     * @param initialYPos the initial y-coordinate of the random moving boss projectile.
     */
    public RandomMovingBossProjectile(double initialYPos) {
        super(initialYPos);
        this.random = new Random();
        this.currentDirection = getRandomDirection();
        this.framesInCurrentDirection = 0;
    }

    /**
     * Updates the position of the random moving boss projectile.
     * Moves the projectile horizontally and vertically based on its velocity and random direction.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
        moveVertically(currentDirection * VERTICAL_VELOCITY);
        framesInCurrentDirection++;
        if (framesInCurrentDirection >= FRAMES_BEFORE_DIRECTION_CHANGE) {
            currentDirection = getRandomDirection();
            framesInCurrentDirection = 0;
        }
    }

    /**
     * Gets a random direction for the projectile's vertical movement.
     *
     * @return 1 for upward movement or -1 for downward movement.
     */
    private int getRandomDirection() {
        return random.nextBoolean() ? 1 : -1;
    }

    /**
     * Updates the state of the random moving boss projectile.
     * This method is called in each frame to update the projectile's behavior.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
