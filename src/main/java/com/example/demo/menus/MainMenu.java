package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends ParentMenu {

    public MainMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }


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
