module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    //opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.planes to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.displays to javafx.fxml;
    opens com.example.demo.projectiles to javafx.fxml;
    opens com.example.demo.entities to javafx.fxml;
    opens com.example.demo.levelviews to javafx.fxml;
    opens com.example.demo.audio to javafx.fxml;
}
