package com.partsinventory.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Command {

    private IntegerProperty partId;
    private IntegerProperty billId;
    private StringProperty clientName;
    private StringProperty clientPhone;
    private StringProperty description;
    private FloatProperty consideredPrice;
    private IntegerProperty quantity;

    public Command(
            Integer partId,
            Integer billId,
            String clientName,
            String clientPhone,
            String description,
            Float consideredPrice,
            Integer quantity) {
        this.partId = new SimpleIntegerProperty();
        this.billId = new SimpleIntegerProperty();
        this.clientName = new SimpleStringProperty();
        this.clientPhone = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.consideredPrice = new SimpleFloatProperty();
        this.quantity = new SimpleIntegerProperty();
        this.partId.set(partId);
        this.billId.set(billId);
        this.clientName.set(clientName);
        this.clientPhone.set(clientPhone);
        this.description.set(description);
        this.consideredPrice.set(consideredPrice);
        this.quantity.set(quantity);
    }

    public Command() {
        this.partId = new SimpleIntegerProperty();
        this.billId = new SimpleIntegerProperty();
        this.clientName = new SimpleStringProperty();
        this.clientPhone = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.consideredPrice = new SimpleFloatProperty();
        this.quantity = new SimpleIntegerProperty();
    }

    public Integer getPartId() {
        return partId.getValue();
    }

    public void setPartId(Integer partId) {
        this.partId.set(partId);
    }

    public Integer getBillId() {
        return billId.getValue();
    }

    public void setBillId(Integer billId) {
        this.billId.set(billId);
    }

    public String getClientName() {
        return clientName.getValue();
    }

    public void setClientName(String clientName) {
        this.clientName.set(clientName);
    }

    public String getClientPhone() {
        return clientPhone.getValue();
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone.set(clientPhone);
    }

    public String getDescription() {
        return description.getValue();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Float getConsideredPrice() {
        return consideredPrice.getValue();
    }

    public void setConsideredPrice(Float consideredPrice) {
        this.consideredPrice.set(consideredPrice);
    }

    public Integer getQuantity() {
        return quantity.getValue();
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }
}
