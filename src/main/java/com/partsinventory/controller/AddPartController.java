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
                partNameField.setText("");
                partMakerField.setValue("");
                partDescriptionField.setText("");
                partPriceField.setText("");
                partQuantityField.setText("");
            }
        };
        partNameField.setOnAction(resetAction);
        partMakerField.setItems(PartService.populateMakerCombobox());
        //partMakerField.setOnAction(resetAction);
        partMakerField.setEditable(true);
        partDescriptionField.setOnAction(resetAction);
        partPriceField.setOnAction(resetAction);
        partQuantityField.setOnAction(resetAction);
        errorLabel.setVisible(false);
    }

    @FXML
    void savePart(ActionEvent event) {
        assert partNameField.getText() != null && !partNameField.getText().isBlank();
        assert partMakerField.getValue() != null && !partMakerField.getSelectionModel().isEmpty();
        assert partDescriptionField.getText() != null && !partDescriptionField.getText().isBlank();
        assert partPriceField.getText() != null && !partPriceField.getText().isBlank();
        assert partQuantityField.getText() != null && !partQuantityField.getText().isBlank();
        if(partMakerField.getValue()==null){
            partMakerField.getSelectionModel().selectFirst();
        }
        Part part = new Part(0,partNameField.getText() , partMakerField.getValue().toString(), partDescriptionField.getText(),
                Float.parseFloat(partPriceField.getText()), Integer.parseInt(partQuantityField.getText()));

        partNameField.setText("");
        partMakerField.setValue("");
        partDescriptionField.setText("");
        partPriceField.setText("");
        partQuantityField.setText("");

        if (!PartService.addPart(part)) {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    void cancelOperation(ActionEvent event) {
        partNameField.setText("");
        partMakerField.setValue("");
        partDescriptionField.setText("");
        partPriceField.setText("");
        partQuantityField.setText("");
    }

}
