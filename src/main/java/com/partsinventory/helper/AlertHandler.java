package com.partsinventory.helper;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertHandler {
    public static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void handleInvalidInput() {
        showAlert(
                "Invalid Input",
                "Please enter valid numerical values for quantity and price.",
                Alert.AlertType.ERROR);
    }

    public static void handleSale() {
        showAlert(
                "Successful Addition",
                "Part added to chart successfully.",
                Alert.AlertType.INFORMATION);
    }

    public static void handleSuccessfulEdit() {
        showAlert("Successful Edit", "Part saved successfully.", Alert.AlertType.INFORMATION);
    }

    public static Boolean handleDelete() {
        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Delete Part " + " ?",
                        ButtonType.YES,
                        ButtonType.NO,
                        ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }

    public static void handleDatabaseError(SQLException e) {
        showAlert(
                "Database Error",
                "Failed to update part: " + e.getMessage(),
                Alert.AlertType.ERROR);
    }
}
