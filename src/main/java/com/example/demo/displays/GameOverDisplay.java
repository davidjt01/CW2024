package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverDisplay extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	public GameOverDisplay() {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
	}

	public void showGameOver() {
		this.setVisible(true);
	}
}
