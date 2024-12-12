package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.displays.ExplosionDisplay;
import com.example.demo.entities.DestructibleEntity;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import java.util.List;

/**
 * The {@code Plane} class represents a plane entity in the game.
 * It extends {@link DestructibleEntity} and provides methods for firing projectiles,
 * taking damage, and handling destruction.
 */
public abstract class Plane extends DestructibleEntity {
    private final AudioPlayer explosionAudioPlayer;
    private int health;

    /**
     * Constructs a {@code Plane} with the specified image, height, initial position, and health.
     *
     * @param imageName the name of the image file.
     * @param imageHeight the height of the image.
     * @param initialXPos the initial x-coordinate of the plane.
     * @param initialYPos the initial y-coordinate of the plane.
     * @param health the initial health of the plane.
     */
    public Plane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
        explosionAudioPlayer = AudioPlayer.createAudioPlayer();
        explosionAudioPlayer.loadAudio("/com/example/demo/audio/explosion.wav");
    }

    /**
     * Fires a projectile from the plane.
     *
     * @return the fired projectile.
     */
    public abstract DestructibleEntity fireProjectile();

    /**
     * Fires multiple projectiles from the plane.
     *
     * @return a list of fired projectiles.
     */
    public List<DestructibleEntity> fireProjectiles() {
        DestructibleEntity projectile = fireProjectile();
        if (projectile != null) {
            return List.of(projectile);
        }
        return List.of();
    }

    /**
     * Inflicts damage on the plane.
     * If the plane's health reaches zero, it is destroyed.
     */
    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    /**
     * Destroys the plane and plays an explosion sound.
     * Displays an explosion effect at the plane's position.
     */
    @Override
    public void destroy() {
        super.destroy();
        explosionAudioPlayer.play();
        ExplosionDisplay explosionDisplay = new ExplosionDisplay(
                this.getLayoutX() + this.getTranslateX(),
                this.getLayoutY() + this.getTranslateY()
        );
        if (this.getParent() instanceof Group) {
            ((Group) this.getParent()).getChildren().add(explosionDisplay);
        } else if (this.getParent() instanceof Pane) {
            ((Pane) this.getParent()).getChildren().add(explosionDisplay);
        }
    }

    /**
     * Gets the x-coordinate for the projectile's initial position.
     *
     * @param xPositionOffset the x-coordinate offset.
     * @return the x-coordinate for the projectile's initial position.
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Gets the y-coordinate for the projectile's initial position.
     *
     * @param yPositionOffset the y-coordinate offset.
     * @return the y-coordinate for the projectile's initial position.
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the plane's health is zero.
     *
     * @return {@code true} if the plane's health is zero, {@code false} otherwise.
     */
    private boolean healthAtZero() {
        return health == 0;
    }

    /**
     * Gets the current health of the plane.
     *
     * @return the current health of the plane.
     */
    public int getHealth() {
        return health;
    }
}
