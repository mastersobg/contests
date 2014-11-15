public class Solution {
    public int singleNumber(int[] A) {
        int xor = 0;
        for (int a : A)
            xor ^= a;
        return xor;
    }
}