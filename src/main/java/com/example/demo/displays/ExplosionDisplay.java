package com.example.demo.displays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

/**
 *
 */
public class ExplosionDisplay extends ImageView {
    private static final String IMAGE_NAME = "/com/example/demo/images/explosion.png";
    private static final int HEIGHT = 200; // Adjust size as needed

    /**
     * @param xPosition
     * @param yPosition
     */
    public ExplosionDisplay(double xPosition, double yPosition) {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setFitHeight(HEIGHT);
        this.setPreserveRatio(true);
        this.setVisible(true);

        // Remove the explosion image after one second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> this.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
