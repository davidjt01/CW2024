package com.example.demo.planes;

import com.example.demo.entities.DestructibleEntity;

import java.util.List;

public abstract class Plane extends DestructibleEntity {

    private int health;

    public Plane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    public abstract DestructibleEntity fireProjectile();

    public abstract List<DestructibleEntity> fireProjectiles();

    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    private boolean healthAtZero() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }

}
