package com.example.demo.menus;

import com.example.demo.controller.Controller;
import com.example.demo.displays.WinDisplay;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class WinMenu extends BaseMenu {

    public WinMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    protected VBox getMenuLayout() {
        WinDisplay winDisplay = new WinDisplay();
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Button levelMenuButton = new Button("Level Menu");
        levelMenuButton.setPrefSize(200, 50);
        levelMenuButton.setStyle("-fx-font-size: 18px;");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefSize(200, 50);
        mainMenuButton.setStyle("-fx-font-size: 18px;");
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
