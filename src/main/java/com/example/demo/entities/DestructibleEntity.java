package com.example.demo.entities;

public abstract class DestructibleEntity extends Entity implements Destructible {

    private boolean isDestroyed;

    public DestructibleEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        isDestroyed = false;
    }

    @Override
    public abstract void updatePosition();

    public abstract void updateActor();

    @Override
    public abstract void takeDamage();

    @Override
    public void destroy() {
        setDestroyed();
    }

    protected void setDestroyed() {
        this.isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

}
