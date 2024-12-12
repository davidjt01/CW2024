package com.example.demo.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class AngledBossProjectileTest extends ApplicationTest {
    private AngledBossProjectile projectile;

    @BeforeEach
    public void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            projectile = new AngledBossProjectile(100, 45); // Initial Y position and angle
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
            double expectedX = 950 + Math.cos(Math.toRadians(45)) * -15;
            double expectedY = 100 + Math.sin(Math.toRadians(45)) * -15;
            assertEquals(expectedX, projectile.getLayoutX() + projectile.getTranslateX(), 0.01, "X position should be updated correctly");
            assertEquals(expectedY, projectile.getLayoutY() + projectile.getTranslateY(), 0.01, "Y position should be updated correctly");
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
