package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LevelMenu {
    private final Stage stage;
    private final Controller gameController;

    public LevelMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    public void show() {
        VBox menulayout = new VBox(20);
        menulayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Image backgroundImage = new Image(getClass().getResource("/com/example/demo/images/background1.jpg").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        menulayout.setBackground(new Background(bgImage));

        for (int i = 1; i <= 5; i++) {
            int level = i;
            Button levelButton = new Button("Level " + level);
            levelButton.setPrefSize(200, 50);
            levelButton.setStyle("-fx-font-size: 18px;");

            String className = "com.example.demo.levels.Level" + getLevelName(level);

            levelButton.setOnAction(e -> {
                try {
                    gameController.onLevelSelected(className);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            menulayout.getChildren().add(levelButton);
        }

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 18px;");

        backButton.setOnAction(e -> gameController.onMainMenuSelected());

        menulayout.getChildren().add(backButton);

        Scene scene =  new Scene(menulayout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    private String getLevelName(int levelNumber) {
        switch (levelNumber) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            default:
                throw new IllegalArgumentException("Invalid level number: " + levelNumber);
        }
    }
}
