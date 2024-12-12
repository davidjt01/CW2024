package com.example.demo.planes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedEnemyPlaneTest {

    private AdvancedEnemyPlane advancedEnemyPlane;

    @BeforeEach
    public void setUp() {
        advancedEnemyPlane = new AdvancedEnemyPlane(100, 100);
    }

    @Test
    public void testInitialization() {
        assertNotNull(advancedEnemyPlane.getImage(), "Image should be loaded");
        assertEquals(100, advancedEnemyPlane.getLayoutX(), "X position should be set correctly");
        assertEquals(100, advancedEnemyPlane.getLayoutY(), "Y position should be set correctly");
        assertEquals(54, advancedEnemyPlane.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        double initialX = advancedEnemyPlane.getLayoutX();
        double initialY = advancedEnemyPlane.getLayoutY();
        advancedEnemyPlane.updatePosition();
        assertNotEquals(initialX, advancedEnemyPlane.getLayoutX() + advancedEnemyPlane.getTranslateX(), "X position should be updated correctly");
        assertNotEquals(initialY, advancedEnemyPlane.getLayoutY() + advancedEnemyPlane.getTranslateY(), "Y position should be updated correctly");
    }

    @Test
    public void testTakeDamage() {
        advancedEnemyPlane.takeDamage();
        assertTrue(advancedEnemyPlane.isDestroyed(), "Plane should be destroyed after taking damage");
    }
}
