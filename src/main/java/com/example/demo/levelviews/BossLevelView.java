package com.example.demo.levelviews;

import com.example.demo.displays.HealthBarDisplay;
import com.example.demo.displays.ShieldDisplay;
import javafx.scene.Group;

/**
 * The {@code BossLevelView} class represents the visual components of a boss level in the game.
 * It extends {@link LevelView} and includes additional displays for the boss's shield and health bar.
 */
public class BossLevelView extends LevelView {
    private final Group root;
    private final ShieldDisplay shieldDisplay;
    private final HealthBarDisplay healthBarDisplay;

    /**
     * Constructs a {@code BossLevelView} with the specified root group and number of hearts to display.
     *
     * @param root the root group of the scene.
     * @param heartsToDisplay the number of hearts to display.
     */
    private BossLevelView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldDisplay = ShieldDisplay.createShieldDisplay(0, 0);
        this.healthBarDisplay = HealthBarDisplay.createHealthBarDisplay(0, 0);
    }

    /**
     * Creates a new instance of {@code BossLevelView}.
     *
     * @param root the root group of the scene.
     * @param heartsToDisplay the number of hearts to display.
     * @return a new {@code BossLevelView} instance.
     */
    public static BossLevelView createBossLevelView(Group root, int heartsToDisplay) {
        return new BossLevelView(root, heartsToDisplay);
    }

    public ShieldDisplay getShieldDisplay() {
        return shieldDisplay;
    }

    public HealthBarDisplay getHealthBarDisplay() {
        return healthBarDisplay;
    }

    /**
     * Adds the shield and health bar images to the root group if they are not already present.
     */
    private void addImagesToRoot() {
        if (!root.getChildren().contains(shieldDisplay)) {
            root.getChildren().addAll(shieldDisplay);
        }
        if (!root.getChildren().contains(healthBarDisplay.getContainer())) {
            root.getChildren().addAll(healthBarDisplay.getContainer());
        }
    }

    /**
     * Displays the shield image.
     */
    public void showShield() {
        addImagesToRoot();
        shieldDisplay.showShield();
    }

    /**
     * Hides the shield image.
     */
    public void hideShield() {
        shieldDisplay.hideShield();
    }

    /**
     * Updates the health bar to reflect the current health percentage.
     *
     * @param healthPercentage the current health percentage, a value between 0.0 and 1.0.
     */
    public void updateHealthBar(double healthPercentage) {
        addImagesToRoot();
        healthBarDisplay.updateHealth(healthPercentage);
    }

    /**
     * Sets the position of the shield image.
     *
     * @param x the x-coordinate of the shield.
     * @param y the y-coordinate of the shield.
     */
    public void setShieldPosition(double x, double y) {
        shieldDisplay.setLayoutX(x);
        shieldDisplay.setLayoutY(y);
    }

    /**
     * Sets the position of the health bar.
     *
     * @param x the x-coordinate of the health bar.
     * @param y the y-coordinate of the health bar.
     */
    public void setHealthBarPosition(double x, double y) {
        healthBarDisplay.getContainer().setLayoutX(x);
        healthBarDisplay.getContainer().setLayoutY(y);
    }
}
