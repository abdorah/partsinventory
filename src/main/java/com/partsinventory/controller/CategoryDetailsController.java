package com.partsinventory.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CategoryDetailsController {
    @FXML
    private ImageView categoryImage;
    @FXML
    private StackPane resultsStackPane;

    public ImageView getCategoryImage(){
        return categoryImage;
    }
    public StackPane getResultsStackPane(){return resultsStackPane;}
    @FXML
    void initialize() {

    }

}
