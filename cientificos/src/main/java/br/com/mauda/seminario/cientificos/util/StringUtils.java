package br.com.mauda.seminario.cientificos.util;

public final class StringUtils {
    private StringUtils() {}

    public static boolean isValidString(String string, int length) {
        return !(string == null || string.trim().equals("") || string.length() > length);
    }

    public static boolean isValidEmail(String string, int length) {
        return isValidString(string, length) && string.matches(EmailUtils.EMAIL_PATTERN);
    }
}
