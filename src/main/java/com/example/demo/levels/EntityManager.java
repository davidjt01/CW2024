package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private final List<DestructibleEntity> friendlyUnits;
    private final List<DestructibleEntity> enemyUnits;
    private final List<DestructibleEntity> userProjectiles;
    private final List<DestructibleEntity> enemyProjectiles;
    private final Group root;

    public EntityManager(Group root) {
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.root = root;
    }

    public void addFriendlyUnit(DestructibleEntity unit) {
        friendlyUnits.add(unit);
        root.getChildren().add(unit);
    }

    public void addEnemyUnit(DestructibleEntity unit) {
        enemyUnits.add(unit);
        root.getChildren().add(unit);
    }

    public void addUserProjectile(DestructibleEntity projectile) {
        userProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    public void addEnemyProjectile(DestructibleEntity projectile) {
        enemyProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    public void updateActors() {
        friendlyUnits.forEach(DestructibleEntity::updateActor);
        enemyUnits.forEach(DestructibleEntity::updateActor);
        userProjectiles.forEach(DestructibleEntity::updateActor);
        enemyProjectiles.forEach(DestructibleEntity::updateActor);
    }

    public void removeDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<DestructibleEntity> actors) {
        List<DestructibleEntity> destroyedActors = actors.stream()
                .filter(DestructibleEntity::isDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
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
}
