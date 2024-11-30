package com.partsinventory.controller;

import com.partsinventory.helper.LocaleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.Locale;

public class SettingsController {

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    private Runnable updateUICallback;
    private Locale currentLocale;

    @FXML
    void initialize() {
        // Populate language choice box
        languageChoiceBox.getItems().addAll("English", "French", "Arabic");

        // Preselect the current language
        if (currentLocale != null) {
            switch (currentLocale.getLanguage()) {
                case "fr":
                    languageChoiceBox.setValue("French");
                    break;
                case "ar":
                    languageChoiceBox.setValue("Arabic");
                    break;
                default:
                    languageChoiceBox.setValue("English");
                    break;
            }
        }

        // Add a listener to detect changes
        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Locale newLocale;
            switch (newVal) {
                case "French":
                    newLocale = new Locale("fr", "FR");
                    break;
                case "Arabic":
                    newLocale = new Locale("ar", "SA");
                    break;
                default:
                    newLocale = new Locale("en", "US");
                    break;
            }

            // Update locale and refresh UI
            if (updateUICallback != null) {
                currentLocale = newLocale;
                LocaleManager.savePreferredLocale(newLocale);
                updateUICallback.run();
            }
        });
    }

    public void setUpdateUICallback(Runnable callback) {
        this.updateUICallback = callback;
    }

    public void setCurrentLocale(Locale locale) {
        this.currentLocale = locale;
    }
    @FXML
    void applyLanguageChange(ActionEvent event) {
        String selectedLanguage = languageChoiceBox.getValue(); // Value from the ChoiceBox
        Locale newLocale;

        switch (selectedLanguage) {
            case "French":
                newLocale = new Locale("fr", "FR");
                break;
            case "Arabic":
                newLocale = new Locale("ar", "SA");
                break;
            default:
                newLocale = new Locale("en", "US");
                break;
        }

        // Notify NavigationController to change the language
        if (updateUICallback != null) {
            updateUICallback.run();
        }
    }
    public Locale getCurrentLocale() {
        return currentLocale;
    }

}
