package com.partsinventory.controller;

import com.partsinventory.helper.ViewManager;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenController {

    @FXML private AnchorPane anchorPane;

    @FXML private ProgressBar progressBar;

    @FXML
    void initialize() {
        CompletableFuture.runAsync(
                        () -> {
                            try {
                                Thread.sleep(2500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .thenRun(
                        () ->
                                Platform.runLater(
                                        () -> {
                                            Stage stage = new Stage();
                                            ViewManager.getInstance().setPrimaryStage(stage);
                                            ViewManager.getInstance()
                                                    .loadView("login-view.fxml", "Login");
                                            stage.initStyle(StageStyle.UNDECORATED);
                                            progressBar.setProgress(0.5);
                                            progressBar.setStyle("-fx-accent: green;");
                                            stage.show();
                                            anchorPane.getScene().getWindow().hide();
                                        }));
    }
}
