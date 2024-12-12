package com.example.demo.projectiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProjectileTest {

    private UserProjectile userProjectile;

    @BeforeEach
    public void setUp() {
        userProjectile = new UserProjectile(100, 100);
    }

    @Test
    public void testInitialization() {
        assertNotNull(userProjectile.getImage(), "Image should be loaded");
        assertEquals(100, userProjectile.getLayoutX(), "X position should be set correctly");
        assertEquals(100, userProjectile.getLayoutY(), "Y position should be set correctly");
        assertEquals(6, userProjectile.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        double initialX = userProjectile.getLayoutX();
        userProjectile.updatePosition();
        assertEquals(initialX + 15, userProjectile.getLayoutX() + userProjectile.getTranslateX(), "X position should be updated correctly");
    }

    @Test
    public void testTakeDamage() {
        userProjectile.takeDamage();
        assertTrue(userProjectile.isDestroyed(), "Projectile should be destroyed after taking damage");
    }
}
