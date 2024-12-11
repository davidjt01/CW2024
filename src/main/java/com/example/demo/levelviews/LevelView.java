package com.example.demo.levelviews;

import com.example.demo.displays.HeartDisplay;
import javafx.scene.Group;

public class LevelView {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private final Group root;
    private final HeartDisplay heartDisplay;

    LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = HeartDisplay.createHeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
    }

    public static LevelView createLevelView(Group root, int heartsToDisplay) {
        return new LevelView(root, heartsToDisplay);
    }

    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

}
