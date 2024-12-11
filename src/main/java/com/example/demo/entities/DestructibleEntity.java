package com.example.demo.entities;

/**
 * The {@code DestructibleEntity} class represents an entity that can take damage and be destroyed.
 * It extends {@link Entity} and implements the {@link Destructible} interface.
 */
public abstract class DestructibleEntity extends Entity implements Destructible {
    private boolean isDestroyed;

    /**
     * Constructs a {@code DestructibleEntity} with the specified image, height, and initial position.
     *
     * @param imageName the name of the image file.
     * @param imageHeight the height of the image.
     * @param initialXPos the initial x-coordinate of the entity.
     * @param initialYPos the initial y-coordinate of the entity.
     */
    public DestructibleEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        isDestroyed = false;
    }

    /**
     * Updates the position of the entity.
     * This method should be implemented by subclasses to define how the entity moves.
     */
    @Override
    public abstract void updatePosition();

    /**
     * Updates the state of the entity.
     * This method should be implemented by subclasses to define how the entity behaves.
     */
    public abstract void updateActor();

    /**
     * Inflicts damage on the entity.
     * This method should be implemented by subclasses to define how the entity takes damage.
     */
    @Override
    public abstract void takeDamage();

    /**
     * Destroys the entity.
     * This method sets the entity's destroyed state to true.
     */
    @Override
    public void destroy() {
        setDestroyed();
    }

    /**
     * Checks if the entity is destroyed.
     *
     * @return {@code true} if the entity is destroyed, {@code false} otherwise.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Sets the entity's destroyed state to true.
     */
    protected void setDestroyed() {
        this.isDestroyed = true;
    }
}
