package com.example.demo.menus;

import com.example.demo.audio.AudioPlayer;
import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsMenu {
    private final Stage stage;
    private final Controller gameController;
    private Text volumeText;

    public SettingsMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    public void show() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(new Font("Arial", 48));
        settingsTitle.setStyle("-fx-fill: white;");

        Button increaseVolumeButton = new Button("Increase Volume");
        increaseVolumeButton.setPrefSize(200, 50);
        increaseVolumeButton.setStyle("-fx-font-size: 18px;");
        increaseVolumeButton.setOnAction(e -> changeVolume(0.1));

        Button decreaseVolumeButton = new Button("Decrease Volume");
        decreaseVolumeButton.setPrefSize(200, 50);
        decreaseVolumeButton.setStyle("-fx-font-size: 18px;");
        decreaseVolumeButton.setOnAction(e -> changeVolume(-0.1));

        volumeText = new Text("Volume: " + (int) (AudioPlayer.getGlobalVolume() * 100) + "%");
        volumeText.setFont(new Font("Arial", 24));
        volumeText.setStyle("-fx-fill: white;");

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setStyle("-fx-font-size: 18px;");
        backButton.setOnAction(e -> gameController.onMainMenuSelected());

        menuLayout.getChildren().addAll(settingsTitle, increaseVolumeButton, decreaseVolumeButton, volumeText, backButton);

        Scene scene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    private void changeVolume(double delta) {
        double newVolume = AudioPlayer.getGlobalVolume() + delta;
        newVolume = Math.max(0, Math.min(newVolume, 1)); // Ensure volume is between 0 and 1
        AudioPlayer.setGlobalVolume(newVolume);
        volumeText.setText("Volume: " + (int) (newVolume * 100) + "%");
    }
}
