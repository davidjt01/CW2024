package com.example.demo.levels;

import com.example.demo.entities.DestructibleEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

public class GameLoopManager {

    private static final int MILLISECOND_DELAY = 50;
    private final Timeline timeline;
    private final Runnable updateSceneCallback;

    public GameLoopManager(Runnable updateSceneCallback) {
        this.timeline = new Timeline();
        this.updateSceneCallback = updateSceneCallback;
        initializeTimeline();
    }

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateSceneCallback.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    public void startGame() {
        timeline.play();
    }

    public void stopGame() {
        timeline.stop();
    }

    public void clearGameLoop() {
        timeline.getKeyFrames().clear();
    }
}
