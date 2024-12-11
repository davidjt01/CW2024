package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.BossProjectile;
import com.example.demo.levelviews.BossLevelView;

import java.util.*;

public class BossPlane extends Plane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = .01;
	private static final int IMAGE_HEIGHT = 56;
	private static final int VERTICAL_VELOCITY = 8;
	private static final double SHIELD_OFFSET_X = 40.0;
	private static final double SHIELD_OFFSET_Y = -60.0;
	private static final double HEALTH_BAR_OFFSET_X = 35.0;
	private static final double HEALTH_BAR_OFFSET_Y = -40.0;
	private static final int HEALTH = 10;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = 10;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 50;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	protected final BossLevelView levelView;

	private AudioPlayer bossFireAudioPlayer;

	public BossPlane(String imageName, int imageHeight, BossLevelView levelView) {
		super(imageName, imageHeight, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.levelView = levelView;
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
		bossFireAudioPlayer = new AudioPlayer();
		bossFireAudioPlayer.loadAudio("/com/example/demo/audio/bossfire.wav");
	}

	public BossPlane(BossLevelView levelView) {
		this(IMAGE_NAME, IMAGE_HEIGHT, levelView);
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		updateHealthBar();
		updateUIElements();
	}

	@Override
	public DestructibleEntity fireProjectile() {
		if (bossFiresInCurrentFrame()) {
			bossFireAudioPlayer.play();
			return new BossProjectile(getProjectileInitialPosition());
		}
		return null;
	}

	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
			updateHealthBar();
		}
	}

	protected void updateUIElements() {
		double x = getLayoutX() + getTranslateX();
		double y = getLayoutY() + getTranslateY();
		levelView.setShieldPosition(x + SHIELD_OFFSET_X, y + SHIELD_OFFSET_Y);
		levelView.setHealthBarPosition(x + HEALTH_BAR_OFFSET_X, y + HEALTH_BAR_OFFSET_Y);
	}

	private void updateHealthBar() {
		double healthPercentage = (double) getHealth() / HEALTH;
		System.out.println(healthPercentage);
		levelView.updateHealthBar(healthPercentage);
	}

	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	private void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) activateShield();
		if (shieldExhausted()) deactivateShield();
	}

	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	protected boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	protected double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	private void activateShield() {
		isShielded = true;
		levelView.showShield();
		System.out.println("Activated Shield");
	}

	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		levelView.hideShield();
	}
}
