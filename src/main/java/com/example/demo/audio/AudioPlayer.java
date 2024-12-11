package com.example.demo.audio;

import javafx.scene.media.AudioClip;

import java.util.Objects;

public class AudioPlayer {
    public static double getGlobalVolume() {
        return globalVolume;
    }

    private static double globalVolume = 1.0;
    private AudioClip audioClip;

    // Load audio file
    public void loadAudio(String filePath) {
        try {
            audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(filePath)).toExternalForm());
            audioClip.setVolume(globalVolume); // Set the global volume
        } catch (Exception e) {
            System.err.println("Error loading audio: " + e.getMessage());
        }
    }

    // Play audio
    public void play() {
        if (isReady()) {
            audioClip.play();
        }
    }

    // Set global volume for all instances
    public static void setGlobalVolume(double volume) {
        globalVolume = volume;
    }

    // Check if audio clip is ready
    private boolean isReady() {
        return audioClip != null;
    }
}
