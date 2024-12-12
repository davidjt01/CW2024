package com.example.demo.displays;

import javafx.scene.layout.HBox;
import javafx.scene.control.ProgressBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthBarDisplayTest {

    private HealthBarDisplay healthBarDisplay;

    @BeforeEach
    public void setUp() {
        healthBarDisplay = HealthBarDisplay.createHealthBarDisplay(100, 100);
    }

    @Test
    public void testInitialization() {
        HBox container = healthBarDisplay.getContainer();
        assertNotNull(container, "Container should be initialized");
        assertEquals(100, container.getLayoutX(), "X position should be set correctly");
        assertEquals(100, container.getLayoutY(), "Y position should be set correctly");

        ProgressBar progressBar = (ProgressBar) container.getChildren().get(0);
        assertNotNull(progressBar, "ProgressBar should be initialized");
        assertEquals(1.0, progressBar.getProgress(), "Initial progress should be set to 1.0");
        assertEquals(200, progressBar.getPrefWidth(), "ProgressBar width should be set correctly");
        assertEquals(20, progressBar.getPrefHeight(), "ProgressBar height should be set correctly");
        assertEquals("-fx-accent: red;", progressBar.getStyle(), "ProgressBar style should be set correctly");
    }

    @Test
    public void testUpdateHealth() {
        healthBarDisplay.updateHealth(0.5);
        ProgressBar progressBar = (ProgressBar) healthBarDisplay.getContainer().getChildren().get(0);
        assertEquals(0.5, progressBar.getProgress(), "Progress should be updated to 0.5");

        healthBarDisplay.updateHealth(0.0);
        assertEquals(0.0, progressBar.getProgress(), "Progress should be updated to 0.0");

        healthBarDisplay.updateHealth(1.0);
        assertEquals(1.0, progressBar.getProgress(), "Progress should be updated to 1.0");
    }
}
