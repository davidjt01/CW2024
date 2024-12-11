package com.example.demo.entities;

/**
 * The {@code Destructible} interface defines the methods required for an entity
 * that can take damage and be destroyed in the game.
 */
public interface Destructible {

    /**
     * Inflicts damage on the entity.
     * This method should be called when the entity takes damage.
     */
    void takeDamage();

    /**
     * Destroys the entity.
     * This method should be called when the entity is destroyed.
     */
    void destroy();

}
