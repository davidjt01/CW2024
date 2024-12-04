package com.example.demo.projectiles;

public class AngledBossProjectile extends BossProjectile {

    private final double angle;

    public AngledBossProjectile(double initialYPos, double angle) {
        super(initialYPos);
        this.angle = Math.toRadians(angle);
    }

    @Override
    public void updatePosition() {
        double dx = Math.cos(angle) * HORIZONTAL_VELOCITY;
        double dy = Math.sin(angle) * HORIZONTAL_VELOCITY;
        super.moveHorizontally(dx);
        super.moveVertically(dy);
        checkIfOffScreen(1300, 750);
    }
}
