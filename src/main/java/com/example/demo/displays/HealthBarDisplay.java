package com.example.demo.displays;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class HealthBarDisplay {
    private static final double INITIAL_PROGRESS = 1.0;

    private final HBox container;
    private final ProgressBar progressBar;

    public HealthBarDisplay(double xPosition, double yPosition) {
        container = new HBox();
        container.setLayoutX(xPosition);
        container.setLayoutY(yPosition);

        progressBar = new ProgressBar(INITIAL_PROGRESS);
        progressBar.setPrefWidth(200);
        progressBar.setPrefHeight(20);

        container.getChildren().add(progressBar);
    }

    public void updateHealth(double healthPercentage) {
        progressBar.setProgress(healthPercentage); // Automatically clamps between 0.0 and 1.0
    }

    public HBox getContainer() {
        return container;
    }
}
