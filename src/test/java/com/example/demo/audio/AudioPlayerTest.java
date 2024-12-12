package com.example.demo.audio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AudioPlayerTest {

    private AudioPlayer audioPlayer;

    @BeforeEach
    void setUp() {
        audioPlayer = AudioPlayer.createAudioPlayer();
    }

    @Test
    void testGlobalVolume() {
        AudioPlayer.setGlobalVolume(0.5);
        assertEquals(0.5, AudioPlayer.getGlobalVolume(), "Global volume should be 0.5");
    }

    @Test
    void testLoadAudioWithInvalidPath() {
        audioPlayer.loadAudio("invalid/path");
        assertDoesNotThrow(() -> {
            Method isReadyMethod = AudioPlayer.class.getDeclaredMethod("isReady");
            isReadyMethod.setAccessible(true);
            boolean isReady = (boolean) isReadyMethod.invoke(audioPlayer);
            assertFalse(isReady, "Audio should not be ready if an invalid path is loaded.");
        });
    }

    @Test
    void testPlayWithoutAudioLoaded() {
        assertDoesNotThrow(() -> {
            Method isReadyMethod = AudioPlayer.class.getDeclaredMethod("isReady");
            isReadyMethod.setAccessible(true);
            boolean isReady = (boolean) isReadyMethod.invoke(audioPlayer);
            assertFalse(isReady, "Audio should not be ready if nothing is loaded.");
        });
        audioPlayer.play(); // Should not throw even if no audio is loaded.
    }
}
