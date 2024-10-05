package com.partsinventory.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import com.partsinventory.helper.ImageUtils;
import com.partsinventory.model.Category;
import com.partsinventory.service.PartService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class CategoriesController {

    @FXML private Button chooseImageButton;

    @FXML private Label errorLabel1;

    @FXML private FlowPane flowPane;

    @FXML private TextField partDescriptionField1;

    @FXML private TextField partNameField1;

    @FXML private ImageView categoryImage;

    @FXML private StackPane resultsStackPane;

    private static final int BUTTON_SIZE = 100;

    String imagePath = null;

    @FXML
    void initialize() throws SQLException {

        ObservableList<Category> categories = PartService.getAllCategories();
        flowPane.getChildren().clear();
        Button button = null;
        MenuItem menuItem1 = null;
        for (Category category : categories) {
            button = createCatCard(category.getImage());  // Updated to use byte[]
            button.setText(category.getName());

            HBox hBox = new HBox();
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
            menuItem1.setOnAction(
                    event -> {
                        PartService.deleteCategory(category.getId());
                    });
            button.setOnAction(
                    event -> {
                        openCategory(category);
                    });
        }
    }

    // Refactored: CreateCatCard now accepts byte[] for image creation
    private Button createCatCard(byte[] imageBytes) {
        if (imageBytes != null) {
            // Create image from byte[]
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(BUTTON_SIZE - 50);
            imageView.setFitHeight(BUTTON_SIZE - 50);
            imageView.setPreserveRatio(true);  // Preserve the aspect ratio

            // Create a button with the image
            Button button = new Button();
            button.setGraphic(imageView);
            button.setPrefSize(BUTTON_SIZE + 50, BUTTON_SIZE);

            return button;
        } else {
            // Handle missing or invalid image bytes by returning a placeholder button
            Button button = new Button("No Image Available");
            return button;
        }
    }

    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        // Set extension filter to only allow image files
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(chooseImageButton.getScene().getWindow());

        if (selectedFile != null) {
            try {
                // Read the image file as byte array
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
    
                // Convert byte array to image for display in the UI
                Image image = new Image(new ByteArrayInputStream(imageBytes));  // Use ByteArrayInputStream to create the Image
                categoryImage = new ImageView();
                categoryImage.setImage(image);
    
            } catch (IOException e) {
                e.printStackTrace();
                errorLabel1.setText("Failed to load image.");
            }
        }
    }

    @FXML
    void addCategory(ActionEvent event) {
        assert partNameField1.getText() != null && !partNameField1.getText().isBlank();
        assert partDescriptionField1.getText() != null && !partDescriptionField1.getText().isBlank();
        assert imagePath != null && !imagePath.isBlank();

        // Convert the image into bytes
        byte[] imageBytes = null;
        try {
            imageBytes = ImageUtils.imageToBytes(categoryImage.getImage(), "png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the category with image data as a byte array
        Category category = new Category(0, partNameField1.getText(), partDescriptionField1.getText(), imageBytes);

        partNameField1.setText("");
        partDescriptionField1.setText("");

        if (!PartService.addCategoryWithBlob(category)) {
            errorLabel1.setVisible(true);
        }
    }

    private void openCategory(Category category) {
        FXMLLoader categoriesLoader =
                new FXMLLoader(getClass().getResource("/views/category-details-component.fxml"));
        resultsStackPane.getChildren().clear();
        try {
            Parent categoriesViewRoot = categoriesLoader.load();
            CategoryDetailsController categoryController = categoriesLoader.getController();
            categoryImage = categoryController.getCategoryImage();
            categoryImage.setImage(new javafx.scene.image.Image(new ByteArrayInputStream(category.getImage())));  // Ensure byte[] is used correctly
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
        PartController.loader = "part";
        FXMLLoader tableViewLoader =
                new FXMLLoader(getClass().getResource("/views/parts-table-component.fxml"));
        Parent tableViewRoot = tableViewLoader.load();
        PartController productTableView = tableViewLoader.getController();
        productTableView.getPartsListTableView().getColumns().stream()
                .filter(
                        partTableColumn ->
                                partTableColumn.getId().toLowerCase().contains("category"))
                .forEach(partTableColumn -> partTableColumn.setVisible(false));
        StackPane.setMargin(productTableView.getPartsListTableView(), new Insets(10, 10, 10, 10));
        return tableViewRoot;
    }
}
