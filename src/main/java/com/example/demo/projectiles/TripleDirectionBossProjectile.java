package com.example.demo.projectiles;

public class TripleDirectionBossProjectile extends BossProjectile {

    private static final int NEGATIVE_VERTICAL_VELOCITY = -10;
    private static final int POSITIVE_VERTICAL_VELOCITY = 10;
    private static final int ZERO_VERTICAL_VELOCITY = 0;

    private static int projectileCount = 0;
    private final int verticalVelocity;

    public TripleDirectionBossProjectile(double initialYPos) {
        super(initialYPos);

        // Determine the vertical velocity based on the current projectile count
        switch (projectileCount % 3) {
            case 0:
                verticalVelocity = NEGATIVE_VERTICAL_VELOCITY;
                break;
            case 1:
                verticalVelocity = ZERO_VERTICAL_VELOCITY;
                break;
            case 2:
                verticalVelocity = POSITIVE_VERTICAL_VELOCITY;
                break;
            default:
                verticalVelocity = ZERO_VERTICAL_VELOCITY;
        }

        projectileCount++;
    }

    @Override
    public void updatePosition() {
        // Update both horizontal and vertical positions
        moveHorizontally(HORIZONTAL_VELOCITY);
        moveVertically(verticalVelocity);
    }
}
