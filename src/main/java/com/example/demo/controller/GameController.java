package com.example.demo.controller;

import com.example.demo.levels.LevelParent;
import com.example.demo.menus.LevelMenu;
import com.example.demo.menus.MainMenu;
import com.example.demo.menus.SettingsMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class GameController implements Observer, Controller {

    private final Stage stage;
    private LevelParent currLevel;

    /**
     *
     * @param stage
     */
    private GameController(Stage stage) {
        this.stage = stage;
    }

    /**
     *
     * @param stage
     * @return
     */
    public static GameController createGameController(Stage stage) {
        return new GameController(stage);
    }

    /**
     *
     * @throws SecurityException
     * @throws IllegalArgumentException
     */
    public void launchGame() throws SecurityException,
            IllegalArgumentException {
        showMainMenu();
    }

    /**
     *
     */
    private void showMainMenu() {
        MainMenu mainMenu = new MainMenu(stage, this);
        mainMenu.show();
    }

    /**
     *
     */
    private void showLevelMenu() {
        LevelMenu levelMenu = new LevelMenu(stage, this);
        levelMenu.show();
    }

    /**
     *
     */
    private void showSettingsMenu() {
        SettingsMenu settingsMenu = new SettingsMenu(stage, this);
        settingsMenu.show();
    }

    /**
     *
     * @param className
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(Controller.class, double.class, double.class);
        currLevel = (LevelParent) constructor.newInstance(this, stage.getHeight(), stage.getWidth());
        currLevel.addObserver(this);
        Scene scene = currLevel.initializeScene();
        stage.setScene(scene);
        currLevel.startGame();

    }

    /**
     *
     * @param arg0     the observable object.
     * @param arg1   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        try {
            goToLevel((String) arg1);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getClass().toString());
            alert.show();
        }
    }

    /**
     *
     * @param levelName the name of the selected level.
     */
    public void onLevelSelected(String levelName) {
        try {
            System.out.println("go to level " + levelName);
            goToLevel(levelName);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.getClass().toString());
            alert.show();
        }
    }

    /**
     *
     */
    public void onMainMenuSelected() {
        System.out.println("main menu selected");
        showMainMenu();
    }

    /**
     *
     */
    public void onLevelMenuSelected() {
        showLevelMenu();
    }

    /**
     *
     */
    public void onContinueSelected() {
        currLevel.resumeGame();
    }

    /**
     *
     */
    public void onSettingsMenuSelected() {
        showSettingsMenu();
    }
}
