package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class LevelMenu extends BaseMenu {

    public LevelMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    protected VBox getMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        for (int i = 1; i <= 5; i++) {
            Button levelButton = new Button("Level " + i);
            levelButton.setPrefSize(200, 50);
            levelButton.setStyle("-fx-font-size: 18px;");

            String className = "com.example.demo.levels.Level" + getLevelName(i);

            levelButton.setOnAction(e -> {
                try {
                    gameController.onLevelSelected(className);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            menuLayout.getChildren().add(levelButton);
        }

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 18px;");

        backButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().add(backButton);
        return menuLayout;
    }

    private String getLevelName(int levelNumber) {
        return switch (levelNumber) {
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            case 4 -> "Four";
            case 5 -> "Five";
            default -> throw new IllegalArgumentException("Invalid level number: " + levelNumber);
        };
    }
}
