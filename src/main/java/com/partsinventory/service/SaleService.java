package com.partsinventory.service;

import static com.partsinventory.helper.AlertHandler.handleDatabaseError;

import com.partsinventory.helper.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleService {
    public static void addPartToChart(int id) {
        try (Connection connection = DbConnection.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(DbConnection.load("ADD_PART_TO_CHART"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }
}
