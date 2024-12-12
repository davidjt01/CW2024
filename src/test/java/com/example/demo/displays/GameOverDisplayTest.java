package com.example.demo.displays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameOverDisplayTest {

    private GameOverDisplay gameOverDisplay;

    @BeforeEach
    public void setUp() {
        gameOverDisplay = new GameOverDisplay();
    }

    @Test
    public void testInitialization() {
        assertNotNull(gameOverDisplay.getImage(), "Image should be loaded");
        assertEquals(400, gameOverDisplay.getFitHeight(), "Height should be set correctly");
        assertEquals(400, gameOverDisplay.getFitWidth(), "Width should be set correctly");
        assertFalse(gameOverDisplay.isVisible(), "Game Over display should be invisible initially");
    }

    @Test
    public void testShowGameOver() {
        gameOverDisplay.showGameOver();
        assertTrue(gameOverDisplay.isVisible(), "Game Over display should be visible after showGameOver is called");
    }
}