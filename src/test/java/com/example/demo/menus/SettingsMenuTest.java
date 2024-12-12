package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class SettingsMenuTest extends ApplicationTest {
    private SettingsMenu settingsMenu;
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
            settingsMenu = new SettingsMenu(stage, gameController);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testVolumeControl() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            VBox menuLayout = settingsMenu.getMenuLayout();
            HBox volumeControlLayout = (HBox) menuLayout.getChildren().get(1);
            Text volumeText = (Text) volumeControlLayout.getChildren().get(1);
            assertEquals("Volume: 100%", volumeText.getText(), "Initial volume should be 100%");

            Button decreaseVolumeButton = (Button) volumeControlLayout.getChildren().get(0);
            decreaseVolumeButton.fire();
            assertEquals("Volume: 90%", volumeText.getText(), "Volume should decrease to 90%");

            Button increaseVolumeButton = (Button) volumeControlLayout.getChildren().get(2);
            increaseVolumeButton.fire();
            assertEquals("Volume: 100%", volumeText.getText(), "Volume should increase to 100%");

            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testBackButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button backButton = (Button) settingsMenu.getMenuLayout().getChildren().get(2);
            assertEquals("Back", backButton.getText(), "Back button should have the correct text.");
            backButton.fire();
            // Add assertions to verify the behavior when the back button is clicked
            latch.countDown();
        });
        latch.await();
    }
}
