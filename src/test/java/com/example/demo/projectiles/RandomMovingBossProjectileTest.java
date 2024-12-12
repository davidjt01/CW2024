package com.example.demo.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class RandomMovingBossProjectileTest extends ApplicationTest {
    private RandomMovingBossProjectile projectile;

    @BeforeEach
    public void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            projectile = new RandomMovingBossProjectile(100); // Initial Y position
            latch.countDown();
        });
        latch.await();
    }

    @Test
    public void testInitialization() {
        assertNotNull(projectile.getImage(), "Image should be loaded");
        assertEquals(950, projectile.getLayoutX(), "X position should be set correctly");
        assertEquals(100, projectile.getLayoutY(), "Y position should be set correctly");
        assertEquals(75, projectile.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        Platform.runLater(() -> {
            projectile.updatePosition();
            // Check if the projectile moves correctly
            double expectedX = 950 - 15; // Assuming HORIZONTAL_VELOCITY is -15
            assertEquals(expectedX, projectile.getLayoutX() + projectile.getTranslateX(), 0.01, "X position should be updated correctly");
            // Add more assertions to check vertical movement if needed
        });
    }

    @Test
    public void testTakeDamage() {
        Platform.runLater(() -> {
            projectile.takeDamage();
            assertTrue(projectile.isDestroyed(), "Projectile should be destroyed after taking damage");
        });
    }
}
