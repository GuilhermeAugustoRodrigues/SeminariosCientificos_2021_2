package br.com.mauda.seminario.cientificos.util;

import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;

import java.util.List;

public final class ListUtils {
    private ListUtils() {}

    public static boolean isValidList(List<? extends DataValidation> list, boolean validateObjectsInList) {
        boolean validList = !(list == null || list.contains(null));
        if (!validateObjectsInList) {
            return validList;
        }
        if (validList) {
            list.forEach(DataValidation::validateForDataModification);
        }
        return validList;
    }

    public static boolean isEmpty(List<? extends DataValidation> list) {
        return list == null || list.isEmpty();
    }
}
