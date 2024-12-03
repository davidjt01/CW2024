package com.example.demo.projectiles;

import java.util.Random;

public class RandomMovingBossProjectile extends BossProjectile {
    private static final int VERTICAL_VELOCITY = 5;
    private final Random random;

    public RandomMovingBossProjectile(double initialYPos) {
        super(initialYPos);
        this.random = new Random();
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
        moveVertically(getRandomVerticalMove());
    }

    private double getRandomVerticalMove() {
        return (random.nextBoolean() ? 1 : -1) * VERTICAL_VELOCITY;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}