package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PauseMenu extends BaseMenu {
    private Scene gameScene;
    private WritableImage backgroundImage;

    public PauseMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    public void saveGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setBackgroundImage(WritableImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected Image getImage() {
        return backgroundImage;
    }

    protected VBox getMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

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

        return menuLayout;
    }
}
