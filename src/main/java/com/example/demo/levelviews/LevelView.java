package com.example.demo.levelviews;

import com.example.demo.displays.GameOverDisplay;
import com.example.demo.displays.HeartDisplay;
import com.example.demo.displays.WinDisplay;
import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSITION = -375;
	private final Group root;
	private final WinDisplay winDisplay;
	//private final GameOverDisplay gameOverDisplay;
	private final HeartDisplay heartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winDisplay = new WinDisplay(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		//this.gameOverDisplay = new GameOverDisplay(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winDisplay);
		winDisplay.showWinImage();
	}
	
	public void showGameOverImage() {
		//root.getChildren().add(gameOverDisplay);
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
