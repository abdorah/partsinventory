package com.partsinventory.controller;

import com.partsinventory.model.Categorie;
import com.partsinventory.service.PartService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.sql.SQLException;


public class CategoriesController {


    @FXML
    private FlowPane flowPane;
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
            flowPane.setPrefWidth(400); // Set preferred width for the FlowPane
            flowPane.setHgap(20); // Set horizontal gap between buttons
            flowPane.setVgap(20);
            flowPane.getChildren().add(button);

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
