package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private final List<DestructibleEntity> friendlyUnits;
    private final List<DestructibleEntity> enemyUnits;
    private final List<DestructibleEntity> userProjectiles;
    private final List<DestructibleEntity> enemyProjectiles;

    public EntityManager() {
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
    }

    public List<DestructibleEntity> getFriendlyUnits() {
        return friendlyUnits;
    }

    public List<DestructibleEntity> getEnemyUnits() {
        return enemyUnits;
    }

    public List<DestructibleEntity> getUserProjectiles() {
        return userProjectiles;
    }

    public List<DestructibleEntity> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public void addFriendlyUnit(DestructibleEntity entity) {
        friendlyUnits.add(entity);
    }

    public void addEnemyUnit(DestructibleEntity entity) {
        enemyUnits.add(entity);
    }

    public void addUserProjectile(DestructibleEntity projectile) {
        userProjectiles.add(projectile);
    }

    public void addEnemyProjectile(DestructibleEntity projectile) {
        enemyProjectiles.add(projectile);
    }

    public void clearAll() {
        friendlyUnits.clear();
        enemyUnits.clear();
        userProjectiles.clear();
        enemyProjectiles.clear();
    }
}