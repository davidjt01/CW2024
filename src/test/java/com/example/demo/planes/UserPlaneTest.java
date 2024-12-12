package com.example.demo.planes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class UserPlaneTest {
    private UserPlane userPlane;

    @BeforeEach
    public void setUp() {
        userPlane = new UserPlane(5);
    }

    @Test
    public void testInitialization() {
        assertNotNull(userPlane.getImage(), "Image should be loaded");
        assertEquals(5.0, userPlane.getLayoutX(), "X position should be set correctly");
        assertEquals(300.0, userPlane.getLayoutY(), "Y position should be set correctly");
        assertEquals(39, userPlane.getFitHeight(), "Height should be set correctly");
    }

    @Test
    public void testTakeDamage() throws Exception {
        Field healthField = Plane.class.getDeclaredField("health");
        healthField.setAccessible(true);
        int initialHealth = (int) healthField.get(userPlane);

        Method takeDamageMethod = UserPlane.class.getDeclaredMethod("takeDamage");
        takeDamageMethod.setAccessible(true);
        takeDamageMethod.invoke(userPlane);

        assertEquals(initialHealth - 1, healthField.get(userPlane), "Health should decrease by 1 when taking damage");
    }

    @Test
    public void testUpdatePosition() throws Exception {
        Method moveUpMethod = UserPlane.class.getDeclaredMethod("moveUp");
        moveUpMethod.setAccessible(true);
        moveUpMethod.invoke(userPlane);

        Method updatePositionMethod = UserPlane.class.getDeclaredMethod("updatePosition");
        updatePositionMethod.setAccessible(true);
        updatePositionMethod.invoke(userPlane);

        double initialY = userPlane.getLayoutY();
        double translateY = userPlane.getTranslateY();
        assertNotEquals(initialY, userPlane.getLayoutY() + translateY, "Y position should be updated correctly");
    }
}
