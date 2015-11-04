package com.ivangorbachev;

import com.ivangorbachev.misc.IntIntPair;

import java.util.*;
import java.io.*;

import static com.ivangorbachev.numbers.NumbersUtil.gcd;
import static com.ivangorbachev.util.Dbg.dbg;
import static java.lang.Math.*;

public class AnArray {

    HashMap<IntIntPair, Integer> count = new HashMap<>();

    private List<Integer> factor(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ret.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            ret.add(n);
        }
        return ret;
    }

    void fillMap(int []v, int n) {
        for (int i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                go(i, v);
                go(n / i, v);
            }
        }
    }

    private void go(int n, int[] v) {
        int s = 0;
        for (int i = v.length - 1; i >= 0; --i) {
            if (v[i] % n == 0)
                ++s;
            IntIntPair p = new IntIntPair(i, n);
            count.put(p, s);
        }
    }

    int get(IntIntPair p) {
        Integer ret = count.get(p);
        return ret == null ? 0 : ret;
    }

    public int solveProblem(int[] A, int K) {
        fillMap(A, K);
        long ret = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                long gcd = gcd(A[i] * (long) A[j], K);
                long other = (long)K / gcd;
                ret += get(new IntIntPair(j + 1, (int) other));
            }
        }
        return (int) ret;

    }
}
