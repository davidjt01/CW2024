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

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/background1.jpg")).toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        menulayout.setBackground(new Background(bgImage));

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
            menulayout.getChildren().add(levelButton);
        }

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 18px;");

        backButton.setOnAction(e -> gameController.onMainMenuSelected());

        menulayout.getChildren().add(backButton);

        Scene scene = new Scene(menulayout, stage.getWidth(), stage.getHeight());

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });

        stage.setScene(scene);
        stage.show();
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
