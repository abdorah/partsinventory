package com.partsinventory.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty description;
     private ObjectProperty<byte[]> image;  

    public Category(int id, String name, String description, byte[] image) {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.image = new SimpleObjectProperty<>();
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
        this.image.set(image);
    }

    public Category() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.image = new SimpleObjectProperty<>();
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

    public byte[] getImage() {
        return image.get();
    }

    // public byte[] getImageName() {
    //     return new File(image.get()).getName();
    // }

    public void setImage(byte[] image) {
        this.image.set(image);
    }
}
