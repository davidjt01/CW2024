package com.example.demo.planes;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.projectiles.UserProjectile;

public class UserPlane extends Plane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = 10;
    private static final double Y_LOWER_BOUND = 660.0;
    private static final double X_LEFT_BOUND = 0.0;
    private static final double X_RIGHT_BOUND = 800.0; // to be adjusted later
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 39;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 8;
    private static final int PROJECTILE_X_POSITION_OFFSET = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private int verticalVelocityMultiplier;
    private int horizontalVelocityMultiplier;
    private int numberOfKills;

    private final AudioPlayer fireAudioPlayer;
    private final AudioPlayer damageAudioPlayer;

    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
        fireAudioPlayer = new AudioPlayer();
        fireAudioPlayer.loadAudio("/com/example/demo/audio/userfire.wav");
        damageAudioPlayer = new AudioPlayer();
        damageAudioPlayer.loadAudio("/com/example/demo/audio/usertakedamage.wav");
    }

    @Override
    public void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            double initialTranslateX = getTranslateX();
            this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
            this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newPositionY = getLayoutY() + getTranslateY();
            double newPositionX = getLayoutX() + getTranslateX();
            if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
            if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
                this.setTranslateX(initialTranslateX);
            }
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public DestructibleEntity fireProjectile() {
        fireAudioPlayer.play();
        return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    @Override
    public void takeDamage() {
        super.takeDamage();
        damageAudioPlayer.play();
    }

    private boolean isMovingVertically() {
        return verticalVelocityMultiplier != 0;
    }

    private boolean isMovingHorizontally() {
        return horizontalVelocityMultiplier != 0;
    }

    private boolean isMoving() {
        return isMovingVertically() || isMovingHorizontally();
    }

    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }

    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    public void stopVerticalVelocity() {
        verticalVelocityMultiplier = 0;
    }

    public void stopHorizontalVelocity() {
        horizontalVelocityMultiplier = 0;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
        numberOfKills++;
    }
}
