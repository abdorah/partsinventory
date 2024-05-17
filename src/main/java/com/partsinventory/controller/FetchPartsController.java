package com.partsinventory.controller;

import static com.partsinventory.helper.AlertHandler.handleDatabaseError;
import static com.partsinventory.helper.AlertHandler.handleDelete;
import static com.partsinventory.helper.AlertHandler.handleSale;

import com.partsinventory.model.Part;
import com.partsinventory.service.PartService;
import java.io.IOException;
import java.sql.SQLException;
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

public class FetchPartsController {

    @FXML private SplitPane rootSplitPane;

    @FXML private StackPane resultsStackPane;

    @FXML private Group searchGroup;

    @FXML private SplitMenuButton searchOptionPick;

    @FXML private MenuItem searchByDescription;

    @FXML private MenuItem searchByName;

    @FXML private TextField searchTextField;

    @FXML private Button searchButton;

    @FXML private Button addToChartButton;

    @FXML private Button deleteButton;

    @FXML
    void initialize() {
        rootSplitPane.setDividerPosition(0, 0.2);
        searchByName.setOnAction(event -> searchOptionPick.setText(searchByName.getText()));
        searchByDescription.setOnAction(
                event -> searchOptionPick.setText(searchByDescription.getText()));
        FXMLLoader tableViewLoader =
                new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        resultsStackPane.getChildren().clear();
        try {
            Parent tableViewRoot = tableViewLoader.load();
            PartController productTableView = tableViewLoader.getController();
            StackPane.setMargin(
                    productTableView.getPartsListTableView(), new Insets(10, 10, 10, 10));
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

            addToChartButton.setOnAction(
                    event -> {
                        if (selectedItems.toArray().length != 0) {
                            handleSale();
                            int billId = (int) PartService.addBill("", "");
                            float totalPrice = 0f;
                            for (Part part : selectedItems) {
                                PartService.addToChart(part.getId(), billId, 1, part.getPrice());
                                totalPrice += part.getPrice();
                            }
                            PartService.updateBill(billId, "", "", totalPrice);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearch(ActionEvent event) {
        FXMLLoader tableViewLoader =
                new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        resultsStackPane.getChildren().clear();
        try {
            Parent tableViewRoot = tableViewLoader.load();
            PartController productTableView = tableViewLoader.getController();
            ObservableList<Part> parts;
            if (searchTextField.getText() != null
                    && !searchTextField.getText().isBlank()
                    && !searchOptionPick.getText().contains("Search by")) {
                parts =
                        PartService.getPartByCriteria(
                                searchOptionPick.getText() + " like", searchTextField.getText());
            } else if (searchOptionPick.getText().contains("Search by")) {
                Label warningMessage = new Label("Please select a search criteria");
                warningMessage.setMinHeight(0);
                resultsStackPane.getChildren().add(warningMessage);
                return;
            } else {
                parts = PartService.getAllParts();
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
