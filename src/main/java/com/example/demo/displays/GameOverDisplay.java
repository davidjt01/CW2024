package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code GameOverDisplay} class represents the game over screen in the game.
 * It extends {@link ImageView} and displays a "Game Over" image when the game ends.
 */
public class GameOverDisplay extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;

    /**
     * Constructs a {@code GameOverDisplay} and initializes it with the "Game Over" image.
     * The image is initially invisible.
     */
    public GameOverDisplay() {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
    }

    /**
     * Makes the "Game Over" image visible.
     */
    public void showGameOver() {
        this.setVisible(true);
    }
}
