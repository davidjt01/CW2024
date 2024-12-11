package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code WinDisplay} class represents the win screen in the game.
 * It extends {@link ImageView} and displays a "You Win" image when the player wins the game.
 */
public class WinDisplay extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;

    /**
     * Constructs a {@code WinDisplay} and initializes it with the "You Win" image.
     * The image is initially invisible.
     */
    public WinDisplay() {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
    }

    /**
     * Makes the "You Win" image visible.
     */
    public void showWinImage() {
        this.setVisible(true);
    }
}
