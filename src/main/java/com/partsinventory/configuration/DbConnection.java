package com.partsinventory.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DbConnection {

    public static Connection connection;

    private static final String dbPrefix = "jdbc:sqlite:";
    private static final String location = "/C:/code/desktop application/New version/partsstore/partsstore/src/main/resources/database/database.db";

    private DbConnection() {
    }

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

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not connect to SQLite DB at " +
                            location);
            return null;
        }
        return connection;
    }

    public static String load(String id) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src\\main\\resources\\sql\\queries.sql")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(id);
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not close SQLite DB connection");
        }
    }

    public static ResultSet DbqueryExecute(String query) throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        CachedRowSet crs = null;

        try {
            st = DbConnection.getConnection().createStatement();
            rs = st.executeQuery(query);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
            st.close();
        }
        return crs;
    }

}
