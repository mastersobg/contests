public class Solution {
    
    public int atoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        str = str.trim();
        int mul = 1;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            mul = str.charAt(0) == '-' ? -1 : 1;
            str = str.substring(1, str.length());
        }
        long ret = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                break;
            }
            ret = ret * 10L + (long) (c - '0');
            if (ret * mul > (long)Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (ret * mul < (long)Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int) ret * mul;
    }
}