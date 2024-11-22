package com.partsinventory.helper;

import java.io.IOException;

import com.partsinventory.controller.NavigationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

    private static ViewManager instance = new ViewManager();
    private Stage primaryStage;

    private ViewManager() {}

    public static ViewManager getInstance() {
        return instance;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String fxmlPath, String title, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadViewWithRole(String fxmlFile, String title, int width, int height, String role) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the controller of the loaded view
            NavigationController controller = loader.getController();

            // Pass the role to the controller
            controller.initializeRole(role);

            // Use the primary stage (reuse existing window)
            if (primaryStage != null) {
                primaryStage.setTitle(title);
                primaryStage.setScene(new Scene(root, width, height));
                primaryStage.show();
            } else {
                // Fallback to creating a new stage if primaryStage is not set
                Stage newStage = new Stage();
                newStage.setTitle(title);
                newStage.setScene(new Scene(root, width, height));
                newStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
