package com.example.demo.menus;

import com.example.demo.controller.Controller;
import com.example.demo.displays.GameOverDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOverMenu extends ParentMenu {
    private String levelName;

    public GameOverMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    protected VBox getMenuLayout() {
        GameOverDisplay gameOverDisplay = new GameOverDisplay();
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Button retryButton = new Button("Retry");
        retryButton.setPrefSize(200, 50);
        retryButton.setStyle("-fx-font-size: 18px;");

        System.out.println(levelName);
        retryButton.setOnAction(e -> gameController.onLevelSelected(levelName));

        Button levelMenuButton = createButton("Level Menu");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = createButton("Main Menu");
        mainMenuButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(gameOverDisplay, retryButton, levelMenuButton, mainMenuButton);

        gameOverDisplay.showGameOver();

        return menuLayout;
    }

    @Override
    protected String getBackgroundImageFile() {
        return "/com/example/demo/images/background2.jpg";
    }
}
