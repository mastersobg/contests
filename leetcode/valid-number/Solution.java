public class Solution {
    public boolean isNumber(String s) {
        if (s == null)
            return false;
        s = s.trim();
        try {
            Double.valueOf(s);
        } catch (NumberFormatException e) {
            return false;
        }
        if (!Character.isDigit(s.charAt(s.length() - 1))) {
            if (s.charAt(s.length() - 1) == '.')
                return true;
            return false;
        }
        return true;
    }
}