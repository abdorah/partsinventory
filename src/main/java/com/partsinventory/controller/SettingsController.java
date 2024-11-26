package com.partsinventory.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsController {


    @FXML
    private ComboBox<String> languagecombobox;

    @FXML
    private Button applyButton;

    private Locale currentLocale;
    private ResourceBundle bundle;
    private Runnable updateUI;

    public void initialize() {
        // Initialize ComboBox
        languagecombobox.getItems().addAll("English", "Arabic", "French");
    }

    @FXML
    public void applyLanguageChange() {
        String selectedLanguage = languagecombobox.getValue();
        if (selectedLanguage != null) {
            switch (selectedLanguage) {
                case "English" -> currentLocale = Locale.ENGLISH;
                case "Arabic" -> currentLocale = new Locale("ar");
                case "French" -> currentLocale = new Locale("fr");
            }
            bundle = ResourceBundle.getBundle("messages", currentLocale);

            // Call the callback to update the main UI
            if (updateUI != null) {
                updateUI.run();
            }

            // Close settings window
            Stage stage = (Stage) applyButton.getScene().getWindow();
            stage.close();
        }
    }

    public void setUpdateUICallback(Runnable updateUI) {
        this.updateUI = updateUI;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
