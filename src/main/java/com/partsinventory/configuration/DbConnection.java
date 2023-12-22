package com.partsinventory.configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    public Connection connection;
    public String Dbuser="";
    public String Dbpassword="";
    public String Dburl="";
    public String dbPrefix = "jdbc:sqlite:";
    private static final String location = DbConnection.class.getResource("/database/database.db").toExternalForm();
    public static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Connection getConnection(){
        Connection connection;
        String dbPrefix="jdbc:sqlite:";
        try {
                connection = DriverManager.getConnection(dbPrefix + location);

        }catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not connect to SQLite DB at " +
                            location);
            return null;
        }
        return connection;
    }

}
