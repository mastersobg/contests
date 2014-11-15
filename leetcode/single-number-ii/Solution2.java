public class Solution {
    public int singleNumber(int[] A) {
        int one = 0, two = 0;
        for (int a : A) {
            int oldTwo = two;
            two = two & ~(two & a);
            two |= one & a;
            
            int oldOne = one;
            one = one & ~(one & a);
            one = one | ~((oldTwo & a) | (oldOne & a)) & a;
        }
        return one;
    }
}