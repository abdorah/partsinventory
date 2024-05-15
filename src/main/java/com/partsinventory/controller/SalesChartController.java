package com.partsinventory.controller;

import com.partsinventory.service.PartService;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;

public class SalesChartController {

    @FXML private CategoryAxis salesDaysAxis;

    @FXML private CategoryAxis partsDaysAxis;

    @FXML private StackedBarChart<String, Number> partsSoldPerWeekChart;

    @FXML private NumberAxis salesAxis;

    @FXML private NumberAxis partsAxis;

    @FXML private StackedBarChart<String, Number> salesPerWeekChart;

    @FXML
    void initialize() {
        try {
            partsSoldPerWeekChart.getData().add(PartService.getAllPartsQunatityDESC());
            salesPerWeekChart.getData().add(PartService.getAllPartsPriceDESC());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
