package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.images.ExplosionImage;
import com.example.demo.levelui.FinalBossLevelUI;
import com.example.demo.projectiles.AngledBossProjectile;
import com.example.demo.projectiles.BossProjectile;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalBossPlane extends Plane {

    private static final String IMAGE_NAME = "finalbossplane.png";
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = .002;
    private static final int IMAGE_HEIGHT = 80;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 3; // Lowered from 100 for testing
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = 20;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 500;

    private final List<Integer> movePattern;
    private final FinalBossLevelUI levelView;

    private final AudioPlayer fireBallAudio;
    private final AudioPlayer shieldActivateAudio;
    private final AudioPlayer shieldDeactivateAudio;
    private final AudioPlayer explosionAudio;
    private final Group root;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;

    public FinalBossPlane(FinalBossLevelUI levelView, Group root) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        this.levelView = levelView;
        this.root = root;

        movePattern = new ArrayList<>();
        initializeMovePattern();

        fireBallAudio = new AudioPlayer();
        fireBallAudio.loadAudio("/com/example/demo/audio/fireball.wav");

        shieldActivateAudio = new AudioPlayer();
        shieldActivateAudio.loadAudio("/com/example/demo/audio/activateshield.wav");

        shieldDeactivateAudio = new AudioPlayer();
        shieldDeactivateAudio.loadAudio("/com/example/demo/audio/deactivateshield.wav");

        explosionAudio = new AudioPlayer();
        explosionAudio.loadAudio("/com/example/demo/audio/explosion.wav");
    }

    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
        levelView.updateShieldPosition(getLayoutX() + getTranslateX(), getLayoutY() + getTranslateY());
    }

    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
    }

    @Override
    public DestructibleEntity fireProjectile() {
        return null;
    }

    @Override
    public List<DestructibleEntity> fireProjectiles() {
        List<DestructibleEntity> projectiles = new ArrayList<>();
        if (bossFiresInCurrentFrame()) {
            fireBallAudio.play();
            projectiles.add(new BossProjectile(getProjectileInitialPosition()));
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), -20));
            projectiles.add(new AngledBossProjectile(getProjectileInitialPosition(), 20));
        }
        return projectiles;
    }

    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        explosionAudio.play();

        new ExplosionImage(getLayoutX() + getTranslateX(), getLayoutY() + getTranslateY(), root);
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
        if (isShielded)
            framesWithShieldActivated++;
        else if (shieldShouldBeActivated())
            activateShield();
        if (shieldExhausted())
            deactivateShield();
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

    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    private double getProjectileInitialPosition() {
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
        shieldActivateAudio.play();
    }

    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        levelView.hideShield();
        shieldDeactivateAudio.play();
    }
}
