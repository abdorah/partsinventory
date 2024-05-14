package com.partsinventory.controller;

import com.partsinventory.helper.ViewManager;
import com.partsinventory.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML private Button loginButton;

    @FXML private PasswordField password;

    @FXML private TextField username;

    @FXML private Label incorrectPasswordLabel;

    @FXML private Button loginCancelButton;

    @FXML
    void onLogin(ActionEvent event) {
        try {
            if (LoginService.checkCredentials(username.getText(), password.getText())) {
                Stage stage = new Stage();
                loginButton.getScene().getWindow().hide();
                ViewManager.getInstance().setPrimaryStage(stage);
                ViewManager.getInstance().loadView("main-view.fxml", "DashBoard", 800, 500);
                stage.show();
            } else {
                incorrectPasswordLabel.setText("password not correct!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        Stage stage = (Stage) loginCancelButton.getScene().getWindow();
        stage.close();
    }
}
