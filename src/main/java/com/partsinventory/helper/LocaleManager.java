package com.partsinventory.helper;

import java.util.Locale;
import java.util.prefs.Preferences;

public class LocaleManager {
    private static final String LANGUAGE_KEY = "language";
    private static final String COUNTRY_KEY = "country";

    // Load the preferred locale from user preferences
    public static Locale loadPreferredLocale() {
        Preferences prefs = Preferences.userNodeForPackage(LocaleManager.class);
        String language = prefs.get("language", "en");
        String country = prefs.get("country", "US");
        return new Locale(language, country);
    }


    // Save the preferred locale to user preferences
    public static void savePreferredLocale(Locale locale) {
        Preferences prefs = Preferences.userNodeForPackage(LocaleManager.class);
        prefs.put("language", locale.getLanguage());
        prefs.put("country", locale.getCountry());
    }

}
