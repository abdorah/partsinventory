package com.partsinventory.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class QueryLoader {
    
    public static String load(String id){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src\\main\\resources\\sql\\queries.sql")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(id);
    }
}
