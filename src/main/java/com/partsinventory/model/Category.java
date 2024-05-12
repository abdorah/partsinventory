package com.partsinventory.model;

import javafx.beans.property.*;

public class Category {
    private IntegerProperty catId;
    private StringProperty catName;
    private StringProperty catDesc;
    private StringProperty catImage;

    public Category(int catId, String catName, String catDesc, String catImage) {
        this.catId = new SimpleIntegerProperty();
        this.catName = new SimpleStringProperty();
        this.catDesc = new SimpleStringProperty();
        this.catImage = new SimpleStringProperty();
        this.catId.set(catId);
        this.catName.set(catName);
        this.catDesc.set(catDesc);
        this.catImage.set(catImage);
    }
    public Category() {
        this.catId = new SimpleIntegerProperty();
        this.catName = new SimpleStringProperty();
        this.catDesc = new SimpleStringProperty();
        this.catImage = new SimpleStringProperty();
    }


    public int getCatId() {
        return catId.get();
    }
    public void setCatId(int catId) {
        this.catId.set(catId);
    }

    public String getCatName() {
        return catName.get();
    }


    public void setCatName(String catName) {
        this.catName.set(catName);
    }

    public String getCatDesc() {
        return catDesc.get();
    }

    public void setCatDesc(String catDesc) {
        this.catDesc.set(catDesc);
    }

    public String getCatImage() {
        return catImage.get();
    }

    public void setCatImage(String catImage) {
        this.catImage.set(catImage);
    }
}
