package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelMenu extends ParentMenu {

    public LevelMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    protected VBox getMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        for (int i = 1; i <= 5; i++) {
            Button levelButton = createButton("Level " + i);

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

        Button backButton = createButton("Back");
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
