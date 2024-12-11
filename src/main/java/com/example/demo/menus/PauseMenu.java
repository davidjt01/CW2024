package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PauseMenu {
    private final Stage stage;
    private final Controller gameController;
    private Scene gameScene;
    private WritableImage backgroundImage; // Add this field

    public PauseMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    public void saveGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setBackgroundImage(WritableImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void show() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Set the captured image as the background
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false));
        menuLayout.setBackground(new Background(bgImage));

        Text pauseTitle = new Text("Paused");
        pauseTitle.setFont(new Font("Arial", 48));
        pauseTitle.setStyle("-fx-fill: white;");

        Button continueButton = new Button("Continue");
        continueButton.setPrefSize(200, 50);
        continueButton.setStyle("-fx-font-size: 18px;");
        continueButton.setOnAction(e -> {
            stage.setScene(gameScene);
            gameController.onContinueSelected();
        });

        Button levelMenuButton = new Button("Level Menu");
        levelMenuButton.setPrefSize(200, 50);
        levelMenuButton.setStyle("-fx-font-size: 18px;");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefSize(200, 50);
        mainMenuButton.setStyle("-fx-font-size: 18px;");
        mainMenuButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(pauseTitle, continueButton, levelMenuButton, mainMenuButton);

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
