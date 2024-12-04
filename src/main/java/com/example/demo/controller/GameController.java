package com.example.demo.controller;

import com.example.demo.levels.LevelParent;
import com.example.demo.menus.LevelSelectionMenu;
import com.example.demo.menus.MainMenu;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class GameController {

    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";
    private final Stage stage;

    public GameController(Stage stage) {
        this.stage = stage;
    }

    public void showMainMenu() {
        MainMenu mainMenu = new MainMenu(stage);
        Scene mainMenuScene = mainMenu.createMainMenuScene(this);
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public void showLevelSelectionMenu() {
        LevelSelectionMenu levelSelectionMenu = new LevelSelectionMenu(stage, this);
        Scene levelSelectionScene = levelSelectionMenu.createLevelSelectionScene();
        stage.setScene(levelSelectionScene);
        stage.show();
    }

    public void goToLevel(String className) throws Exception {
        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
        myLevel.addObserver((o, arg) -> {
            try {
                goToLevel((String) arg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Scene scene = myLevel.initializeScene();
        stage.setScene(scene);
        myLevel.startGame();
    }
}
