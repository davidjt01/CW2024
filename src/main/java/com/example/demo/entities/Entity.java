package com.example.demo.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code Entity} class represents a game entity with an image.
 * It extends {@link ImageView} and provides methods to update the entity's position.
 */
public abstract class Entity extends ImageView {

    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    /**
     * Constructs an {@code Entity} with the specified image, height, and initial position.
     *
     * @param imageName the name of the image file.
     * @param imageHeight the height of the image.
     * @param initialXPos the initial x-coordinate of the entity.
     * @param initialYPos the initial y-coordinate of the entity.
     */
    public Entity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    /**
     * Updates the position of the entity.
     * This method should be implemented by subclasses to define how the entity moves.
     */
    public abstract void updatePosition();

    /**
     * Moves the entity horizontally by the specified amount.
     *
     * @param horizontalMove the amount to move the entity horizontally.
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the entity vertically by the specified amount.
     *
     * @param verticalMove the amount to move the entity vertically.
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }

}
