package com.partsinventory.controller;

import com.partsinventory.model.Part;
import com.partsinventory.service.PartService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPartController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField partDescriptionField;

    @FXML
    private TextField partNameField;
    @FXML
    private ComboBox partMakerField;

    @FXML
    private TextField partPriceField;

    @FXML
    private TextField partQuantityField;

    @FXML
    private Button saveButton;

    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        javafx.event.EventHandler<ActionEvent> resetAction = (event) -> {
            if (!errorLabel.isVisible()) {
                partMakerField.getSelectionModel().select(0);
                partNameField.setText("");
                partDescriptionField.setText("");
                partPriceField.setText("");
                partQuantityField.setText("");
            }
        };
        partMakerField.setItems(PartService.populateMakerCombobox());
        //partMakerField.setOnAction(resetAction);
        partNameField.setOnAction(resetAction);
        partDescriptionField.setOnAction(resetAction);
        partPriceField.setOnAction(resetAction);
        partQuantityField.setOnAction(resetAction);
        errorLabel.setVisible(false);
    }

    @FXML
    void savePart(ActionEvent event) {
        assert partMakerField.getValue() != null && !partMakerField.getSelectionModel().isEmpty();
        assert partNameField.getText() != null && !partNameField.getText().isBlank();
        assert partDescriptionField.getText() != null && !partDescriptionField.getText().isBlank();
        assert partPriceField.getText() != null && !partPriceField.getText().isBlank();
        assert partQuantityField.getText() != null && !partQuantityField.getText().isBlank();

        Part part = new Part(0,partMakerField.getValue().toString(), partNameField.getText(), partDescriptionField.getText(),
                Float.parseFloat(partPriceField.getText()), Integer.parseInt(partQuantityField.getText()));

        partMakerField.setValue("");
        partNameField.setText("");
        partDescriptionField.setText("");
        partPriceField.setText("");
        partQuantityField.setText("");

        if (!PartService.addPart(part)) {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    void cancelOperation(ActionEvent event) {
        partMakerField.setValue("");
        partNameField.setText("");
        partDescriptionField.setText("");
        partPriceField.setText("");
        partQuantityField.setText("");
    }

}
