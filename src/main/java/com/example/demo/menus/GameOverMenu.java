package com.example.demo.menus;

import com.example.demo.controller.Controller;
import com.example.demo.displays.GameOverDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code GameOverMenu} class represents the game over menu displayed when the game ends.
 * It extends {@link ParentMenu} and provides options to retry the level, go to the level menu, or return to the main menu.
 */
public class GameOverMenu extends ParentMenu {
    private String levelName;

    /**
     * Constructs a {@code GameOverMenu} with the specified stage and game controller.
     *
     * @param stage the primary stage for this application.
     * @param gameController the game controller managing the menu.
     */
    public GameOverMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    /**
     * Sets the name of the level to retry.
     *
     * @param levelName the name of the level to retry.
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Creates and returns the layout for the game over menu.
     *
     * @return the layout for the game over menu.
     */
    @Override
    protected VBox getMenuLayout() {
        GameOverDisplay gameOverDisplay = new GameOverDisplay();
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Button retryButton = new Button("Retry");
        retryButton.setPrefSize(200, 50);
        retryButton.setStyle("-fx-font-size: 18px;");
        retryButton.setOnAction(e -> gameController.onLevelSelected(levelName));

        Button levelMenuButton = createButton("Level Menu");
        levelMenuButton.setOnAction(e -> gameController.onLevelMenuSelected());

        Button mainMenuButton = createButton("Main Menu");
        mainMenuButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(gameOverDisplay, retryButton, levelMenuButton, mainMenuButton);
        gameOverDisplay.showGameOver();

        return menuLayout;
    }

    /**
     * Returns the file path for the background image of the game over menu.
     *
     * @return the file path for the background image.
     */
    @Override
    protected String getBackgroundImageFile() {
        return "/com/example/demo/images/background2.jpg";
    }
}
