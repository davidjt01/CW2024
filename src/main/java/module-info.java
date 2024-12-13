module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // Required for testing
    requires org.junit.jupiter.api;
    requires org.mockito;
    requires org.testfx.junit5;
    requires org.junit.platform.commons;

    // Exports
    exports com.example.demo.controller;

    // Opens for reflection during tests
    opens com.example.demo.planes to javafx.fxml, org.junit.jupiter.api;
    opens com.example.demo.levels to javafx.fxml, org.junit.jupiter.api;
    opens com.example.demo.displays to javafx.fxml, org.junit.jupiter.api;
    opens com.example.demo.projectiles to javafx.fxml, org.junit.jupiter.api;
    opens com.example.demo.entities to javafx.fxml, org.junit.jupiter.api;
    opens com.example.demo.levelviews to javafx.fxml, org.junit.jupiter.api;
    opens com.example.demo.audio to javafx.fxml, org.junit.jupiter.api;
}
