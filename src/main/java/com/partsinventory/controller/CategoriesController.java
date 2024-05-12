package com.partsinventory.controller;

import com.partsinventory.model.Category;
import com.partsinventory.service.PartService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class CategoriesController {

    @FXML
    private Button chooseImageButton;

    @FXML
    private Label errorLabel1;

    @FXML
    private FlowPane flowPane;

    @FXML
    private TextField partDescriptionField1;

    @FXML
    private TextField partNameField1;

    @FXML
    private ImageView categoryImage;
    @FXML
    private StackPane resultsStackPane;



    private static final int BUTTON_SIZE = 100;

    //Image image = new Image("https://via.placeholder.com/150");
    String imagePath = null;
    @FXML
    void initialize() throws SQLException {

        ObservableList<Category>categories= PartService.getAllCategories();
        flowPane.getChildren().clear();
        Button button = null;
        MenuItem menuItem1=null;
        for(Category category : categories){
           button = CreateCatCard(category.getCatImage());
            button.setText(category.getCatName());

            HBox hBox=new HBox();
            hBox.setSpacing(10); // Set horizontal gap between buttons
            hBox.getChildren().add(button);
            flowPane.setPrefWidth(400); // Set preferred width for the FlowPane
            flowPane.setHgap(10); // Set horizontal gap between buttons
            flowPane.setVgap(10);
            flowPane.getChildren().add(hBox);
            ContextMenu contextMenu = new ContextMenu();
            // create menuitems
            menuItem1 = new MenuItem("delete category");
            // add menu items to menu
            contextMenu.getItems().add(menuItem1);
            button.setContextMenu(contextMenu);
            menuItem1.setOnAction(e->{
                PartService.deleteCategory(category.getCatId());
            });
            button.setOnAction(e->{
                openCategory(category);
            });
        }

    }
    @FXML
    void chooseImage(ActionEvent event) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);// Set width of the image view (adjust as needed)
        imageView.setPreserveRatio(true); // Preserve aspect ratio of the image

        // Event handler for the chooseImageButton

        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        // Set extension filter to only allow image files
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(chooseImageButton.getScene().getWindow());
        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            imageView.setImage(new javafx.scene.image.Image(imagePath));
        }
    }
    @FXML
    void addCategory(ActionEvent event) {
        assert partNameField1.getText() != null && !partNameField1.getText().isBlank();
        assert partDescriptionField1.getText() != null && !partDescriptionField1.getText().isBlank();
        assert imagePath != null && !imagePath.isBlank();

        Category category = new Category(0,partNameField1.getText() ,partDescriptionField1.getText(),imagePath);

        partNameField1.setText("");
        partDescriptionField1.setText("");


        if (!PartService.addCategory(category)) {
            errorLabel1.setVisible(true);
        }


    }

    private Button CreateCatCard (String imagepath){
        if (imagepath.startsWith("file:/")) {
            imagepath = imagepath.substring(6); // Remove "file:/" prefix
        }
        Image image=new Image("file:" + imagepath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(BUTTON_SIZE-50);
        imageView.setFitHeight(BUTTON_SIZE-50);

        // Create a button with the image
        Button button = new Button();
        button.setGraphic(imageView);
        button.setPrefSize(BUTTON_SIZE+50, BUTTON_SIZE);

        return button;
    }
private void openCategory(Category category){
    FXMLLoader categoriesLoader = new FXMLLoader(getClass().getResource("/views/category-details-component.fxml"));
    resultsStackPane.getChildren().clear();
    try {
        Parent categoriesViewRoot = categoriesLoader.load();
        CategoryDetailsController categoryController=categoriesLoader.getController();
        categoryImage= categoryController.getCategoryImage();
        categoryImage.setImage(new javafx.scene.image.Image(category.getCatImage()));
        BorderPane root = new BorderPane();
        root.setTop(categoryImage);
        root.setCenter(categoriesViewRoot);
        root.setBottom(displayCategorydetails());
        resultsStackPane.getChildren().add(root);
        resultsStackPane.setAlignment(Pos.BASELINE_CENTER);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
private Parent displayCategorydetails() throws IOException {
    FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        Parent tableViewRoot = tableViewLoader.load();
        PartController productTableView = tableViewLoader.getController();
        productTableView.getPartsListTableView()
                .getColumns()
                .stream()
                .filter(partTableColumn -> partTableColumn.getId().toLowerCase().contains("category"))
                .forEach(partTableColumn -> partTableColumn.setVisible(false));
        StackPane.setMargin(productTableView.getPartsListTableView(), new Insets(10, 10, 10, 10));
        return tableViewRoot;

}

}
