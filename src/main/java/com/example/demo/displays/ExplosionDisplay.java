package com.example.demo.displays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

/**
 * The {@code ExplosionDisplay} class represents an explosion effect in the game.
 * It extends {@link ImageView} and displays an explosion image at a specified position.
 * The explosion image is automatically removed after a short duration.
 */
public class ExplosionDisplay extends ImageView {
    private static final String IMAGE_NAME = "/com/example/demo/images/explosion.png";
    private static final int HEIGHT = 200;

    /**
     * Constructs an {@code ExplosionDisplay} at the specified position.
     *
     * @param xPosition the x-coordinate of the explosion.
     * @param yPosition the y-coordinate of the explosion.
     */
    public ExplosionDisplay(double xPosition, double yPosition) {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setFitHeight(HEIGHT);
        this.setPreserveRatio(true);
        this.setVisible(true);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> this.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
