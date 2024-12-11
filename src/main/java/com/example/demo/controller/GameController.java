package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.menus.LevelMenu;
import com.example.demo.menus.MainMenu;
import com.example.demo.menus.PauseMenu;
import com.example.demo.menus.SettingsMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;

public class GameController implements Observer, Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private LevelParent currLevel;

	public GameController(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			showMainMenu();
			//stage.show();
			//goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void showMainMenu() {
		MainMenu mainMenu = new MainMenu(stage, this);
		mainMenu.show();
	}

	private void showLevelMenu() {
		LevelMenu levelMenu = new LevelMenu(stage, this);
		levelMenu.show();
	}

	private void showPauseMenu() {
		PauseMenu pauseMenu = new PauseMenu(stage, this);
		pauseMenu.show();
	}

	private void showSettingsMenu() {
		SettingsMenu settingsMenu = new SettingsMenu(stage, this);
		settingsMenu.show();
	}

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

	public void onLevelSelected(String levelName) {
		try {
			System.out.println("go to level " + levelName);
			goToLevel(levelName);
		}
		catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(ex.getClass().toString());
			alert.show();
		}
	}

	public void onMainMenuSelected() {
		System.out.println("main menu selected");
		showMainMenu();
	}

	public void onLevelMenuSelected() {
		showLevelMenu();
	}

	public void onPauseSelected() {
		showPauseMenu();
	}

	public void onContinueSelected() {
		currLevel.resumeGame();
	}

	public void onSettingsMenuSelected() {
		showSettingsMenu();
	}
}
