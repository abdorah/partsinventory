package com.partsinventory.controller;

import java.sql.SQLException;

import com.partsinventory.configuration.DefaultFloatConvertor;
import com.partsinventory.configuration.DefaultIntegerConvertor;
import com.partsinventory.model.Part;
import com.partsinventory.service.PartService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class PartController {
    @FXML
    private TableView<Part> partsListTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, String> partDescriptionColumn;

    @FXML
    private TableColumn<Part, Float> partPriceColumn;

    @FXML
    private TableColumn<Part, Integer> partQuantityColumn;

    public TableView<Part> getPartsListTableView() {
        return partsListTableView;
    }

    public TableView<Part> getPartsListByCriteriaTableView(String criteria, String identifier) {
        ObservableList<Part> parts = PartService.getPartByCriteria(criteria, identifier);
        partsListTableView.setItems(parts);
        return partsListTableView;
    }

    @FXML
    private void initialize() throws SQLException {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        partNameColumn.setOnEditCommit(event -> PartService.onEditCommit(event, "name"));

        partDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        partDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        partDescriptionColumn.setOnEditCommit(event -> PartService.onEditCommit(event, "description"));

        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultFloatConvertor()));
        partPriceColumn.setOnEditCommit(event -> PartService.onEditCommit(event, "price"));

        partQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        partQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultIntegerConvertor()));
        partQuantityColumn.setOnEditCommit(event -> PartService.onEditCommit(event, "quantity"));
        
        ObservableList<Part> parts = PartService.getAllParts();
        partsListTableView.setItems(parts);

        partsListTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}
