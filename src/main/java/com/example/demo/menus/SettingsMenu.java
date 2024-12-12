package com.example.demo.menus;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The {@code SettingsMenu} class represents the settings menu of the game.
 * It extends {@link ParentMenu} and provides options to adjust the game settings, such as volume control.
 */
public class SettingsMenu extends ParentMenu {

    private Text volumeText;

    /**
     * Constructs a {@code SettingsMenu} with the specified stage and game controller.
     *
     * @param stage the primary stage for this application.
     * @param gameController the game controller managing the menu.
     */
    public SettingsMenu(Stage stage, Controller gameController) {
        super(stage, gameController);
    }

    /**
     * Creates and returns the layout for the settings menu.
     *
     * @return the layout for the settings menu.
     */
    @Override
    protected VBox getMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(new Font("Arial", 48));
        settingsTitle.setStyle("-fx-fill: white;");

        HBox volumeControlLayout = new HBox(10);
        volumeControlLayout.setStyle("-fx-alignment: center;");

        Button decreaseVolumeButton = new Button("-");
        decreaseVolumeButton.setPrefSize(50, 50);
        decreaseVolumeButton.setStyle("-fx-font-size: 18px;");
        decreaseVolumeButton.setOnAction(e -> changeVolume(-0.1));

        volumeText = new Text("Volume: " + (int) (AudioPlayer.getGlobalVolume() * 100) + "%");
        volumeText.setFont(new Font("Arial", 24));
        volumeText.setStyle("-fx-fill: white;");

        Button increaseVolumeButton = new Button("+");
        increaseVolumeButton.setPrefSize(50, 50);
        increaseVolumeButton.setStyle("-fx-font-size: 18px;");
        increaseVolumeButton.setOnAction(e -> changeVolume(0.1));

        volumeControlLayout.getChildren().addAll(decreaseVolumeButton, volumeText, increaseVolumeButton);

        Button backButton = createButton("Back");
        backButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(settingsTitle, volumeControlLayout, backButton);
        return menuLayout;
    }

    /**
     * Changes the global volume by the specified delta.
     *
     * @param delta the amount to change the volume by.
     */
    private void changeVolume(double delta) {
        double newVolume = AudioPlayer.getGlobalVolume() + delta;
        newVolume = Math.max(0, Math.min(newVolume, 1)); // Ensure volume is between 0 and 1
        AudioPlayer.setGlobalVolume(newVolume);
        volumeText.setText("Volume: " + (int) (newVolume * 100) + "%");
    }
}
