
package com.example.demo.planes;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.EnemyProjectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyPlaneTest {

    private EnemyPlane enemyPlane;

    @BeforeEach
    public void setUp() {
        enemyPlane = new EnemyPlane(100, 100);
    }

    @Test
    public void testInitialization() {
        assertNotNull(enemyPlane.getImage(), "Image should be loaded");
        assertEquals(100, enemyPlane.getLayoutX(), "X position should be set correctly");
        assertEquals(100, enemyPlane.getLayoutY(), "Y position should be set correctly");
        assertEquals(54, enemyPlane.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        double initialX = enemyPlane.getLayoutX();
        enemyPlane.updatePosition();
        assertNotEquals(initialX, enemyPlane.getLayoutX() + enemyPlane.getTranslateX(), "X position should be updated correctly");
    }

    @Test
    public void testFireProjectile() {
        DestructibleEntity projectile = enemyPlane.fireProjectile();
        if (projectile != null) {
            assertInstanceOf(EnemyProjectile.class, projectile, "Projectile should be an instance of EnemyProjectile");
        }
    }

    @Test
    public void testTakeDamage() {
        enemyPlane.takeDamage();
        assertTrue(enemyPlane.isDestroyed(), "Enemy plane should be destroyed after taking damage");
    }
}
