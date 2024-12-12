package com.example.demo.planes;

import com.example.demo.levelviews.BossLevelView;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.BossProjectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.Group;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BossPlaneTest {

    private BossPlane bossPlane;

    @BeforeEach
    public void setUp() {
        BossLevelView levelView = BossLevelView.createBossLevelView(new Group(), 5);
        bossPlane = new BossPlane(levelView);
    }

    @Test
    public void testInitialization() {
        assertNotNull(bossPlane.getImage(), "Image should be loaded");
        assertEquals(1000.0, bossPlane.getLayoutX(), "X position should be set correctly");
        assertEquals(400, bossPlane.getLayoutY(), "Y position should be set correctly");
        assertEquals(56, bossPlane.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testUpdatePosition() {
        double initialY = bossPlane.getLayoutY();
        boolean positionUpdated = false;
        for (int i = 0; i < 20; i++) { // Try multiple updates to ensure movement
            bossPlane.updatePosition();
            if (bossPlane.getLayoutY() + bossPlane.getTranslateY() != initialY) {
                positionUpdated = true;
                break;
            }
        }
        assertTrue(positionUpdated, "Y position should be updated correctly");
    }

    @Test
    public void testFireProjectile() {
        DestructibleEntity projectile = bossPlane.fireProjectile();
        if (projectile != null) {
            assertInstanceOf(BossProjectile.class, projectile, "Projectile should be an instance of BossProjectile");
        }
    }

    @Test
    public void testTakeDamage() {
        int initialHealth = bossPlane.getHealth();
        bossPlane.takeDamage();
        if (!isShielded(bossPlane)) {
            assertEquals(initialHealth - 1, bossPlane.getHealth(), "Health should decrease by 1 when taking damage");
        }
    }

    @Test
    public void testActivateShield() throws Exception {
        invokePrivateMethod(bossPlane, "activateShield");
        assertTrue(isShielded(bossPlane), "Shield should be activated");
    }

    @Test
    public void testDeactivateShield() throws Exception {
        invokePrivateMethod(bossPlane, "activateShield");
        invokePrivateMethod(bossPlane, "deactivateShield");
        assertFalse(isShielded(bossPlane), "Shield should be deactivated");
    }

    private boolean isShielded(BossPlane bossPlane) {
        try {
            Field field = BossPlane.class.getDeclaredField("isShielded");
            field.setAccessible(true);
            return field.getBoolean(bossPlane);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void invokePrivateMethod(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(obj);
    }
}
