package com.example.app;

import com.example.app.connect.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load() ,850, 650);
        //String css  = HelloApplication.class.getResource("hello.css").toExternalForm();
        //scene.getStylesheets().add(css);

        stage.setScene(scene);


        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}