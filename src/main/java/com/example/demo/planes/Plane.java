package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;

import java.util.List;

public abstract class Plane extends DestructibleEntity {

	private int health;
	private AudioPlayer explosionAudioPlayer;

	public Plane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
		explosionAudioPlayer = new AudioPlayer();
		explosionAudioPlayer.loadAudio("/com/example/demo/audio/explosion.wav");
	}

	public abstract DestructibleEntity fireProjectile();

	public List<DestructibleEntity> fireProjectiles() {
		DestructibleEntity projectile = fireProjectile();
		if (projectile != null) {
			return List.of(projectile);
		}
		return List.of();
	}

	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		explosionAudioPlayer.play();
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}
}
