package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code MainMenu} class represents the main menu of the game.
 * It extends {@link ParentMenu} and provides options to start the game, access settings, or quit the application.
 */
public class MainMenu extends ParentMenu {

    /**
     * Constructs a {@code MainMenu} with the specified stage and game controller.
     *
     * @param stage the primary stage for this application.
     * @param gameController the game controller managing the menu.
     */
    public MainMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    /**
     * Creates and returns the layout for the main menu.
     *
     * @return the layout for the main menu.
     */
    @Override
    protected VBox getMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text gameTitle = new Text("Sky Battle");
        gameTitle.setFont(new Font("Arial", 48));
        gameTitle.setStyle("-fx-fill: white;");

        Button levelMenuButton = createButton("Play");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button settingsButton = createButton("Settings");
        settingsButton.setOnAction(e -> gameController.onSettingsMenuSelected());

        Button quitButton = createButton("Quit");
        quitButton.setOnAction(e -> stage.close());

        menuLayout.getChildren().addAll(gameTitle, levelMenuButton, settingsButton, quitButton);

        return menuLayout;
    }
}
