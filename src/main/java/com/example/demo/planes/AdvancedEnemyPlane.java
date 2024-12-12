package com.example.demo.planes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code AdvancedEnemyPlane} class represents an advanced enemy plane in the game.
 * It extends {@link EnemyPlane} and provides additional movement patterns.
 */
public class AdvancedEnemyPlane extends EnemyPlane {
    private static final String IMAGE_NAME = "advancedenemyplane.png";
    private static final int HORIZONTAL_VELOCITY = -10;
    private static final int VERTICAL_VELOCITY = 6;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = 10;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private final List<Integer> movePattern;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;

    /**
     * Constructs an {@code AdvancedEnemyPlane} with the specified initial x and y positions.
     *
     * @param initialXPos the initial x-coordinate of the advanced enemy plane.
     * @param initialYPos the initial y-coordinate of the advanced enemy plane.
     */
    public AdvancedEnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, initialXPos, initialYPos);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        initializeMovePattern();
    }

    /**
     * Updates the position of the advanced enemy plane.
     * Moves the plane horizontally and vertically based on its movement pattern.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Initializes the movement pattern for the advanced enemy plane.
     * The pattern includes vertical movements and no movement.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
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
}
