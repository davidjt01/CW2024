package com.example.demo.levelviews;

import com.example.demo.displays.HealthBarDisplay;
import com.example.demo.displays.ShieldDisplay;
import javafx.scene.Group;

public class BossLevelView extends LevelView {

    private final Group root;
    private final ShieldDisplay shieldDisplay;
    private final HealthBarDisplay healthBarDisplay;

    public BossLevelView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldDisplay = new ShieldDisplay(0, 0);
        this.healthBarDisplay = HealthBarDisplay.createHealthBarDisplay(0, 0);
    }

    private void addImagesToRoot() {
        if (!root.getChildren().contains(shieldDisplay)) {
            root.getChildren().addAll(shieldDisplay);
        }
        if (!root.getChildren().contains(healthBarDisplay.getContainer())) {
            root.getChildren().addAll(healthBarDisplay.getContainer());
        }
    }

    public void showShield() {
        addImagesToRoot();
        shieldDisplay.showShield();
    }

    public void hideShield() {
        shieldDisplay.hideShield();
    }

    public void updateHealthBar(double healthPercentage) {
        addImagesToRoot();
        healthBarDisplay.updateHealth(healthPercentage);
    }

    public void setShieldPosition(double x, double y) {
        shieldDisplay.setLayoutX(x);
        shieldDisplay.setLayoutY(y);
    }

    public void setHealthBarPosition(double x, double y) {
        healthBarDisplay.getContainer().setLayoutX(x);
        healthBarDisplay.getContainer().setLayoutY(y);
    }
}
