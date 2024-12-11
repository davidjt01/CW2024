package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncher extends Application {

    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws SecurityException,
            IllegalArgumentException {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        GameController myGameController = GameController.createGameController(stage);
        myGameController.launchGame();
    }
}
