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

class MainMenuTest extends ApplicationTest {

    private MainMenu mainMenu;
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
            mainMenu = new MainMenu(stage, gameController);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testMenuButtons() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button playButton = (Button) mainMenu.getMenuLayout().getChildren().get(1);
            assertEquals("Play", playButton.getText(), "Play button should have the correct text.");
            playButton.fire();

            Button settingsButton = (Button) mainMenu.getMenuLayout().getChildren().get(2);
            assertEquals("Settings", settingsButton.getText(), "Settings button should have the correct text.");
            settingsButton.fire();

            Button quitButton = (Button) mainMenu.getMenuLayout().getChildren().get(3);
            assertEquals("Quit", quitButton.getText(), "Quit button should have the correct text.");
            quitButton.fire();

            latch.countDown();
        });
        latch.await();
    }
}
