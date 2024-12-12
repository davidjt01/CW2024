package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code PauseMenu} class represents the pause menu displayed when the game is paused.
 * It extends {@link ParentMenu} and provides options to continue the game, go to the level menu, or return to the main menu.
 */
public class PauseMenu extends ParentMenu {

    private Scene gameScene;
    private WritableImage backgroundImage;

    /**
     * Constructs a {@code PauseMenu} with the specified stage and game controller.
     *
     * @param stage the primary stage for this application.
     * @param gameController the game controller managing the menu.
     */
    public PauseMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    /**
     * Saves the current game scene to be restored when the game is resumed.
     *
     * @param gameScene the current game scene.
     */
    public void saveGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     * Sets the background image for the pause menu.
     *
     * @param backgroundImage the background image to set.
     */
    public void setBackgroundImage(WritableImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Gets the image for the background.
     *
     * @return the background image.
     */
    @Override
    protected Image getImage() {
        return backgroundImage;
    }

    /**
     * Creates and returns the layout for the pause menu.
     *
     * @return the layout for the pause menu.
     */
    @Override
    protected VBox getMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text pauseTitle = new Text("Paused");
        pauseTitle.setFont(new Font("Arial", 48));
        pauseTitle.setStyle("-fx-fill: white;");

        Button continueButton = new Button("Continue");
        continueButton.setPrefSize(200, 50);
        continueButton.setStyle("-fx-font-size: 18px;");
        continueButton.setOnAction(e -> {
            stage.setScene(gameScene);
            gameController.onContinueSelected();
        });

        Button levelMenuButton = createButton("Level Menu");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = createButton("Main Menu");
        mainMenuButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(pauseTitle, continueButton, levelMenuButton, mainMenuButton);
        return menuLayout;
    }
}
