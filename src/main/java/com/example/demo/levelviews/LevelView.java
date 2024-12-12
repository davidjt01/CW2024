package com.example.demo.levelviews;

import com.example.demo.displays.HeartDisplay;
import javafx.scene.Group;

/**
 * The {@code LevelView} class represents the visual components of a game level.
 * It manages the display of hearts indicating the player's remaining lives.
 */
public class LevelView {
    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private final Group root;
    private final HeartDisplay heartDisplay;

    /**
     * Constructs a {@code LevelView} with the specified root group and number of hearts to display.
     *
     * @param root the root group of the scene.
     * @param heartsToDisplay the number of hearts to display.
     */
    LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = HeartDisplay.createHeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
    }

    /**
     * Creates a new instance of {@code LevelView}.
     *
     * @param root the root group of the scene.
     * @param heartsToDisplay the number of hearts to display.
     * @return a new {@code LevelView} instance.
     */
    public static LevelView createLevelView(Group root, int heartsToDisplay) {
        return new LevelView(root, heartsToDisplay);
    }

    public HeartDisplay getHeartDisplay() {
        return heartDisplay;
    }

    /**
     * Displays the heart display on the screen.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Removes hearts from the display based on the remaining hearts.
     *
     * @param heartsRemaining the number of hearts remaining.
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }
}
