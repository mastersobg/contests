package com.ivangorbachev.numbers;

import com.ivangorbachev.util.BitsUtil;

/**
 * @author Ivan Gorbachev
 */
public class NumbersUtil {

    public static int [][] getBinomialCoefficients(int size, int mod) {
        int [][]ret = new int[size][size];
        ret[0][0] = 1;
        for (int i = 1; i < ret.length; ++i) {
            ret[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                ret[i][j] = (ret[i - 1][j] + ret[i - 1][j - 1]) % mod;
            }
        }
        return ret;
    }

    public static int pow(int base, int pow, int mod) {
        int ret = 1;
        while (pow > 0) {
            if (BitsUtil.isOdd(pow))
                ret = multiply(ret, base, mod);
            base = multiply(base, base, mod);
            pow >>= 1;

        }
        return ret;
    }

    public static int multiply(int a, int b, int mod) {
        return (int) (((long) a * (long) b) % (long) mod);
    }

    public static int revElementPrimeMod(int el, int mod) {
        return pow(el, mod - 2, mod);
    }

    public static int[] factorial(int n, int mod) {
        int []ret = new int[n + 1];
        ret[0] = 1;
        for (int i = 1; i <= n; i++) {
            ret[i] = multiply(ret[i - 1], i, mod);
        }
        return ret;
    }

    public static long gcd(long a, long b) {
        if (a < b)
            return gcd(b, a);
        return b == 0 ? a : gcd(b, a % b);
    }
}
