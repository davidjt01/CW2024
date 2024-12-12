package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code GameLauncher} class serves as the entry point for the application.
 * It extends {@link Application} and sets up the primary stage for the game.
 */
public class GameLauncher extends Application {

    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";

    /**
     * Default constructor for the {@code GameLauncher} class.
     * This constructor initializes the application entry point.
     */
    public GameLauncher() {
        // Default constructor
    }

    /**
     * The main method which launches the JavaFX application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * @param stage the primary stage for this application.
     * @throws SecurityException if a security violation occurs.
     * @throws IllegalArgumentException if an illegal argument is provided.
     */
    @Override
    public void start(Stage stage) throws SecurityException, IllegalArgumentException {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        GameController myGameController = GameController.createGameController(stage);
        myGameController.launchGame();
    }
}
