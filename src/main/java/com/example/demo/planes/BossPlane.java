package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.levelviews.BossLevelView;
import com.example.demo.projectiles.BossProjectile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code BossPlane} class represents a boss plane in the game.
 * It extends {@link Plane} and provides additional functionalities such as firing projectiles,
 * activating shields, and updating UI elements.
 */
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
    /**
     * The view associated with the boss level.
     */
    protected final BossLevelView levelView;
    private final List<Integer> movePattern;
    private final AudioPlayer bossFireAudioPlayer;
    private final AudioPlayer shieldAudioPlayer;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;

    /**
     * Constructs a {@code BossPlane} with the specified image name, image height, and level view.
     *
     * @param imageName the name of the image file.
     * @param imageHeight the height of the image.
     * @param levelView the level view associated with the boss plane.
     */
    public BossPlane(String imageName, int imageHeight, BossLevelView levelView) {
        super(imageName, imageHeight, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        this.levelView = levelView;
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();
        bossFireAudioPlayer = AudioPlayer.createAudioPlayer();
        bossFireAudioPlayer.loadAudio("/com/example/demo/audio/bossfire.wav");
        shieldAudioPlayer = AudioPlayer.createAudioPlayer();
        shieldAudioPlayer.loadAudio("/com/example/demo/audio/shield.wav");
    }

    /**
     * Constructs a {@code BossPlane} with the default image name and height, and the specified level view.
     *
     * @param levelView the level view associated with the boss plane.
     */
    public BossPlane(BossLevelView levelView) {
        this(IMAGE_NAME, IMAGE_HEIGHT, levelView);
    }

    /**
     * Updates the position of the boss plane.
     * Moves the plane vertically based on its movement pattern.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Updates the state of the boss plane.
     * This includes updating its position, shield, health bar, and UI elements.
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
        updateHealthBar();
        updateUIElements();
    }

    /**
     * Fires a projectile from the boss plane.
     * Plays the firing audio and returns the fired projectile.
     *
     * @return the fired projectile, or {@code null} if the plane does not fire in the current frame.
     */
    @Override
    public DestructibleEntity fireProjectile() {
        if (bossFiresInCurrentFrame()) {
            bossFireAudioPlayer.play();
            return new BossProjectile(getProjectileInitialPosition());
        }
        return null;
    }

    /**
     * Inflicts damage on the boss plane.
     * If the plane is shielded, it does not take damage.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
            updateHealthBar();
        }
    }

    /**
     * Updates the UI elements associated with the boss plane.
     * This includes the shield and health bar positions.
     */
    protected void updateUIElements() {
        double x = getLayoutX() + getTranslateX();
        double y = getLayoutY() + getTranslateY();
        levelView.setShieldPosition(x + SHIELD_OFFSET_X, y + SHIELD_OFFSET_Y);
        levelView.setHealthBarPosition(x + HEALTH_BAR_OFFSET_X, y + HEALTH_BAR_OFFSET_Y);
    }

    /**
     * Updates the health bar to reflect the current health percentage.
     */
    private void updateHealthBar() {
        double healthPercentage = (double) getHealth() / HEALTH;
        System.out.println(healthPercentage);
        levelView.updateHealthBar(healthPercentage);
    }

    /**
     * Initializes the movement pattern for the boss plane.
     * The pattern includes vertical movements and no movement.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Updates the shield state of the boss plane.
     * Activates or deactivates the shield based on certain conditions.
     */
    private void updateShield() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();
        if (shieldExhausted()) deactivateShield();
    }

    /**
     * Gets the next move in the movement pattern.
     * Shuffles the pattern if the plane has moved in the same direction for too long.
     *
     * @return the next vertical movement value.
     */
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

    /**
     * Checks if the boss plane should fire in the current frame.
     *
     * @return {@code true} if the boss plane should fire, {@code false} otherwise.
     */
    protected boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Gets the initial position for the projectile.
     *
     * @return the initial y-coordinate for the projectile.
     */
    protected double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Checks if the shield should be activated.
     *
     * @return {@code true} if the shield should be activated, {@code false} otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks if the shield is exhausted.
     *
     * @return {@code true} if the shield is exhausted, {@code false} otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the shield for the boss plane.
     * Plays the shield activation audio and updates the UI.
     */
    private void activateShield() {
        isShielded = true;
        shieldAudioPlayer.play();
        levelView.showShield();
        System.out.println("Activated Shield");
    }

    /**
     * Deactivates the shield for the boss plane.
     * Plays the shield deactivation audio and updates the UI.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        shieldAudioPlayer.play();
        levelView.hideShield();
    }
}
