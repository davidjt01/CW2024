package com.example.demo.menus;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public abstract class ParentMenu {
    protected Stage stage;
    protected Controller gameController;

    public ParentMenu(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }


    public void show() {
        VBox menuLayout = getMenuLayout();
        menuLayout.setBackground(getBackground(stage));
        Scene scene = new Scene(menuLayout, stage.getWidth(), stage.getHeight());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    protected Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setPrefSize(200, 50);
        button.setStyle("-fx-font-size: 18px;");
        return button;
    }

    protected Background getBackground(Stage stage) {
        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(), stage.getHeight(), false, false, false, false);
        BackgroundImage bgImage = new BackgroundImage(getImage(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        return new Background(bgImage);
    }

    protected Image getImage() {
        return new Image(Objects.requireNonNull(getClass().getResource(getBackgroundImageFile())).toExternalForm());
    }

    protected String getBackgroundImageFile() {
        return "/com/example/demo/images/background1.jpg";
    }

    abstract VBox getMenuLayout();
}
