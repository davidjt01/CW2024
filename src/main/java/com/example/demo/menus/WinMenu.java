package com.example.demo.menus;

import com.example.demo.controller.Controller;
import com.example.demo.displays.WinDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code WinMenu} class represents the win menu displayed when the player wins the game.
 * It extends {@link ParentMenu} and provides options to go to the level menu or return to the main menu.
 */
public class WinMenu extends ParentMenu {

    /**
     * Constructs a {@code WinMenu} with the specified stage and game controller.
     *
     * @param stage the primary stage for this application.
     * @param gameController the game controller managing the menu.
     */
    public WinMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    /**
     * Creates and returns the layout for the win menu.
     *
     * @return the layout for the win menu.
     */
    @Override
    protected VBox getMenuLayout() {
        WinDisplay winDisplay = new WinDisplay();
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Button levelMenuButton = createButton("Level Menu");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = createButton("Main Menu");
        mainMenuButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(winDisplay, levelMenuButton, mainMenuButton);
        winDisplay.showWinImage();
        return menuLayout;
    }

    /**
     * Returns the file path for the background image of the win menu.
     *
     * @return the file path for the background image.
     */
    @Override
    protected String getBackgroundImageFile() {
        return "/com/example/demo/images/background2.jpg";
    }
}
