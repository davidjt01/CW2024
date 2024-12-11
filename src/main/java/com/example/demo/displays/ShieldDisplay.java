package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ShieldDisplay extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
    private static final int SHIELD_SIZE = 200;

    private ShieldDisplay(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        //this.setImage(new Image(IMAGE_NAME));
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    public static ShieldDisplay createShieldDisplay(double xPosition, double yPosition) {
        return new ShieldDisplay(xPosition, yPosition);
    }

    public void showShield() {
        this.setVisible(true);
    }

    public void hideShield() {
        this.setVisible(false);
    }

}
