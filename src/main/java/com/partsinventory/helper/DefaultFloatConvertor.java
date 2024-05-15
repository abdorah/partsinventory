package com.partsinventory.helper;

import javafx.util.StringConverter;

public class DefaultFloatConvertor extends StringConverter<Float> {

    @Override
    public String toString(Float object) {
        return object.toString();
    }

    @Override
    public Float fromString(String string) {
        try {
            float quantity = Float.parseFloat(string);
            if (quantity >= 0) {
                return quantity;
            } else {
                return (float) 0;
            }
        } catch (NumberFormatException e) {
            return (float) 0;
        }
    }
}
