package com.example.demo.projectiles;

import com.example.demo.entities.DestructibleEntity;

/**
 * The {@code Projectile} class represents a projectile entity in the game.
 * It extends {@link DestructibleEntity} and provides methods for updating its position
 * and handling its behavior.
 */
public abstract class Projectile extends DestructibleEntity {

    /**
     * Constructs a {@code Projectile} with the specified image, height, initial position.
     *
     * @param imageName the name of the image file.
     * @param imageHeight the height of the image.
     * @param initialXPos the initial x-coordinate of the projectile.
     * @param initialYPos the initial y-coordinate of the projectile.
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    /**
     * Inflicts damage on the projectile.
     * This method destroys the projectile when it takes damage.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }

    /**
     * Updates the position of the projectile.
     * This method should be implemented by subclasses to define how the projectile moves.
     */
    @Override
    public abstract void updatePosition();
}
