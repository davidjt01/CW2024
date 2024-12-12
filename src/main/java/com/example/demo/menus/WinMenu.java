package com.example.demo.menus;

import com.example.demo.controller.Controller;
import com.example.demo.displays.WinDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WinMenu extends ParentMenu {

    public WinMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

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

    @Override
    protected String getBackgroundImageFile() {
        return "/com/example/demo/images/background2.jpg";
    }
}
