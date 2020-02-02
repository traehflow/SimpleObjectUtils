package com.proba;

import java.lang.reflect.Field;

public interface SampleInterface {
    default Object getValue(Field field) {
        try {
            return field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "";
        }

    }
}
