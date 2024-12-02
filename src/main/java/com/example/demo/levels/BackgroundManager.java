package com.example.demo.levels;

import com.example.demo.planes.UserPlane;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.Objects;

public class BackgroundManager {

    private final ImageView background;

    public BackgroundManager(String backgroundImageName, double screenHeight, double screenWidth) {
        this.background = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
        initializeBackground(screenHeight, screenWidth);
    }

    private void initializeBackground(double screenHeight, double screenWidth) {
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setFocusTraversable(true);
    }

    public void configureKeyEvents(UserPlane user, Runnable fireProjectileAction) {
        background.setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP)
                user.moveUp();
            if (kc == KeyCode.DOWN)
                user.moveDown();
            if (kc == KeyCode.LEFT)
                user.moveLeft();
            if (kc == KeyCode.RIGHT)
                user.moveRight();
            if (kc == KeyCode.SPACE)
                fireProjectileAction.run();
        });

        background.setOnKeyReleased(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.UP || kc == KeyCode.DOWN)
                user.stopVerticalMovement();
            if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT)
                user.stopHorizontalMovement();
        });
    }

    public ImageView getBackground() {
        return background;
    }
}
