package com.example.demo.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class ExplosionImage extends ImageView {
    private static final String IMAGE_NAME = "/com/example/demo/images/explosion.png";
    private static final int IMAGE_HEIGHT = 100;

    public ExplosionImage(double xPosition, double yPosition) {
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setLayoutX(xPosition);
        setLayoutY(yPosition);
        setFitHeight(IMAGE_HEIGHT);
        setPreserveRatio(true);
    }
}
