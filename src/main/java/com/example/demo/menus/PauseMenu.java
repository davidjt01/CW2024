package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text gameTitle = new Text("Pause");
        gameTitle.setFont(new Font("Arial", 48));
        gameTitle.setStyle("-fx-fill: white;");

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

        menuLayout.getChildren().addAll(gameTitle, continueButton, levelMenuButton);

        Scene scene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public void saveGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
}
