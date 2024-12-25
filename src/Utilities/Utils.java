package Utilities;

public class Utils {
    public static double parseDouble(String s, double defaultValue) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public static double parseDouble(String s) {
        return parseDouble(s, 0);
    }
}
