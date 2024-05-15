package com.partsinventory.model;

import java.io.File;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty description;
    private StringProperty image;

    public Category(int id, String name, String description, String image) {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.image = new SimpleStringProperty();
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
        this.image.set(image);
    }

    public Category() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.image = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getImage() {
        return image.get();
    }

    public String getImageName() {
        return new File(image.get()).getName();
    }

    public void setImage(String image) {
        this.image.set(image);
    }
}
