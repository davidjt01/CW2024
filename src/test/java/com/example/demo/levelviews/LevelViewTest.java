package com.example.demo.levelviews;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class LevelViewTest extends ApplicationTest {
    private LevelView levelView;
    private Group root;

    @Override
    public void start(Stage stage) {
        // Required by TestFX but not used in this test
    }

    @BeforeEach
    void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            root = new Group();
            levelView = LevelView.createLevelView(root, 5);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testInitialization() {
        Platform.runLater(() -> {
            assertNotNull(levelView, "LevelView should be initialized");
            assertNotNull(levelView.getHeartDisplay(), "HeartDisplay should be initialized");
            assertEquals(5, levelView.getHeartDisplay().getContainer().getChildren().size(), "HeartDisplay should contain 5 hearts");
        });
    }

    @Test
    void testShowHeartDisplay() {
        Platform.runLater(() -> {
            levelView.showHeartDisplay();
            HBox heartContainer = levelView.getHeartDisplay().getContainer();
            assertTrue(root.getChildren().contains(heartContainer), "HeartDisplay should be added to the root group");
        });
    }

    @Test
    void testRemoveHearts() {
        Platform.runLater(() -> {
            levelView.showHeartDisplay();
            levelView.removeHearts(3);
            assertEquals(3, levelView.getHeartDisplay().getContainer().getChildren().size(), "HeartDisplay should contain 3 hearts after removing 2");
            levelView.removeHearts(1);
            assertEquals(1, levelView.getHeartDisplay().getContainer().getChildren().size(), "HeartDisplay should contain 1 heart after removing 2 more");
        });
    }
}
