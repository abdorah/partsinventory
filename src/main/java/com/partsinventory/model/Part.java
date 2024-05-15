package com.partsinventory.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Part {

    private IntegerProperty id;
    private StringProperty maker;
    private StringProperty name;
    private StringProperty description;
    private FloatProperty price;
    private IntegerProperty quantity;
    private ObjectProperty<Category> category;

    public Part(
            int id,
            String name,
            String maker,
            String description,
            Float price,
            int quantity,
            Category category) {
        this.id = new SimpleIntegerProperty();
        this.maker = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleFloatProperty();
        this.quantity = new SimpleIntegerProperty();
        this.category = new SimpleObjectProperty<Category>();
        this.id.set(id);
        this.maker.set(maker);
        this.name.set(name);
        this.description.set(description);
        this.price.set(price);
        this.quantity.set(quantity);
        this.category.set(category);
    }

    public Part() {
        this.id = new SimpleIntegerProperty();
        this.maker = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleFloatProperty();
        this.quantity = new SimpleIntegerProperty();
        this.category = new SimpleObjectProperty<Category>();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMaker() {
        return maker.get();
    }

    public void setMaker(String maker) {
        this.maker.set(maker);
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

    public float getPrice() {
        return price.get();
    }

    public void setPrice(Float price) {
        this.price.set(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public Category getCategory() {
        return category.getValue();
    }

    public StringProperty categoryNameProperty() {
        return new SimpleStringProperty(this.category.getValue().getName());
    }

    public void setCategory(Category category) {
        this.category.setValue(category);
    }
}
