package br.com.mauda.seminario.cientificos.dto.util;

import java.lang.reflect.Field;
import java.util.List;

public class ClassAttributeValidation {
    private ClassAttributeValidation() {}

    public static boolean hasNonNullAttribute(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (fieldIsNonNull(field, object)) {
                return true;
            }
        }
        return false;
    }

    private static boolean fieldIsNonNull(Field field, Object object) {
        try {
            if (field.get(object) instanceof List) {
                return field.get(object) != null && !((List<?>) field.get(object)).isEmpty();
            }
            return field.get(object) != null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
