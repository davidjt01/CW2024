package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private static final double SHIELD_X_POSITION_OFFSET = 0.0;
	private static final double SHIELD_Y_POSITION_OFFSET = 0.0;
	private final Group root;
	private final ShieldImage shieldImage;

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
	}

	private void addImagesToRoot() {
		if (!root.getChildren().contains(shieldImage)) {
			root.getChildren().addAll(shieldImage);
		}
	}

	public void showShield() {
		shieldImage.showShield();
		addImagesToRoot();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

	public void updateShieldPosition(double bossX, double bossY) {
		double shieldX = bossX + SHIELD_X_POSITION_OFFSET;
		double shieldY = bossY + SHIELD_Y_POSITION_OFFSET;
		shieldImage.updatePosition(shieldX, shieldY);
	}

}
