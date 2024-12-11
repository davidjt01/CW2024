package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 *
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
     * @param xPosition
     * @param yPosition
     * @param heartsToDisplay
     */
    private HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.numberOfHeartsToDisplay = heartsToDisplay;
        initializeContainer();
        initializeHearts();
    }

    /**
     * @param xPosition
     * @param yPosition
     * @param heartsToDisplay
     * @return
     */
    public static HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        return new HeartDisplay(xPosition, yPosition, heartsToDisplay);
    }

    /**
     *
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
    }

    /**
     *
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
     *
     */
    public void removeHeart() {
        if (!container.getChildren().isEmpty())
            container.getChildren().remove(INDEX_OF_FIRST_ITEM);
    }

    /**
     * @return
     */
    public HBox getContainer() {
        return container;
    }

}
