package com.example.demo.projectiles;

import java.util.Random;

public class RandomMovingBossProjectile extends BossProjectile {
    private static final int VERTICAL_VELOCITY = 5;
    private static final int FRAMES_BEFORE_DIRECTION_CHANGE = 30;
    private final Random random;
    private int currentDirection;
    private int framesInCurrentDirection;

    public RandomMovingBossProjectile(double initialYPos) {
        super(initialYPos);
        this.random = new Random();
        this.currentDirection = getRandomDirection();
        this.framesInCurrentDirection = 0;
    }

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

    private int getRandomDirection() {
        return random.nextBoolean() ? 1 : -1;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}