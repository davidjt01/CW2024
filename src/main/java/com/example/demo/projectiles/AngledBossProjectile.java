package com.example.demo.projectiles;

/**
 * The {@code AngledBossProjectile} class represents a projectile fired by boss planes at an angle.
 * It extends {@link BossProjectile} and provides methods for updating its position based on the specified angle.
 */
public class AngledBossProjectile extends BossProjectile {
    private final double angle;

    /**
     * Constructs an {@code AngledBossProjectile} with the specified initial y position and angle.
     *
     * @param initialYPos the initial y-coordinate of the angled boss projectile.
     * @param angle the angle at which the projectile is fired, in degrees.
     */
    public AngledBossProjectile(double initialYPos, double angle) {
        super(initialYPos);
        this.angle = Math.toRadians(angle);
    }

    /**
     * Updates the position of the angled boss projectile.
     * Moves the projectile horizontally and vertically based on its velocity and angle.
     */
    @Override
    public void updatePosition() {
        double dx = Math.cos(angle) * HORIZONTAL_VELOCITY;
        double dy = Math.sin(angle) * HORIZONTAL_VELOCITY;
        super.moveHorizontally(dx);
        super.moveVertically(dy);
    }
}
