package com.partsinventory.controller;

import static com.partsinventory.helper.AlertHandler.handleDatabaseError;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import com.partsinventory.helper.DefaultFloatConvertor;
import com.partsinventory.helper.LocalDateTableCell;
import com.partsinventory.model.Bill;
import com.partsinventory.service.BillService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class BillsController {

    @FXML private TableView<Bill> billsListTableView;

    @FXML private TableColumn<Bill, Integer> billId;

    @FXML private TableColumn<Bill, String> clientName;

    @FXML private TableColumn<Bill, String> clientPhoneNumber;

    @FXML private TableColumn<Bill, LocalDate> date;

    @FXML private TableColumn<Bill, Float> totalPrice;

    private ResourceBundle bundle;

    @FXML
    private void initialize() throws SQLException {
        // Set the locale and load the resource bundle based on the current locale
        Locale locale = Locale.getDefault(); // This could be set based on user preference
        bundle = ResourceBundle.getBundle("messages.messages", locale); // Load the appropriate properties file

        // Call the updateUI method to set the column headers
        updateUI();

        // Setup cell value factories and cell factories
        billId.setCellValueFactory(new PropertyValueFactory<>("id"));

        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        clientName.setCellFactory(TextFieldTableCell.forTableColumn());
        clientName.setOnEditCommit(event -> BillService.onEditCommit(event, "clientName"));

        clientPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
        clientPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        clientPhoneNumber.setOnEditCommit(event -> BillService.onEditCommit(event, "clientPhone"));

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setCellFactory(LocalDateTableCell::new);
        date.setOnEditCommit(event -> BillService.onEditCommit(event, "date"));

        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultFloatConvertor()));
        totalPrice.setOnEditCommit(event -> BillService.onEditCommit(event, "totalPrice"));

        ObservableList<Bill> bills = BillService.getAllBills();
        try {
            for (Bill bill : billsListTableView.getItems()) {
                BillService.recalculateChartTotal(billId.getCellData(bill));
            }
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
        bills.clear();
        bills.setAll(BillService.getAllBills());
        billsListTableView.setItems(bills);
        billsListTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * This method updates the UI elements based on the language settings.
     */
    private void updateUI() {
        // Update column headers using the ResourceBundle
        billId.setText(bundle.getString("billId"));
        clientName.setText(bundle.getString("clientName"));
        clientPhoneNumber.setText(bundle.getString("clientPhoneNumber"));
        date.setText(bundle.getString("date"));
        totalPrice.setText(bundle.getString("totalPrice"));
    }
}
