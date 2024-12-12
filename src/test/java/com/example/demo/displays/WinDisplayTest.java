package com.example.demo.displays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WinDisplayTest {

    private WinDisplay winDisplay;

    @BeforeEach
    public void setUp() {
        winDisplay = new WinDisplay();
    }

    @Test
    public void testInitialization() {
        assertEquals(400, winDisplay.getFitHeight(), "Height should be set correctly");
        assertEquals(400, winDisplay.getFitWidth(), "Width should be set correctly");
        assertFalse(winDisplay.isVisible(), "Win image should be invisible initially");
        assertNotNull(winDisplay.getImage(), "Image should be loaded");
    }

    @Test
    public void testShowWinImage() {
        winDisplay.showWinImage();
        assertTrue(winDisplay.isVisible(), "Win image should be visible after showWinImage is called");
    }
}
