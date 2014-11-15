public class Solution {
    public int singleNumber(int[] A) {
        int []bits = new int[32];
        for (int a : A) {
            for (int i = 0; i < 32; ++i)
                if (in(a, i)) {
                    bits[i] = (bits[i] + 1) % 3;
                }
        }
        int ret = 0;
        for (int i = 0; i < 32; ++i)
            if (bits[i] == 1)
                ret = ret | (1 << i);
        return ret;
    }
    
    private boolean in(int a, int bit) {
        return (a & (1 << bit)) != 0;
    }
}