package com.example.demo.audio;

import javafx.scene.media.AudioClip;
import java.util.Objects;

/**
 * The {@code AudioPlayer} class provides methods to manage and play audio clips.
 * It supports setting a global volume that affects all instances of {@code AudioPlayer}.
 */
public class AudioPlayer {
    private static double globalVolume = 1.0;
    private AudioClip audioClip;

    /**
     * Private constructor to prevent direct instantiation.
     * Use {@link #createAudioPlayer()} to create an instance.
     */
    private AudioPlayer() {
    }

    /**
     * Gets the global volume for all audio players.
     *
     * @return the global volume, a value between 0.0 and 1.0.
     */
    public static double getGlobalVolume() {
        return globalVolume;
    }

    /**
     * Sets the global volume for all audio players.
     *
     * @param volume the global volume to set, a value between 0.0 and 1.0.
     */
    public static void setGlobalVolume(double volume) {
        globalVolume = volume;
    }

    /**
     * Creates a new instance of {@code AudioPlayer}.
     *
     * @return a new {@code AudioPlayer} instance.
     */
    public static AudioPlayer createAudioPlayer() {
        return new AudioPlayer();
    }

    /**
     * Loads an audio file from the specified file path.
     *
     * @param filePath the path to the audio file to load.
     */
    public void loadAudio(String filePath) {
        try {
            audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(filePath)).toExternalForm());
            audioClip.setVolume(globalVolume); // Set the global volume
        } catch (Exception e) {
            System.err.println("Error loading audio: " + e.getMessage());
        }
    }

    /**
     * Plays the loaded audio clip.
     * If the audio clip is not loaded, this method does nothing.
     */
    public void play() {
        if (isReady()) {
            audioClip.play();
        }
    }

    /**
     * Checks if the audio clip is ready to be played.
     *
     * @return {@code true} if the audio clip is loaded and ready to play, {@code false} otherwise.
     */
    private boolean isReady() {
        return audioClip != null;
    }
}
