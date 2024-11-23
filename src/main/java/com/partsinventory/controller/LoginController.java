package com.partsinventory.controller;

import com.partsinventory.helper.Session;
import com.partsinventory.helper.ViewManager;
import com.partsinventory.model.Users;
import com.partsinventory.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

public class LoginController {
    @FXML private Button loginButton;

    @FXML private PasswordField password;

    @FXML private TextField username;

    @FXML private Label incorrectPasswordLabel;

    @FXML private Button loginCancelButton;

    @FXML
    void onLogin(ActionEvent event) throws SQLException, AuthenticationException {
//        try {
//            if (LoginService.checkCredentials(username.getText(), password.getText())) {
//                Stage stage = new Stage();
//                loginButton.getScene().getWindow().hide();
//                ViewManager.getInstance().setPrimaryStage(stage);
//                ViewManager.getInstance().loadView("main-view.fxml", "Sales Operations DashBoard", 800, 500);
//                stage.show();
//            } else {
//                incorrectPasswordLabel.setText("Password is not correct!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Users user = LoginService.checkCredentials(username.getText(), password.getText());

        if (user != null) {
            // Set the logged-in user in the session
            Session.getInstance().setLoggedInUser(user);

            // Proceed to setup UI based on the role
            setupUIBasedOnRole();
        } else {
            incorrectPasswordLabel.setText("Invalid username or password!");
        }
    }
    public void setupUIBasedOnRole() {
        Users user = Session.getInstance().getLoggedInUser();
        if (user == null) {
            incorrectPasswordLabel.setText("No user is logged in!");
            return;
        }
        Stage stage = new Stage();
        loginButton.getScene().getWindow().hide();
        ViewManager.getInstance().setPrimaryStage(stage);

        // Pass the user's role to the main view
        ViewManager.getInstance().loadViewWithRole("main-view.fxml", "Sales Operations DashBoard", 800, 500, user.getRole());
        stage.show();
    }
    @FXML
    void onCancel(ActionEvent event) {
        Stage stage = (Stage) loginCancelButton.getScene().getWindow();
        stage.close();
    }
}
