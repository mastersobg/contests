public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        long other = 0;
        int oldX = x;
        while (x > 0) {
            int a = x % 10;
            other = other * 10L + a;
            x /= 10;
        }
        return other == oldX;
    }
}