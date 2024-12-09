package com.example.demo.displays;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBarDisplay {
    private static final int BAR_WIDTH = 200;
    private static final int BAR_HEIGHT = 20;
    private static final Color HEALTH_COLOR = Color.GREEN;
    private static final Color BACKGROUND_COLOR = Color.RED;

    private HBox container;
    private Rectangle healthBar;
    private Rectangle backgroundBar;

    public HealthBarDisplay(double xPosition, double yPosition) {
        container = new HBox();
        container.setLayoutX(xPosition);
        container.setLayoutY(yPosition);

        backgroundBar = new Rectangle(BAR_WIDTH, BAR_HEIGHT, BACKGROUND_COLOR);
        healthBar = new Rectangle(BAR_WIDTH, BAR_HEIGHT, HEALTH_COLOR);

        container.getChildren().addAll(backgroundBar, healthBar);
    }

    public void updateHealth(double healthPercentage) {
        healthBar.setWidth(BAR_WIDTH * healthPercentage);
    }

    public HBox getContainer() {
        return container;
    }
}
