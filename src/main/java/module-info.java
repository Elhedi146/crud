module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires jdk.jdi;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.controller;
    opens com.example.app.controller to javafx.fxml;
    exports com.example.app.connect;
    opens com.example.app.connect to javafx.fxml;
    exports com.example.app.dao;
    opens com.example.app.dao to javafx.fxml;


    opens com.example.app.models to javafx.base;


}