public class Solution {
    public int removeElement(int[] a, int elem) {
        int end = a.length - 1;
        int idx = 0;
        while (idx <= end) {
            if (a[idx] == elem) {
                int swap = a[idx];
                a[idx] = a[end];
                a[end] = swap;
                --end;
            } else {
                ++idx;
            }
        }
        return end + 1;
    }
}