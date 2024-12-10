package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinDisplay extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	public WinDisplay() {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
	}

	public void showWinImage() {
		this.setVisible(true);
	}
}
