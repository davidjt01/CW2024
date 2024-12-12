package com.example.demo.planes;

import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.RandomMovingBossProjectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecondBossPlaneTest {

    private SecondBossPlane secondBossPlane;
    private BossLevelView mockLevelView;

    @BeforeEach
    public void setUp() {
        mockLevelView = Mockito.mock(BossLevelView.class);
        secondBossPlane = new SecondBossPlane(mockLevelView);
    }

    @Test
    public void testInitialization() {
        assertNotNull(secondBossPlane.getImage(), "Image should be loaded");
        assertEquals(1000.0, secondBossPlane.getLayoutX(), "X position should be set correctly");
        assertEquals(400, secondBossPlane.getLayoutY(), "Y position should be set correctly");
        assertEquals(70, secondBossPlane.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testFireProjectile() {
        RandomMovingBossProjectile projectile = (RandomMovingBossProjectile) secondBossPlane.fireProjectile();
        if (projectile != null) {
            assertInstanceOf(RandomMovingBossProjectile.class, projectile, "Projectile should be an instance of RandomMovingBossProjectile");
        }
    }

    @Test
    public void testTakeDamage() {
        int initialHealth = secondBossPlane.getHealth();
        secondBossPlane.takeDamage();
        assertEquals(initialHealth - 1, secondBossPlane.getHealth(), "Health should decrease by 1 when taking damage");
    }

    @Test
    public void testUpdateUIElements() {
        secondBossPlane.updateActor();
        verify(mockLevelView).setShieldPosition(anyDouble(), anyDouble());
        verify(mockLevelView).setHealthBarPosition(anyDouble(), anyDouble());
    }
}
