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
        double initialY = advancedEnemyPlane.getLayoutY();
        boolean positionUpdated = false;
        for (int i = 0; i < 20; i++) { // Try multiple updates to ensure movement
            advancedEnemyPlane.updatePosition();
            if (advancedEnemyPlane.getLayoutY() + advancedEnemyPlane.getTranslateY() != initialY) {
                positionUpdated = true;
                break;
            }
        }
        assertTrue(positionUpdated, "Y position should be updated correctly");
    }

    @Test
    public void testTakeDamage() {
        advancedEnemyPlane.takeDamage();
        assertTrue(advancedEnemyPlane.isDestroyed(), "Plane should be destroyed after taking damage");
    }
}
