package com.partsinventory.controller;

import com.partsinventory.model.Category;
import com.partsinventory.model.Part;
import com.partsinventory.service.PartService;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class AddPartController {

    @FXML private Button cancelButton;

    @FXML private TextField partDescriptionField;

    @FXML private TextField partNameField;

    @FXML private ComboBox<String> partMakerField;

    @FXML private TextField partPriceField;

    @FXML private TextField partQuantityField;

    @FXML private ComboBox<Category> partCategory;

    @FXML private Label errorLabel;

    @FXML
    void initialize() throws SQLException {
        javafx.event.EventHandler<ActionEvent> resetAction =
                (event) -> {
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
        partMakerField.setEditable(true);
        partDescriptionField.setOnAction(resetAction);
        partPriceField.setOnAction(resetAction);
        partQuantityField.setOnAction(resetAction);
        errorLabel.setVisible(false);
        ObservableList<Category> categories = PartService.getAllCategories();
        partCategory.getItems().addAll(categories);
        // Set a StringConverter to display category names in ComboBox
        partCategory.setConverter(
                new StringConverter<Category>() {
                    @Override
                    public String toString(Category category) {
                        return (category != null) ? category.getCatName() : null;
                    }

                    @Override
                    public Category fromString(String s) {
                        return null;
                    }
                });
    }

    @FXML
    void savePart(ActionEvent event) {
        assert partNameField.getText() != null && !partNameField.getText().isBlank();
        assert partMakerField.getValue() != null && !partMakerField.getSelectionModel().isEmpty();
        assert partDescriptionField.getText() != null && !partDescriptionField.getText().isBlank();
        assert partPriceField.getText() != null && !partPriceField.getText().isBlank();
        assert partQuantityField.getText() != null && !partQuantityField.getText().isBlank();
        if (partMakerField.getValue() == null) {
            partMakerField.getSelectionModel().selectFirst();
        }
        Category selectedCategory = partCategory.getValue();
        Part part =
                new Part(
                        0,
                        partNameField.getText(),
                        partMakerField.getValue().toString(),
                        partDescriptionField.getText(),
                        Float.parseFloat(partPriceField.getText()),
                        Integer.parseInt(partQuantityField.getText()),
                        selectedCategory);

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
