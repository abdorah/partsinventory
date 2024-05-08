package com.partsinventory.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class CategoryDetailsController {
    @FXML
    private ImageView categoryImage;

    public ImageView getCategoryImage(){
        return categoryImage;
    }
    @FXML
    void initialize() {

    }

}
