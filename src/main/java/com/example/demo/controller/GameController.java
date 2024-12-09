package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.menus.LevelMenu;
import com.example.demo.menus.MainMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;

public class GameController implements Observer, Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;

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
		Scene mainMenuScene = mainMenu.createMainMenuScene();
		stage.setScene(mainMenuScene);
		stage.show();
	}

	private void showLevelMenu() {
		LevelMenu levelMenu = new LevelMenu(stage, this);
		Scene levelMenuScene = levelMenu.createLevelSelectionScene();
		stage.setScene(levelMenuScene);
		stage.show();
	}


	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();

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
			goToLevel(levelName);
		}
		catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(ex.getClass().toString());
			alert.show();
		}
	}

	public void onMainMenuSelected() {
		showMainMenu();
	}

	public void onPlaySelected() {
		showLevelMenu();
	}

}
