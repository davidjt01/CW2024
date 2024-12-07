package com.example.demo.projectiles;

import com.example.demo.entities.DestructibleEntity;

public abstract class Projectile extends DestructibleEntity {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

}
