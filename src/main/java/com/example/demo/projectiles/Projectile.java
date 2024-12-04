package com.example.demo.projectiles;

import com.example.demo.entities.DestructibleEntity;

public abstract class Projectile extends DestructibleEntity {

    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    @Override
    public void takeDamage() {
        this.destroy();
    }

    @Override
    public abstract void updatePosition();

    protected void checkIfOffScreen(double screenWidth, double screenHeight) {
        if (getLayoutX() + getTranslateX() < 0 || getLayoutX() + getTranslateX() > screenWidth ||
                getLayoutY() + getTranslateY() < 0 || getLayoutY() + getTranslateY() > screenHeight) {
            this.destroy();
        }
    }
}
