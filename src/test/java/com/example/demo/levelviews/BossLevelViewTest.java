package com.example.demo.levelviews;

import com.example.demo.displays.HealthBarDisplay;
import com.example.demo.displays.ShieldDisplay;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BossLevelViewTest {
    private BossLevelView bossLevelView;

    @BeforeEach
    public void setUp() {
        Group root = new Group();
        bossLevelView = BossLevelView.createBossLevelView(root, 5);
    }

    @Test
    public void testInitialization() {
        assertNotNull(bossLevelView, "BossLevelView should be initialized");
        assertNotNull(bossLevelView.getHeartDisplay(), "HeartDisplay should be initialized");
        assertEquals(5, bossLevelView.getHeartDisplay().getContainer().getChildren().size(), "HeartDisplay should contain 5 hearts");
    }

    @Test
    public void testShowShield() {
        bossLevelView.showShield();
        ShieldDisplay shieldDisplay = bossLevelView.getShieldDisplay();
        assertTrue(shieldDisplay.isVisible(), "Shield should be visible after showShield is called");
    }

    @Test
    public void testHideShield() {
        bossLevelView.showShield();
        bossLevelView.hideShield();
        ShieldDisplay shieldDisplay = bossLevelView.getShieldDisplay();
        assertFalse(shieldDisplay.isVisible(), "Shield should be invisible after hideShield is called");
    }

    @Test
    public void testUpdateHealthBar() {
        bossLevelView.updateHealthBar(0.5);
        HealthBarDisplay healthBarDisplay = bossLevelView.getHealthBarDisplay();
        assertEquals(0.5, healthBarDisplay.getProgress(), "Health bar should be updated to 0.5");
    }

    @Test
    public void testSetShieldPosition() {
        bossLevelView.setShieldPosition(100, 200);
        ShieldDisplay shieldDisplay = bossLevelView.getShieldDisplay();
        assertEquals(100, shieldDisplay.getLayoutX(), "Shield X position should be set correctly");
        assertEquals(200, shieldDisplay.getLayoutY(), "Shield Y position should be set correctly");
    }

    @Test
    public void testSetHealthBarPosition() {
        bossLevelView.setHealthBarPosition(100, 200);
        HealthBarDisplay healthBarDisplay = bossLevelView.getHealthBarDisplay();
        HBox container = healthBarDisplay.getContainer();
        assertEquals(100, container.getLayoutX(), "Health bar X position should be set correctly");
        assertEquals(200, container.getLayoutY(), "Health bar Y position should be set correctly");
    }
}
