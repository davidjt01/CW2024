package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code ShieldDisplay} class represents a shield effect in the game.
 * It extends {@link ImageView} and displays a shield image at a specified position.
 */
public class ShieldDisplay extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
    private static final int SHIELD_SIZE = 200;

    /**
     * Constructs a {@code ShieldDisplay} at the specified position.
     *
     * @param xPosition the x-coordinate of the shield.
     * @param yPosition the y-coordinate of the shield.
     */
    private ShieldDisplay(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    /**
     * Creates a new instance of {@code ShieldDisplay}.
     *
     * @param xPosition the x-coordinate of the shield.
     * @param yPosition the y-coordinate of the shield.
     * @return a new {@code ShieldDisplay} instance.
     */
    public static ShieldDisplay createShieldDisplay(double xPosition, double yPosition) {
        return new ShieldDisplay(xPosition, yPosition);
    }

    /**
     * Makes the shield image visible.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Makes the shield image invisible.
     */
    public void hideShield() {
        this.setVisible(false);
    }
}
