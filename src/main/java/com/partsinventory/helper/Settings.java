package com.partsinventory.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static final File propertiesFile =
            new File("src\\main\\resources\\application-main.properties");
    private static Properties properties = new Properties();

    private Settings() {}

    public static Properties getProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String loadPath(String rootPropertyKey, String child) {
        return new File(Settings.getProperties().getProperty(rootPropertyKey), child)
                .toURI()
                .toString();
    }
}
