package com.partsinventory.service;

import com.partsinventory.helper.DbConnection;
import com.partsinventory.model.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BillService {

    private static int currentBillId;
    public static BillService instance = new BillService();

    private BillService() {
        currentBillId = -1;
    }

    public int getCurrentBillId() {
        return currentBillId;
    }

    public void setCurrentBillId(int currentBillId) {
        BillService.currentBillId = currentBillId;
    }

    public static ObservableList<Part> getPartsOfBill(int billId) throws SQLException {
        if (billId == -1) throw new SQLException("No bill Selected");
        String statement = DbConnection.load("PART_IN_BILL");
        ResultSet resultSet = DbConnection.DbqueryExecute(statement);
        ObservableList<Part> partslist = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Part part = new Part();
            part.setId(resultSet.getInt("id"));
            part.setName(resultSet.getString("name"));
            part.setMaker(resultSet.getString("maker"));
            part.setDescription(resultSet.getString("description"));
            part.setPrice(resultSet.getFloat("price"));
            part.setQuantity(resultSet.getInt("quantity"));
            part.setCategory(PartService.catgetegoryById(resultSet.getInt("catid")));
            partslist.add(part);
        }
        return partslist;
    }
}
