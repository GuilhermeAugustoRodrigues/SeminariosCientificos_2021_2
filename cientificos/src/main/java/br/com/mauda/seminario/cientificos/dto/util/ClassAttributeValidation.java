package br.com.mauda.seminario.cientificos.dto.util;

import java.lang.reflect.Field;
import java.util.List;

public class ClassAttributeValidation {
    private ClassAttributeValidation() {}
    
    public static boolean validateAllFields(Object object) {
        Field[] fieldList = object.getClass().getDeclaredFields();
        for (Field field : fieldList) {
            if (fieldIsNonNull(field, object)) {
                return true;
            }
        }
        return false;
    }

    private static boolean fieldIsNonNull(Field field, Object object) {
        try {
            field.setAccessible(true);
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
