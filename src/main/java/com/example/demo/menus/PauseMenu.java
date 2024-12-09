package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class PauseMenu {
    private final Stage stage;
    private final Controller gameController;
    private Scene gameScene;

    public PauseMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    public void show() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50; -fx-background-color: black;");
        //menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text gameTitle = new Text("Pause");
        gameTitle.setFont(new Font("Arial", 48));
        gameTitle.setStyle("-fx-fill: white;");

        Button playButton = new Button("Continue");
        playButton.setPrefSize(200, 50);
        playButton.setStyle("-fx-font-size: 18px;");

        playButton.setOnAction(e -> {
            stage.setScene(gameScene);
            gameController.onContinueSelected();
        });

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 18px;");

        backButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(gameTitle, playButton, backButton);

        Scene scene =new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    };

    public void saveGameScene(Scene gameScene) {
        this.gameScene = gameScene;

    }
}
