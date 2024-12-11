package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * The {@code HeartDisplay} class represents a display of hearts in the game.
 * It uses {@link ImageView} to visually indicate the number of hearts (lives) remaining.
 */
public class HeartDisplay {

    private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
    private static final int HEART_HEIGHT = 50;
    private static final int INDEX_OF_FIRST_ITEM = 0;
    private final double containerXPosition;
    private final double containerYPosition;
    private final int numberOfHeartsToDisplay;
    private HBox container;

    /**
     * Constructs a {@code HeartDisplay} at the specified position with the given number of hearts.
     *
     * @param xPosition the x-coordinate of the heart display.
     * @param yPosition the y-coordinate of the heart display.
     * @param heartsToDisplay the number of hearts to display.
     */
    private HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.numberOfHeartsToDisplay = heartsToDisplay;
        initializeContainer();
        initializeHearts();
    }

    /**
     * Creates a new instance of {@code HeartDisplay}.
     *
     * @param xPosition the x-coordinate of the heart display.
     * @param yPosition the y-coordinate of the heart display.
     * @param heartsToDisplay the number of hearts to display.
     * @return a new {@code HeartDisplay} instance.
     */
    public static HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        return new HeartDisplay(xPosition, yPosition, heartsToDisplay);
    }

    /**
     * Initializes the container for the heart display.
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
    }

    /**
     * Initializes the hearts in the display.
     */
    private void initializeHearts() {
        for (int i = 0; i < numberOfHeartsToDisplay; i++) {
            ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));
            heart.setFitHeight(HEART_HEIGHT);
            heart.setPreserveRatio(true);
            container.getChildren().add(heart);
        }
    }

    /**
     * Removes one heart from the display.
     */
    public void removeHeart() {
        if (!container.getChildren().isEmpty())
            container.getChildren().remove(INDEX_OF_FIRST_ITEM);
    }

    /**
     * Gets the container holding the hearts.
     *
     * @return the container {@link HBox} of the hearts.
     */
    public HBox getContainer() {
        return container;
    }
}
