package com.example.partsinventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("intro-pane.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 546, 365);
        stage.setTitle("Hello!");
        //Image icon=new Image("C:\\code\\desktop application\\partsinventory\\src\\main\\resources\\images\\icon.jpg");
        //stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}