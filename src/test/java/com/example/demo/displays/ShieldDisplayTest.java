package com.example.demo.displays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShieldDisplayTest {

    private ShieldDisplay shieldDisplay;

    @BeforeEach
    public void setUp() {
        shieldDisplay = ShieldDisplay.createShieldDisplay(100, 100);
    }

    @Test
    public void testInitialization() {
        assertEquals(100, shieldDisplay.getLayoutX(), "X position should be set correctly");
        assertEquals(100, shieldDisplay.getLayoutY(), "Y position should be set correctly");
        assertEquals(200, shieldDisplay.getFitHeight(), "Height should be set correctly");
        assertEquals(200, shieldDisplay.getFitWidth(), "Width should be set correctly");
        assertFalse(shieldDisplay.isVisible(), "Shield should be invisible initially");
        assertNotNull(shieldDisplay.getImage(), "Image should be loaded");
    }

    @Test
    public void testShowShield() {
        shieldDisplay.showShield();
        assertTrue(shieldDisplay.isVisible(), "Shield should be visible after showShield is called");
    }

    @Test
    public void testHideShield() {
        shieldDisplay.showShield();
        shieldDisplay.hideShield();
        assertFalse(shieldDisplay.isVisible(), "Shield should be invisible after hideShield is called");
    }
}
