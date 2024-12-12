package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The {@code ParentMenu} class serves as a base class for all menu screens in the game.
 * It provides common functionality for displaying menus, creating buttons, and setting backgrounds.
 */
public abstract class ParentMenu {

    protected Stage stage;
    protected Controller gameController;

    /**
     * Constructs a {@code ParentMenu} with the specified stage and game controller.
     *
     * @param stage the primary stage for this application.
     * @param gameController the game controller managing the menu.
     */
    public ParentMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    /**
     * Displays the menu on the stage.
     */
    public void show() {
        VBox menuLayout = getMenuLayout();
        menuLayout.setBackground(getBackground(stage));
        Scene scene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a button with the specified text.
     *
     * @param buttonText the text to display on the button.
     * @return the created button.
     */
    protected Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setPrefSize(200, 50);
        button.setStyle("-fx-font-size: 18px;");
        return button;
    }

    /**
     * Gets the background for the menu.
     *
     * @param stage the primary stage for this application.
     * @return the background for the menu.
     */
    protected Background getBackground(Stage stage) {
        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false);
        BackgroundImage bgImage = new BackgroundImage(getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(bgImage);
    }

    /**
     * Gets the image for the background.
     *
     * @return the image for the background.
     */
    protected Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResource(getBackgroundImageFile())).toExternalForm());
    }

    /**
     * Gets the file path for the background image.
     *
     * @return the file path for the background image.
     */
    protected String getBackgroundImageFile() {
        return "/com/example/demo/images/background1.jpg";
    }

    /**
     * Creates and returns the layout for the menu.
     *
     * @return the layout for the menu.
     */
    abstract VBox getMenuLayout();
}
