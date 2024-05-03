package com.partsinventory.controller;

import com.partsinventory.model.Categorie;
import com.partsinventory.service.PartService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriesController {

    @FXML
    private HBox Hbox;

    private static final int BUTTON_SIZE = 100;

    FlowPane buttonContainer = new FlowPane();
    // Create a container for buttons
    //Image image = new Image("https://via.placeholder.com/150");

    //Button button = CreateCatCard("https://via.placeholder.com/150");
    @FXML
    void initialize() throws SQLException {
        ObservableList<Categorie>categories= PartService.getAllCategories();
        //String imagePath = "/images/button_image.jpg"; // Path relative to src/main/resources
        System.out.println(categories.size());
        for(Categorie categorie : categories){
            Label label = new Label(categorie.getCatName());
            Button button = CreateCatCard(categorie.getCatImage());
            button.setText("text test");
            Hbox.setSpacing(20);
            Hbox.setPrefHeight(400);
            Hbox.getChildren().add(button);


        }
    }
    private Button CreateCatCard (String imagepath){
        Image image=new Image("file:" + imagepath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(BUTTON_SIZE);
        imageView.setFitHeight(BUTTON_SIZE);

        // Create a button with the image
        Button button = new Button();
        button.setGraphic(imageView);
        button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        return button;
    }
}
