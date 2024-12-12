package com.example.demo.displays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class ExplosionDisplayTest extends ApplicationTest {

    private ExplosionDisplay explosionDisplay;

    @BeforeEach
    public void setUp() {
        explosionDisplay = new ExplosionDisplay(100, 100);
    }

    @Test
    public void testInitialization() {
        assertNotNull(explosionDisplay.getImage(), "Image should be loaded");
        assertEquals(100, explosionDisplay.getLayoutX(), "X position should be set correctly");
        assertEquals(100, explosionDisplay.getLayoutY(), "Y position should be set correctly");
        assertEquals(200, explosionDisplay.getFitHeight(), "Height should be set correctly");
        assertTrue(explosionDisplay.isVisible(), "Explosion should be visible initially");
    }

    @Test
    public void testVisibilityAfterDuration() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> assertFalse(explosionDisplay.isVisible(), "Explosion should be invisible after 1 second")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
