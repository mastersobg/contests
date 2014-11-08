public class Solution {
    public int reverse(int x) {
        int m = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int ret = 0;
        while (x != 0) {
            ret = ret * 10 + (x % 10);
            x /= 10;
        }
        return ret * m;
    }
}