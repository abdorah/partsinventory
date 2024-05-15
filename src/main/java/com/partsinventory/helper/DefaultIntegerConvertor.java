package com.partsinventory.helper;

import javafx.util.StringConverter;

public class DefaultIntegerConvertor extends StringConverter<Integer> {

    @Override
    public String toString(Integer object) {
        return object.toString();
    }

    @Override
    public Integer fromString(String string) {
        try {
            int quantity = Integer.parseInt(string);
            if (quantity >= 0) {
                return quantity;
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
