package main;

import java.util.HashSet;

public class Cdgame {
    public int rescount(int[] a, int[] b) {
        HashSet<Long> set = new HashSet<>();
        long s1 = sum(a), s2 = sum(b);
        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < b.length; ++j) {
                long prod = (s1 - a[i] + b[j]) * (s2 - b[j] + a[i]);
                set.add(prod);
            }
        return set.size();
    }

    private long sum(int[] v) {
        long ret = 0;
        for (int i = 0; i < v.length; i++) {
            ret += v[i];
        }
        return ret;
    }
}
