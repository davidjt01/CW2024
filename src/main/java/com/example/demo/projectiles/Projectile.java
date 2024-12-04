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

    protected void checkIfOffScreen() {
        if (getLayoutX() + getTranslateX() < 0 || getLayoutX() + getTranslateX() > (double) 1300 ||
                getLayoutY() + getTranslateY() < 0 || getLayoutY() + getTranslateY() > (double) 750) {
            this.destroy();
            System.out.println("destroyed off screen projectile");
        }
    }
}
