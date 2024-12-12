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

class WinMenuTest extends ApplicationTest {

    private WinMenu winMenu;
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
            winMenu = new WinMenu(stage, gameController);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testLevelMenuButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button levelMenuButton = (Button) winMenu.getMenuLayout().getChildren().get(1);
            assertEquals("Level Menu", levelMenuButton.getText(), "Level Menu button should have the correct text.");
            levelMenuButton.fire();
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testMainMenuButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button mainMenuButton = (Button) winMenu.getMenuLayout().getChildren().get(2);
            assertEquals("Main Menu", mainMenuButton.getText(), "Main Menu button should have the correct text.");
            mainMenuButton.fire();
            latch.countDown();
        });
        latch.await();
    }
}
