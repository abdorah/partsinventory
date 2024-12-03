package com.partsinventory.controller;

import static com.partsinventory.helper.AlertHandler.handleDatabaseError;
import static com.partsinventory.helper.AlertHandler.handleDelete;

import com.partsinventory.helper.LocaleManager;
import com.partsinventory.model.Part;
import com.partsinventory.service.BillService;
import com.partsinventory.service.PartService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class SalesChartController {

    @FXML private Button deleteButton;

    @FXML private Button printReportButton;

    @FXML private StackPane resultsStackPane;

    @FXML private SplitPane rootSplitPane;

    @FXML private Button saleButton;

    @FXML private Button searchButton;

    @FXML private MenuItem searchByDescription;

    @FXML private MenuItem searchByName;

    @FXML private Group searchGroup;

    @FXML private SplitMenuButton searchOptionPick;

    @FXML private TextField billSearchTextFeild;
  @FXML private Label partsSearchLabel, clientNameLabel, clientPhoneLabel, billSearchLabel;
    private ResourceBundle bundle;
    private Locale currentLocale;
    @FXML
    void initialize() {
        currentLocale = LocaleManager.loadPreferredLocale();
        bundle = ResourceBundle.getBundle("messages.messages", currentLocale);
        UpdateUi();
        rootSplitPane.setDividerPosition(0, 0.2);
        searchByName.setOnAction(event -> searchOptionPick.setText(searchByName.getText()));
        searchByDescription.setOnAction(
                event -> searchOptionPick.setText(searchByDescription.getText()));

        PartController.loader = "chart";
        FXMLLoader tableViewLoader =
                new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        resultsStackPane.getChildren().clear();
        try {
            Parent tableViewRoot = tableViewLoader.load();
            PartController productTableView = tableViewLoader.getController();

            StackPane.setMargin(
                    productTableView.getPartsListInChart(BillService.instance.getCurrentBillId()),
                    new Insets(10, 10, 10, 10));
            StackPane.setAlignment(resultsStackPane, Pos.CENTER);
            StackPane.setMargin(searchGroup, new Insets(10, 10, 10, 10));
            StackPane.setAlignment(searchGroup, Pos.CENTER);
            resultsStackPane.getChildren().add(tableViewRoot);

            ObservableList<Part> selectedItems =
                    productTableView.getPartsListTableView().getSelectionModel().getSelectedItems();
            deleteButton.setOnAction(
                    event -> {
                        if (selectedItems.toArray().length != 0 && handleDelete()) {
                            for (Part part : selectedItems) {
                                PartService.deletePart(part.getId());
                            }
                            productTableView
                                    .getPartsListTableView()
                                    .getItems()
                                    .removeAll(selectedItems);
                        }
                    });

            printReportButton.setOnAction(event -> {});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void UpdateUi() {
        searchButton.setText(bundle.getString("searchButton.text"));
        deleteButton.setText(bundle.getString("deleteButton.text"));
        printReportButton.setText(bundle.getString("printReportButton.text"));
        saleButton.setText(bundle.getString("saleButton.text"));

        partsSearchLabel.setText(bundle.getString("partsSearch.label"));
        clientNameLabel.setText(bundle.getString("clientName.label"));
        clientPhoneLabel.setText(bundle.getString("clientPhone.label"));
        billSearchLabel.setText(bundle.getString("billSearch.label"));

        searchOptionPick.setText(bundle.getString("searchOptionPick.text"));
        searchByName.setText(bundle.getString("searchByName.text"));
        searchByDescription.setText(bundle.getString("searchByDescription.text"));

    }

    @FXML
    void onSale(ActionEvent event) {}

    @FXML
    void onSearch(ActionEvent event) {
        PartController.loader = "chart";
        FXMLLoader tableViewLoader =
                new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        resultsStackPane.getChildren().clear();
        try {
            Parent tableViewRoot = tableViewLoader.load();
            PartController productTableView = tableViewLoader.getController();
            ObservableList<Part> parts = FXCollections.observableArrayList();
            if (billSearchTextFeild.getText() != null && !billSearchTextFeild.getText().isBlank()) {
                BillService.instance.setCurrentBillId(
                        Integer.parseInt(billSearchTextFeild.getText()));
                parts = BillService.getPartsOfBill(BillService.instance.getCurrentBillId());
            } else if (searchOptionPick.getText().contains("Search by")
                    && billSearchTextFeild.getText().isBlank()) {
                Label warningMessage = new Label("Please select a search criteria");
                warningMessage.setMinHeight(0);
                resultsStackPane.getChildren().add(warningMessage);
                return;
            }
            productTableView.getPartsListTableView().setItems(parts);
            StackPane.setAlignment(resultsStackPane, Pos.CENTER);
            resultsStackPane.getChildren().add(tableViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }
}
