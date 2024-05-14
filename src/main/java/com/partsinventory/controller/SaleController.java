package com.partsinventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class SaleController {

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

    @FXML private TextField searchTextField;

    @FXML
    void onSale(ActionEvent event) {}

    @FXML
    void onSearch(ActionEvent event) {}
}
