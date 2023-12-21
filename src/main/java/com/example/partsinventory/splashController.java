package com.example.partsinventory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class splashController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    private ProgressBar introProgressbar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new splashscreen().start();
    }
    class splashscreen extends Thread{

        @Override
        public void run(){
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Parent root=null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("login-view.fxml"));


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                        Scene scene =new Scene(root);
                        Stage stage=new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setScene(scene);
                        introProgressbar.setProgress(0.5);
                        introProgressbar.setStyle("-fx-accent: green;");
                        stage.show();
                        //THEN HIDE THE SPLASH
                        anchorPane.getScene().getWindow().hide();


                }
            });

        }
    }
}
