package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameOverDisplay extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;

    public GameOverDisplay() {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
    }

    public void showGameOver() {
        this.setVisible(true);
    }
}
