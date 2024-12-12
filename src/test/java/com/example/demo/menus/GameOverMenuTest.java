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

class GameOverMenuTest extends ApplicationTest {

    private GameOverMenu gameOverMenu;
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
            gameOverMenu = new GameOverMenu(stage, gameController);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testRetryButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            gameOverMenu.setLevelName("com.example.demo.levels.LevelOne");
            Button retryButton = (Button) gameOverMenu.getMenuLayout().getChildren().get(1);
            assertEquals("Retry", retryButton.getText(), "Retry button should have the correct text.");
            retryButton.fire();
            // Add assertions to verify the behavior when the retry button is clicked
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testLevelMenuButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button levelMenuButton = (Button) gameOverMenu.getMenuLayout().getChildren().get(2);
            assertEquals("Level Menu", levelMenuButton.getText(), "Level Menu button should have the correct text.");
            levelMenuButton.fire();
            // Add assertions to verify the behavior when the level menu button is clicked
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testMainMenuButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button mainMenuButton = (Button) gameOverMenu.getMenuLayout().getChildren().get(3);
            assertEquals("Main Menu", mainMenuButton.getText(), "Main Menu button should have the correct text.");
            mainMenuButton.fire();
            // Add assertions to verify the behavior when the main menu button is clicked
            latch.countDown();
        });
        latch.await();
    }
}
