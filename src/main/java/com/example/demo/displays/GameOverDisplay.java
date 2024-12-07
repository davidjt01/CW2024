package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverDisplay extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	public GameOverDisplay(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()) );
//		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}
