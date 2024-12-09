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

public class WinMenu {
    private final Stage stage;
    private final Controller gameController;

    public WinMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    public void show() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text gameTitle = new Text("Win");
        gameTitle.setFont(new Font("Arial", 48));
        gameTitle.setStyle("-fx-fill: white;");

        Button playButton = new Button("Level Menu");
        playButton.setPrefSize(200, 50);
        playButton.setStyle("-fx-font-size: 18px;");

        playButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button quitButton = new Button("Main Menu");
        quitButton.setPrefSize(200, 50);
        quitButton.setStyle("-fx-font-size: 18px;");

        quitButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(gameTitle, playButton, quitButton);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/background1.jpg")).toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        menuLayout.setBackground(new Background(bgImage));

        Scene scene =  new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }
}
