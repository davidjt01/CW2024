package com.example.demo.displays;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 * The {@code HealthBarDisplay} class represents a health bar display in the game.
 * It uses a {@link ProgressBar} to visually indicate the health status of an entity.
 */
public class HealthBarDisplay {
    private static final double INITIAL_PROGRESS = 1.0;
    private static final String RED_BAR_STYLE = "-fx-accent: red;";

    private final HBox container;
    private final ProgressBar progressBar;

    /**
     * Constructs a {@code HealthBarDisplay} at the specified position.
     *
     * @param xPosition the x-coordinate of the health bar.
     * @param yPosition the y-coordinate of the health bar.
     */
    private HealthBarDisplay(double xPosition, double yPosition) {
        container = new HBox();
        container.setLayoutX(xPosition);
        container.setLayoutY(yPosition);

        progressBar = new ProgressBar(INITIAL_PROGRESS);
        progressBar.setPrefWidth(200);
        progressBar.setPrefHeight(20);
        progressBar.setStyle(RED_BAR_STYLE);

        container.getChildren().add(progressBar);
    }

    /**
     * Creates a new instance of {@code HealthBarDisplay}.
     *
     * @param xPosition the x-coordinate of the health bar.
     * @param yPosition the y-coordinate of the health bar.
     * @return a new {@code HealthBarDisplay} instance.
     */
    public static HealthBarDisplay createHealthBarDisplay(double xPosition, double yPosition) {
        return new HealthBarDisplay(xPosition, yPosition);
    }

    /**
     * Updates the health bar to reflect the current health percentage.
     *
     * @param healthPercentage the current health percentage, a value between 0.0 and 1.0.
     */
    public void updateHealth(double healthPercentage) {
        progressBar.setProgress(healthPercentage);
    }

    /**
     * Gets the container holding the health bar.
     *
     * @return the container {@link HBox} of the health bar.
     */
    public HBox getContainer() {
        return container;
    }
}
