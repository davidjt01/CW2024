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
 * The {@code GameController} class implements the {@link Controller} interface and {@link Observer} interface.
 * It manages the game flow, including level transitions, menu displays, and game settings.
 */
public class GameController implements Observer, Controller {

    private final Stage stage;
    private LevelParent currLevel;

    /**
     * Private constructor to prevent direct instantiation.
     * Use {@link #createGameController(Stage)} to create an instance.
     *
     * @param stage the primary stage for this application.
     */
    private GameController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a new instance of {@code GameController}.
     *
     * @param stage the primary stage for this application.
     * @return a new {@code GameController} instance.
     */
    public static GameController createGameController(Stage stage) {
        return new GameController(stage);
    }

    /**
     * Launches the game by displaying the main menu.
     *
     * @throws SecurityException if a security violation occurs.
     * @throws IllegalArgumentException if an illegal argument is provided.
     */
    public void launchGame() throws SecurityException, IllegalArgumentException {
        showMainMenu();
    }

    /**
     * Displays the main menu.
     */
    private void showMainMenu() {
        MainMenu mainMenu = new MainMenu(stage, this);
        mainMenu.show();
    }

    /**
     * Displays the level menu.
     */
    private void showLevelMenu() {
        LevelMenu levelMenu = new LevelMenu(stage, this);
        levelMenu.show();
    }

    /**
     * Displays the settings menu.
     */
    private void showSettingsMenu() {
        SettingsMenu settingsMenu = new SettingsMenu(stage, this);
        settingsMenu.show();
    }

    /**
     * Transitions to the specified level.
     *
     * @param className the name of the level class to transition to.
     * @throws ClassNotFoundException if the class cannot be located.
     * @throws NoSuchMethodException if a matching method is not found.
     * @throws SecurityException if a security violation occurs.
     * @throws InstantiationException if the class cannot be instantiated.
     * @throws IllegalAccessException if the class or its nullary constructor is not accessible.
     * @throws IllegalArgumentException if the method is invoked with incorrect arguments.
     * @throws InvocationTargetException if the underlying constructor throws an exception.
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
     * Updates the game state based on the observable's notification.
     *
     * @param arg0 the observable object.
     * @param arg1 an argument passed to the {@code notifyObservers} method.
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
     * Handles the event when a level is selected.
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
     * Handles the event when the main menu is selected.
     */
    public void onMainMenuSelected() {
        System.out.println("main menu selected");
        showMainMenu();
    }

    /**
     * Handles the event when the level menu is selected.
     */
    public void onLevelMenuSelected() {
        showLevelMenu();
    }

    /**
     * Handles the event when the continue option is selected.
     */
    public void onContinueSelected() {
        currLevel.resumeGame();
    }

    /**
     * Handles the event when the settings menu is selected.
     */
    public void onSettingsMenuSelected() {
        showSettingsMenu();
    }
}
