package com.example.partsinventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private Button loginbutton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    private Label passincorrectlabel;

    @FXML
    private Button logincanselbutton;
    public boolean islogin(String user,String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query="select*from users where username=? and password=?";
        try {
            if(!DbConnection.checkDrivers()){
                return false;
            }
            preparedStatement=DbConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }

        }catch (Exception ex){
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    @FXML
    void LoginMethode(ActionEvent event){
        try {

            if(islogin(username.getText(),password.getText())){
              try {
                  Parent root=null;
                  root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                  loginbutton.getScene().getWindow().hide();
                  Scene scene =new Scene(root,800,500);
                  Stage stage=new Stage();
                  stage.setScene(scene);
                  stage.show();
              }catch (Exception e){
                  e.printStackTrace();
              }


            }
            else {
                passincorrectlabel.setText("pass not correct!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @FXML
    void LogincancelMethode(ActionEvent event) {
        Stage stage=(Stage) logincanselbutton.getScene().getWindow();
        stage.close();
    }
}
