package com.partsinventory.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class NavigationController {

    @FXML
    private Button dashboard;

    @FXML
    private Pane presentationPane;

    @FXML
    private ImageView homeImage;

    @FXML
    private Button toSearchButton;

    @FXML
    private Button toAddPartButton;

    public Pane getPresentationPane() {
        return presentationPane;
    }

    @FXML
    void initialize() {
        FXMLLoader welcomeViewLoader = new FXMLLoader(getClass().getResource("/views/sales-chart-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent welcomeViewRoot = welcomeViewLoader.load();
            StackPane.setMargin(welcomeViewRoot, new Insets(10, 10, 10, 10));
            StackPane.setAlignment(presentationPane, Pos.CENTER);
            presentationPane.getChildren().add(welcomeViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void returnHome(MouseEvent event) {
        FXMLLoader welcomeViewLoader = new FXMLLoader(getClass().getResource("/views/sales-chart-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent welcomeViewRoot = welcomeViewLoader.load();
            StackPane.setMargin(welcomeViewRoot, new Insets(10, 10, 10, 10));
            StackPane.setAlignment(presentationPane, Pos.CENTER);
            presentationPane.getChildren().add(welcomeViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openDashBoard(ActionEvent event) {
        FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent tableViewRoot = tableViewLoader.load();
            PartController productTableView = tableViewLoader.getController();
            StackPane.setMargin(productTableView.getPartsListTableView(), new Insets(10, 10, 10, 10));
            StackPane.setAlignment(presentationPane, Pos.CENTER);
            presentationPane.getChildren().add(tableViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void openCategories(ActionEvent event) {
        FXMLLoader categoriesLoader = new FXMLLoader(getClass().getResource("/views/categories-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent categoriesViewRoot = categoriesLoader.load();
            presentationPane.getChildren().add(categoriesViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void openSearchPage(ActionEvent event) {
        FXMLLoader searchPartsLoader = new FXMLLoader(getClass().getResource("/views/search-part-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent searchViewRoot = searchPartsLoader.load();
            presentationPane.getChildren().add(searchViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openAddPartButton(ActionEvent event) {
        FXMLLoader addPartsLoader = new FXMLLoader(getClass().getResource("/views/add-part-component.fxml"));
        presentationPane.getChildren().clear();
        try {
            Parent addViewRoot = addPartsLoader.load();
            presentationPane.getChildren().add(addViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
