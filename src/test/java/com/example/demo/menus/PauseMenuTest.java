package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuTest extends ApplicationTest {
    private PauseMenu pauseMenu;
    private Stage stage;
    private Controller gameController;
    private Scene gameScene;

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
            pauseMenu = new PauseMenu(stage, gameController);
            gameScene = new Scene(new VBox(), 800, 600);
            pauseMenu.saveGameScene(gameScene);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testShowMenu() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            WritableImage backgroundImage = new WritableImage(800, 600);
            pauseMenu.setBackgroundImage(backgroundImage);
            pauseMenu.show();
            Scene scene = stage.getScene();
            assertNotNull(scene, "Scene should not be null");
            VBox menuLayout = (VBox) scene.getRoot();
            assertEquals(4, menuLayout.getChildren().size(), "Menu layout should contain four elements");
            Button continueButton = (Button) menuLayout.getChildren().get(1);
            assertEquals("Continue", continueButton.getText(), "Button text should be 'Continue'");
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testSetBackgroundImage() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            WritableImage backgroundImage = new WritableImage(800, 600);
            pauseMenu.setBackgroundImage(backgroundImage);
            Image image = pauseMenu.getImage();
            assertNotNull(image, "Image should not be null");
            assertEquals(backgroundImage, image, "Background image should match the set image");
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testButtonActions() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            WritableImage backgroundImage = new WritableImage(800, 600);
            pauseMenu.setBackgroundImage(backgroundImage);
            pauseMenu.show();
            VBox menuLayout = (VBox) stage.getScene().getRoot();
            Button continueButton = (Button) menuLayout.getChildren().get(1);
            continueButton.fire();
            assertEquals(gameScene, stage.getScene(), "Stage scene should be set to game scene on continue");

            Button levelMenuButton = (Button) menuLayout.getChildren().get(2);
            levelMenuButton.fire();

            Button mainMenuButton = (Button) menuLayout.getChildren().get(3);
            mainMenuButton.fire();

            latch.countDown();
        });
        latch.await();
    }
}
