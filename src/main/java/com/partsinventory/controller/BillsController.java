package com.partsinventory.controller;

import com.partsinventory.model.Command;
import java.sql.Date;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class BillsController {

    @FXML private TableColumn<Command, Integer> billId;

    @FXML private TableColumn<Command, String> clientName;

    @FXML private TableColumn<Command, String> clientPhoneNumber;

    @FXML private TableColumn<Command, Date> date;

    @FXML private TableColumn<Command, Float> totalPrice;

    @FXML
    private void initialize() throws SQLException {}
}
