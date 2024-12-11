package com.example.demo.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class Entity extends ImageView {

    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    public Entity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        //this.setImage(new Image(IMAGE_LOCATION + imageName));
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    public abstract void updatePosition();

    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }

}
