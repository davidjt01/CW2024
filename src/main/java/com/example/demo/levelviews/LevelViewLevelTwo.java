package com.example.demo.levelviews;

import com.example.demo.displays.HealthBarDisplay;
import com.example.demo.displays.ShieldDisplay;
import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private static final int HEALTH_BAR_X_POSITION = 1150;
	private static final int HEALTH_BAR_Y_POSITION = 450;
	private final Group root;
	private final ShieldDisplay shieldDisplay;
	private final HealthBarDisplay healthBarDisplay;

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldDisplay = new ShieldDisplay(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		this.healthBarDisplay = new HealthBarDisplay(HEALTH_BAR_X_POSITION, HEALTH_BAR_Y_POSITION);
	}

	private void addImagesToRoot() {
		if (!root.getChildren().contains(shieldDisplay)) {
			root.getChildren().addAll(shieldDisplay);
		}
		if (!root.getChildren().contains(healthBarDisplay.getContainer())) {
			root.getChildren().addAll(healthBarDisplay.getContainer());
		}
	}

	public void showShield() {
		addImagesToRoot();
		shieldDisplay.showShield();
	}

	public void hideShield() {
		shieldDisplay.hideShield();
	}

	public void updateHealthBar(double healthPercentage) {
		addImagesToRoot();
		healthBarDisplay.updateHealth(healthPercentage);
	}

}
