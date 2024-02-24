package com.partsinventory.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.partsinventory.configuration.DbConnection;
import com.partsinventory.model.Part;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class PartService {

    private static ObservableList<Part> getAllPartsFromResultset(ResultSet rs) throws SQLException {
        ObservableList<Part> partslist = FXCollections.observableArrayList();
        while (rs.next()) {
            Part part = new Part();
            part.setId(rs.getInt("id"));
            part.setMaker(rs.getString("maker"));
            part.setName(rs.getString("name"));
            part.setDescription(rs.getString("description"));
            part.setPrice(rs.getFloat("price"));
            part.setQuantity(rs.getInt("quantity"));
            partslist.add(part);
        }
        return partslist;
    }

    public static ObservableList<Part> getAllParts() throws SQLException {
        String statement = DbConnection.load("ALL_PARTS");
        ResultSet rs = DbConnection.DbqueryExecute(statement);
        ObservableList<Part> partsList = getAllPartsFromResultset(rs);
        return partsList;
    }

    public static Series<String, Number> getAllPartsQunatityDESC() throws SQLException {
        String statement = DbConnection.load("PARTS_QUANTITY_DESC");
        ResultSet rs = DbConnection.DbqueryExecute(statement);
        ObservableList<Part> partsList = getAllPartsFromResultset(rs);
        Series<String, Number> charList = new Series<>();
        for (Part part : partsList) {
            charList.getData().add(new Data<String, Number>(part.getName(), part.getPrice()));
        }
        return charList;
    }

    public static Series<String, Number> getAllPartsPriceDESC() throws SQLException {
        String statement = DbConnection.load("PARTS_PRICE_DESC");
        ResultSet rs = DbConnection.DbqueryExecute(statement);
        ObservableList<Part> partsList = getAllPartsFromResultset(rs);
        Series<String, Number> charList = new Series<>();
        for (Part part : partsList) {
            charList.getData().add(new Data<String, Number>(part.getName(), part.getQuantity()));
        }
        return charList;
    }

    public static ObservableList<Part> getPartByCriteria(String criteria, String target) {
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        try (Connection connection = DbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        String.format("%s %s '%s%%'", DbConnection.load("PART_BY_CRITERIA"), criteria, target))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    partsList.add(extractPartFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partsList;
    }

    public static void updatePart(Part part) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(DbConnection.load("UPDATE_PART"))) {
            statement.setString(1, part.getMaker());
            statement.setString(2, part.getName());
            statement.setString(3, part.getDescription());
            statement.setFloat(4, part.getPrice());
            statement.setInt(5, part.getQuantity());
            statement.setInt(6, part.getId());
            statement.executeUpdate();
        }
    }

    private static Part extractPartFromResultSet(ResultSet resultSet) throws SQLException {
        Part part = new Part();
        part.setId(resultSet.getInt("id"));
        part.setMaker(resultSet.getString("maker"));
        part.setName(resultSet.getString("name"));
        part.setDescription(resultSet.getString("description"));
        part.setPrice(resultSet.getFloat("price"));
        part.setQuantity(resultSet.getInt("quantity"));
        return part;
    }

    public static boolean addPart(Part part) {
        int result = 0;
        try (Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(DbConnection.load("ADD_PART"));) {
            statement.setString(1, part.getMaker());
            statement.setString(2, part.getName());
            statement.setString(3, part.getDescription());
            statement.setFloat(4, part.getPrice());
            statement.setInt(5, part.getQuantity());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result == 1;
    }

    public static void onEditCommit(TableColumn.CellEditEvent<Part, ?> event, String propertyName) {
        try {
            Part editedPart = event.getRowValue();
            String newValue = event.getNewValue().toString();

            switch (propertyName) {
                case "name":
                    editedPart.setName(newValue);
                    break;
                case "maker":
                    editedPart.setMaker(newValue);
                    break;
                case "description":
                    editedPart.setDescription(newValue);
                    break;
                case "price":
                    editedPart.setPrice(Float.parseFloat(newValue));
                    break;
                case "quantity":
                    editedPart.setQuantity(Integer.parseInt(newValue));
                    break;
            }
            PartService.updatePart(editedPart);
            handleSuccessfulEdit();
        } catch (NumberFormatException e) {
            handleInvalidInput();
        } catch (SQLException e) {
            handleDatabaseError(e);
        }
    }

    public static void deletePart(int id) {
        try (Connection connection = DbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DbConnection.load("DELETE_PART"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            //handleDelete();
        } catch (SQLException e){
            handleDatabaseError(e);
        }
    }

    public static void handleSuccessfulEdit() {
        showAlert("Successful Edit", "Part saved successfully.", Alert.AlertType.INFORMATION);
    }

    public static Boolean handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete Part "+" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }else {
            return false;
        }
    }

    public static void handleInvalidInput() {
        showAlert("Invalid Input", "Please enter valid numerical values for quantity and price.",
                Alert.AlertType.ERROR);
    }

    public static void handleDatabaseError(SQLException e) {
        showAlert("Database Error", "Failed to update part: " + e.getMessage(), Alert.AlertType.ERROR);
    }

    public static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static ObservableList<String> populateMakerCombobox(){
        ObservableList<String> collection= FXCollections.observableArrayList("mahle","Bosch");
       return collection;
    }
}
