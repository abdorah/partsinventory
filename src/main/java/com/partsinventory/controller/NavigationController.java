package com.partsinventory.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.partsinventory.helper.LocaleManager;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NavigationController {

    @FXML private Pane presentationPane;
    @FXML
    private Button toAddPartButton;
    @FXML
    private Button Categories;
    @FXML
    private Button dashboard;
    @FXML
    private Button toSearchButton;
    @FXML
    private Button salesChartButton;
    @FXML
    private Button billsButton;
    @FXML
    private Button settingsButton;

    private ResourceBundle bundle;
    private Locale currentLocale;
    private Runnable updateUI;
    @FXML
    void initialize() {

        // Load the user's preferred language or default to English (US)
        currentLocale = LocaleManager.loadPreferredLocale();
        bundle = ResourceBundle.getBundle("messages.messages", currentLocale);

        // Initialize UI with the current language
        updateUI();
        // Load the desired view dynamically
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
//
//        try {
//            Parent root = loader.load();
//
//            // Get the controller of the loaded view
//            NavigationController controller = loader.getController();
//
//            // Assume 'userRole' is the role of the logged-in user
//            String userRole = Session.getInstance().getLoggedInUser().getRole();
//
//            // Pass the role to the controller
//            controller.initializeRole(userRole);
//
//            // Display the view in the appropriate UI container (like a StackPane)
//            presentationPane.getChildren().clear();
//            StackPane.setMargin(root, new Insets(10, 10, 10, 10));
//            StackPane.setAlignment(presentationPane, Pos.CENTER);
//            presentationPane.getChildren().add(root);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        presentationPane.getChildren().clear();
    }

    @FXML
    void returnHome(MouseEvent event) {
        FXMLLoader welcomeViewLoader =
                new FXMLLoader(getClass().getResource("/views/home-screen-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent welcomeViewRoot = welcomeViewLoader.load();
            StackPane.setMargin(welcomeViewRoot, new Insets(10, 10, 10, 10));
            StackPane.setAlignment(presentationPane, Pos.CENTER);
            presentationPane.getChildren().add(welcomeViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openDashBoard(ActionEvent event) {
        PartController.loader = "part";
        FXMLLoader tableViewLoader =
                new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent tableViewRoot = tableViewLoader.load();
            PartController productTableView = tableViewLoader.getController();
            StackPane.setMargin(
                    productTableView.getPartsListTableView(), new Insets(10, 10, 10, 10));
            StackPane.setAlignment(presentationPane, Pos.CENTER);
            presentationPane.getChildren().add(tableViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openCategories(ActionEvent event) {
        FXMLLoader categoriesLoader =
                new FXMLLoader(getClass().getResource("/views/categories-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent categoriesViewRoot = categoriesLoader.load();
            presentationPane.getChildren().add(categoriesViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openSearchPage(ActionEvent event) {
        FXMLLoader searchPartsLoader =
                new FXMLLoader(getClass().getResource("/views/search-part-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent searchViewRoot = searchPartsLoader.load();
            presentationPane.getChildren().add(searchViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openAddPartButton(ActionEvent event) {
        FXMLLoader addPartsLoader =
                new FXMLLoader(getClass().getResource("/views/add-part-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent addViewRoot = addPartsLoader.load();
            presentationPane.getChildren().add(addViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openSalesChartButton(ActionEvent event) {
        FXMLLoader salesChartLoader =
                new FXMLLoader(getClass().getResource("/views/sales-chart-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent salesChartViewRoot = salesChartLoader.load();
            presentationPane.getChildren().add(salesChartViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openBillsButton(ActionEvent event) {
        FXMLLoader billsViewLoader =
                new FXMLLoader(getClass().getResource("/views/bills-table-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent billsViewRoot = billsViewLoader.load();
            presentationPane.getChildren().add(billsViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        bundle = ResourceBundle.getBundle("messages.messages", currentLocale);
        Categories.setText(bundle.getString("categories"));
        dashboard.setText(bundle.getString("dashboard"));
        toSearchButton.setText(bundle.getString("fetchPart"));
        toAddPartButton.setText(bundle.getString("addPart"));
        salesChartButton.setText(bundle.getString("salesChart"));
        billsButton.setText(bundle.getString("bills"));
        settingsButton.setText(bundle.getString("settings"));

        // Refresh the main screen with updated language
        returnHome(null);
    }
    @FXML
    void changeLanguage(Locale newLocale) {
        this.currentLocale = newLocale;
        LocaleManager.savePreferredLocale(newLocale);
        bundle = ResourceBundle.getBundle("messages.messages", currentLocale);
        updateUI();
    }
    // Open the Settings view
    @FXML
    void openSettingsButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/settings-view.fxml"));
            Parent settingsRoot = loader.load();

            SettingsController settingsController = loader.getController();
            settingsController.setUpdateUICallback(() -> changeLanguage(settingsController.getCurrentLocale()));
            settingsController.setCurrentLocale(currentLocale);

            // Open settings in a new stage
            Stage settingsStage = new Stage();
            settingsStage.setTitle("Settings");
            settingsStage.setScene(new Scene(settingsRoot));
            settingsStage.initOwner(presentationPane.getScene().getWindow());
            settingsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void initializeRole(String role) {
        // Role-based UI logic
        switch (role.toLowerCase()) {
            case "admin":
                presentationPane.setDisable(false);
                System.out.println("admin");
                /*adminPanel.setDisable(false);
                transactionPanel.setDisable(false);
                staffPanel.setDisable(false);

                 */
                break;
            case "user":
                presentationPane.setDisable(false);
                System.out.println("user");
               /* adminPanel.setDisable(true);
                transactionPanel.setDisable(false);
                staffPanel.setDisable(false);

                */
                break;
            case "guest":
                /*adminPanel.setDisable(true);
                transactionPanel.setDisable(true);
                staffPanel.setDisable(false);

                 */
                break;
            default:
               /* adminPanel.setDisable(true);
                transactionPanel.setDisable(true);
                staffPanel.setDisable(true);

                */
                break;
        }
    }
}
