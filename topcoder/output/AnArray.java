import java.io.*;
import java.util.*;

import static java.lang.Math.*;

import java.util.HashMap;

public class AnArray {
    HashMap<IntIntPair, Integer> count = new HashMap<>();

    void fillMap(int[] v, int n) {
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
                long gcd = NumbersUtil.gcd(A[i] * (long) A[j], K);
                long other = (long) K / gcd;
                ret += get(new IntIntPair(j + 1, (int) other));
            }
        }
        return (int) ret;

    }

}

class NumbersUtil {
    public static long gcd(long a, long b) {
        if (a < b)
            return gcd(b, a);
        return b == 0 ? a : gcd(b, a % b);
    }

}

class IntIntPair implements Comparable<IntIntPair> {
    private int x;
    private int y;

    public IntIntPair() {
    }

    public IntIntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntIntPair intIntPair = (IntIntPair) o;

        if (x != intIntPair.x) return false;
        return y == intIntPair.y;

    }


    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    public int compareTo(IntIntPair o) {
        if (x == o.x) {
            return y == o.y ? 0 : y < o.x ? -1 : 1;
        }
        return x < o.x ? -1 : 1;
    }

}
