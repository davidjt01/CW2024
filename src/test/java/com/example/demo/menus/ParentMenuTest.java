package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ParentMenuTest extends ApplicationTest {
    private ParentMenu parentMenu;
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
            parentMenu = new ParentMenu(stage, gameController) {
                @Override
                protected VBox getMenuLayout() {
                    VBox menuLayout = new VBox(20);
                    menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");
                    Button testButton = createButton("Test Button");
                    menuLayout.getChildren().add(testButton);
                    return menuLayout;
                }

            };
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testShowMenu() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            parentMenu.show();
            Scene scene = stage.getScene();
            assertNotNull(scene, "Scene should not be null");
            VBox menuLayout = (VBox) scene.getRoot();
            assertEquals(1, menuLayout.getChildren().size(), "Menu layout should contain one button");
            Button testButton = (Button) menuLayout.getChildren().get(0);
            assertEquals("Test Button", testButton.getText(), "Button text should be 'Test Button'");
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testCreateButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Button button = parentMenu.createButton("New Button");
            assertNotNull(button, "Button should not be null");
            assertEquals("New Button", button.getText(), "Button text should be 'New Button'");
            assertEquals(200, button.getPrefWidth(), "Button width should be 200");
            assertEquals(50, button.getPrefHeight(), "Button height should be 50");
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testGetBackground() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Background background = parentMenu.getBackground(stage);
            assertNotNull(background, "Background should not be null");
            BackgroundImage bgImage = background.getImages().get(0);
            assertEquals(BackgroundRepeat.NO_REPEAT, bgImage.getRepeatX(), "Background repeat X should be NO_REPEAT");
            assertEquals(BackgroundRepeat.NO_REPEAT, bgImage.getRepeatY(), "Background repeat Y should be NO_REPEAT");
            assertEquals(BackgroundPosition.CENTER, bgImage.getPosition(), "Background position should be CENTER");
            BackgroundSize bgSize = bgImage.getSize();
            assertEquals(stage.getWidth(), bgSize.getWidth(), "Background width should match stage width");
            assertEquals(stage.getHeight(), bgSize.getHeight(), "Background height should match stage height");
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testGetImage() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Image image = parentMenu.getImage();
            assertNotNull(image, "Image should not be null");
            assertTrue(image.getUrl().contains("/com/example/demo/images/background1.jpg"), "Image URL should contain the background image file path");
            latch.countDown();
        });
        latch.await();
    }
}
