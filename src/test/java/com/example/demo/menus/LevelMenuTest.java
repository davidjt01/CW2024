package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class LevelMenuTest extends ApplicationTest {

    private LevelMenu levelMenu;
    private Stage stage;
    private Controller gameController;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
    }

    @BeforeEach
    void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            gameController = new Controller() {
                @Override
                public void onLevelSelected(String level) {
                    // No-op for test
                }

                @Override
                public void onMainMenuSelected() {
                    // No-op for test
                }

                @Override
                public void onLevelMenuSelected() {
                    // No-op for test
                }

                @Override
                public void onContinueSelected() {
                    // No-op for test
                }

                @Override
                public void onSettingsMenuSelected() {
                    // No-op for test
                }
            };
            levelMenu = new LevelMenu(stage, gameController);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testLevelButtons() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            for (int i = 1; i <= 5; i++) {
                Button levelButton = (Button) levelMenu.getMenuLayout().getChildren().get(i - 1);
                assertEquals("Level " + i, levelButton.getText(), "Level button should have the correct text.");
                levelButton.fire();
            }
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testBackButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button backButton = (Button) levelMenu.getMenuLayout().getChildren().get(5);
            assertEquals("Back", backButton.getText(), "Back button should have the correct text.");
            backButton.fire();
            latch.countDown();
        });
        latch.await();
    }
}
