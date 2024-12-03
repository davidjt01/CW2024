package com.example.demo.images;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Objects;

public class ExplosionImage extends ImageView {
    private static final String IMAGE_NAME = "/com/example/demo/images/explosion.png";
    private static final int IMAGE_HEIGHT = 150;

    public ExplosionImage(double xPosition, double yPosition, Group root) {
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setLayoutX(xPosition);
        setLayoutY(yPosition);
        setFitHeight(IMAGE_HEIGHT);
        setPreserveRatio(true);

        root.getChildren().add(this);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> root.getChildren().remove(this)));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
