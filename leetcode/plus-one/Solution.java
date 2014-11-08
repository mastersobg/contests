public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return null;
        int i = 0;
        for (i = digits.length - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                ++digits[i];
                break;
            } else {
                digits[i] = 0;
            }
        }
        if (i < 0) {
            int []ret = new int[digits.length + 1];
            ret[0] = 1;
            digits = ret;
        }
        return digits;
    }
}