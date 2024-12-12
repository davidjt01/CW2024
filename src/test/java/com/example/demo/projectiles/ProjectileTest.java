package com.example.demo.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectileTest extends ApplicationTest {
    private Projectile projectile;

    @BeforeEach
    public void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            projectile = new TestProjectile(100, 100);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    public void testInitialization() {
        assertNotNull(projectile.getImage(), "Image should be loaded");
        assertEquals(100, projectile.getLayoutX(), "X position should be set correctly");
        assertEquals(100, projectile.getLayoutY(), "Y position should be set correctly");
        assertEquals(32, projectile.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        Platform.runLater(() -> {
            projectile.updatePosition();
            assertEquals(85, projectile.getLayoutX() + projectile.getTranslateX(), "X position should be updated correctly");
        });
    }

    @Test
    public void testTakeDamage() {
        Platform.runLater(() -> {
            projectile.takeDamage();
            assertTrue(projectile.isDestroyed(), "Projectile should be destroyed after taking damage");
        });
    }

    // A simple subclass of Projectile for testing purposes
    private static class TestProjectile extends Projectile {
        private static final String IMAGE_NAME = "userfire.png";
        private static final int IMAGE_HEIGHT = 32;
        private static final int HORIZONTAL_VELOCITY = -15;

        public TestProjectile(double initialXPos, double initialYPos) {
            super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
        }

        @Override
        public void updatePosition() {
            moveHorizontally(HORIZONTAL_VELOCITY);
        }

        @Override
        public void updateActor() {
            updatePosition();
        }
    }
}
