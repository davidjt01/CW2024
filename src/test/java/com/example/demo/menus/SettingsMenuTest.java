package com.example.demo.menus;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SettingsMenuTest {

    private Controller controller;
    private SettingsMenu settingsMenu;

    @BeforeEach
    public void setUp() throws Exception {
        Stage stage = mock(Stage.class);
        controller = mock(Controller.class);
        settingsMenu = new SettingsMenu(stage, controller);

        // Use reflection to initialize the volumeText field
        Field volumeTextField = SettingsMenu.class.getDeclaredField("volumeText");
        volumeTextField.setAccessible(true);
        volumeTextField.set(settingsMenu, new Text("Volume: " + (int) (AudioPlayer.getGlobalVolume() * 100) + "%"));
    }

    @Test
    public void testChangeVolume() throws Exception {
        // Arrange
        double initialVolume = 0.5;
        AudioPlayer.setGlobalVolume(initialVolume);
        double delta = 0.1;

        // Use reflection to access the private changeVolume method
        Method changeVolumeMethod = SettingsMenu.class.getDeclaredMethod("changeVolume", double.class);
        changeVolumeMethod.setAccessible(true);

        // Act
        changeVolumeMethod.invoke(settingsMenu, delta);

        // Assert
        double expectedVolume = initialVolume + delta;
        assertEquals(expectedVolume, AudioPlayer.getGlobalVolume(), 0.01);
    }

    @Test
    public void testOnMainMenuSelected() throws Exception {
        // Use reflection to access the private getMenuLayout method
        Method getMenuLayoutMethod = SettingsMenu.class.getDeclaredMethod("getMenuLayout");
        getMenuLayoutMethod.setAccessible(true);

        // Act
        VBox menuLayout = (VBox) getMenuLayoutMethod.invoke(settingsMenu);
        menuLayout.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .filter(button -> "Back".equals(button.getText()))
                .findFirst()
                .ifPresent(Button::fire);

        // Assert
        verify(controller).onMainMenuSelected();
    }
}
