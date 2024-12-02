package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.planes.UserPlane;

import java.util.List;

public class CollisionManager {

    private final UserPlane user;

    public CollisionManager(UserPlane user) {
        this.user = user;
    }

    public void handlePlaneCollisions(List<DestructibleEntity> friendlyUnits, List<DestructibleEntity> enemyUnits) {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    public void handleUserProjectileCollisions(List<DestructibleEntity> userProjectiles, List<DestructibleEntity> enemyUnits) {
        handleCollisions(userProjectiles, enemyUnits);
    }

    public void handleEnemyProjectileCollisions(List<DestructibleEntity> enemyProjectiles, List<DestructibleEntity> friendlyUnits) {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    public void handleEnemyPenetration(List<DestructibleEntity> enemyUnits, double screenWidth) {
        for (DestructibleEntity enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    private void handleCollisions(List<DestructibleEntity> actors1, List<DestructibleEntity> actors2) {
        for (DestructibleEntity actor : actors2) {
            for (DestructibleEntity otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    private boolean enemyHasPenetratedDefenses(DestructibleEntity enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }
}
