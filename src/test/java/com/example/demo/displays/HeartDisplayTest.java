package com.example.demo.displays;

import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartDisplayTest {

    private HeartDisplay heartDisplay;

    @BeforeEach
    public void setUp() {
        heartDisplay = HeartDisplay.createHeartDisplay(100, 100, 5);
    }

    @Test
    public void testInitialization() {
        HBox container = heartDisplay.getContainer();
        assertNotNull(container, "Container should be initialized");
        assertEquals(100, container.getLayoutX(), "X position should be set correctly");
        assertEquals(100, container.getLayoutY(), "Y position should be set correctly");
        assertEquals(5, container.getChildren().size(), "Number of hearts should be initialized correctly");

        for (int i = 0; i < 5; i++) {
            ImageView heart = (ImageView) container.getChildren().get(i);
            assertNotNull(heart, "Heart should be initialized");
            assertEquals(50, heart.getFitHeight(), "Heart height should be set correctly");
            assertTrue(heart.isPreserveRatio(), "Heart preserve ratio should be true");
        }
    }

    @Test
    public void testRemoveHeart() {
        heartDisplay.removeHeart();
        HBox container = heartDisplay.getContainer();
        assertEquals(4, container.getChildren().size(), "Number of hearts should be reduced by one");

        heartDisplay.removeHeart();
        assertEquals(3, container.getChildren().size(), "Number of hearts should be reduced by one");

        heartDisplay.removeHeart();
        heartDisplay.removeHeart();
        heartDisplay.removeHeart();
        assertEquals(0, container.getChildren().size(), "All hearts should be removed");
    }
}
