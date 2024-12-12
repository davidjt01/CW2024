package com.example.demo.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyProjectileTest extends ApplicationTest {
    private EnemyProjectile enemyProjectile;

    @BeforeEach
    public void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            enemyProjectile = new EnemyProjectile(100, 100); // Initial X and Y positions
            latch.countDown();
        });
        latch.await();
    }

    @Test
    public void testInitialization() {
        assertNotNull(enemyProjectile.getImage(), "Image should be loaded");
        assertEquals(100, enemyProjectile.getLayoutX(), "X position should be set correctly");
        assertEquals(100, enemyProjectile.getLayoutY(), "Y position should be set correctly");
        assertEquals(32, enemyProjectile.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        Platform.runLater(() -> {
            enemyProjectile.updatePosition();
            assertEquals(90, enemyProjectile.getLayoutX() + enemyProjectile.getTranslateX(), "X position should be updated correctly");
        });
    }

    @Test
    public void testTakeDamage() {
        Platform.runLater(() -> {
            enemyProjectile.takeDamage();
            assertTrue(enemyProjectile.isDestroyed(), "Projectile should be destroyed after taking damage");
        });
    }
}
