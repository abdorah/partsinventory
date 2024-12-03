package com.partsinventory.controller;

import com.partsinventory.helper.LocaleManager;
import com.partsinventory.model.Category;
import com.partsinventory.model.Part;
import com.partsinventory.service.PartService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddPartController {

    // UI Components
    @FXML private Button saveButton;
    @FXML private Button cancelButton1;
    @FXML private TextField partNameField;
    @FXML private TextField partDescriptionField;
    @FXML private ComboBox<String> partMakerField;
    @FXML private TextField partPriceField;
    @FXML private TextField partQuantityField;
    @FXML private ComboBox<Category> partCategory;
    @FXML private Label errorLabel;
    @FXML private Label partNameLabel;
    @FXML private Label partDescriptionLabel;
    @FXML private Label makerLabel;
    @FXML private Label categoryLabel;
    @FXML private Label partPriceLabel;
    @FXML private Label partQuantityLabel;

    // Localization
    private Locale currentLocale;
    private ResourceBundle bundle;

    // Initialization
    @FXML
    void initialize() throws SQLException {
        loadLocale();
        configureUI();
        loadData();
        resetUI();
    }

    private void loadLocale() {
        currentLocale = LocaleManager.loadPreferredLocale();
        bundle = ResourceBundle.getBundle("messages.messages", currentLocale);
    }

    private void configureUI() {
        // Localize UI components
        updateUI();

        // Configure error label
        errorLabel.setVisible(false);

        // Reset fields on action
        javafx.event.EventHandler<ActionEvent> resetAction = event -> resetUI();
        partNameField.setOnAction(resetAction);
        partDescriptionField.setOnAction(resetAction);
        partPriceField.setOnAction(resetAction);
        partQuantityField.setOnAction(resetAction);

        // Configure maker field
        partMakerField.setEditable(true);

        // Configure category combo box
        partCategory.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category category) {
                return (category != null) ? category.getName() : null;
            }

            @Override
            public Category fromString(String s) {
                return null; // Not needed for this use case
            }
        });
    }

    private void loadData() throws SQLException {
        // Load makers
        partMakerField.setItems(PartService.getAllMakers());

        // Load categories
        ObservableList<Category> categories = PartService.getAllCategories();
        partCategory.getItems().addAll(categories);
    }

    private void updateUI() {
        saveButton.setText(bundle.getString("saveButton"));
        cancelButton1.setText(bundle.getString("cancelButton1"));
        errorLabel.setText(bundle.getString("errorLabel"));
        partNameLabel.setText(bundle.getString("partNameLabel"));
        partDescriptionLabel.setText(bundle.getString("partDescriptionLabel"));
        makerLabel.setText(bundle.getString("makerLabel"));
        categoryLabel.setText(bundle.getString("categoryLabel"));
        partPriceLabel.setText(bundle.getString("partPriceLabel"));
        partQuantityLabel.setText(bundle.getString("partQuantityLabel"));
    }

    // Actions
    @FXML
    void savePart(ActionEvent event) {
        if (!validateInput()) return;

        // Create a new Part object
        Part part = new Part(
                0,
                partNameField.getText(),
                partMakerField.getValue(),
                partDescriptionField.getText(),
                Float.parseFloat(partPriceField.getText()),
                Integer.parseInt(partQuantityField.getText()),
                partCategory.getValue()
        );

        // Save the part and show errors if any
        if (!PartService.addPart(part)) {
            errorLabel.setVisible(true);
        } else {
            resetUI();
        }
    }

    @FXML
    void cancelOperation(ActionEvent event) {
        resetUI();
    }

    // Helpers
    private boolean validateInput() {
        if (partNameField.getText().isBlank()
                || partMakerField.getValue() == null
                || partDescriptionField.getText().isBlank()
                || partPriceField.getText().isBlank()
                || partQuantityField.getText().isBlank()
                || partCategory.getValue() == null) {

            errorLabel.setText(bundle.getString("fieldEmpty"));
            errorLabel.setVisible(true);
            return false;
        }

        try {
            Float.parseFloat(partPriceField.getText());
            Integer.parseInt(partQuantityField.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(bundle.getString("invalidInput"));
            errorLabel.setVisible(true);
            return false;
        }

        return true;
    }

    private void resetUI() {
        partNameField.setText("");
        partMakerField.setValue("");
        partDescriptionField.setText("");
        partPriceField.setText("");
        partQuantityField.setText("");
        partCategory.getSelectionModel().clearSelection();
        errorLabel.setVisible(false);
    }
}
