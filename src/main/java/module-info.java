module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive javafx.graphics;

    exports com.example.demo.controller;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.images to javafx.fxml;
    opens com.example.demo.levelui to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;
    opens com.example.demo.entities to javafx.fxml;
    opens com.example.demo.audio to javafx.fxml;
    opens com.example.demo.projectiles to javafx.fxml;
    opens com.example.demo.planes to javafx.fxml;
    exports com.example.demo.menus;
    opens com.example.demo.menus to javafx.fxml;
}