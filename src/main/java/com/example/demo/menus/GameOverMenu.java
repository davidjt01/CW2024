package com.example.demo.menus;

import com.example.demo.controller.Controller;
import com.example.demo.displays.GameOverDisplay;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class GameOverMenu {
    private final Stage stage;
    private final Controller gameController;
    private String levelName;

    public GameOverMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void show() {
        GameOverDisplay gameOverDisplay = new GameOverDisplay();
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Button retryButton = new Button("Retry");
        retryButton.setPrefSize(200, 50);
        retryButton.setStyle("-fx-font-size: 18px;");

        System.out.println(levelName);
        retryButton.setOnAction(e -> gameController.onLevelSelected(levelName));

        Button levelMenuButton = new Button("Level Menu");
        levelMenuButton.setPrefSize(200, 50);
        levelMenuButton.setStyle("-fx-font-size: 18px;");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefSize(200, 50);
        mainMenuButton.setStyle("-fx-font-size: 18px;");
        mainMenuButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(gameOverDisplay, retryButton, levelMenuButton, mainMenuButton);

        gameOverDisplay.showGameOver();

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/background1.jpg")).toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        menuLayout.setBackground(new Background(bgImage));

        Scene scene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
