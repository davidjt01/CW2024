package com.example.demo.levelviews;

import com.example.demo.displays.ShieldDisplay;
import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldDisplay shieldDisplay;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldDisplay = new ShieldDisplay(SHIELD_X_POSITION, SHIELD_Y_POSITION);
	}
	
	private void addImagesToRoot() {
		if (!root.getChildren().contains(shieldDisplay)) {
			root.getChildren().addAll(shieldDisplay);
		}
	}
	
	public void showShield() {
		addImagesToRoot();
		shieldDisplay.showShield();
	}

	public void hideShield() {
		shieldDisplay.hideShield();
	}

}
