package com.ivangorbachev;

import java.util.Arrays;

import static java.lang.Math.abs;

public class RandomOption {

    double[][][] dp;
    int n;
    int[] l, r;
    boolean[][] can;

    double calc(int pos, int last, int mask) {
        if (pos == n) {
            return 1.0;
        }
        if (dp[pos][last][mask] < 0.0) {
            double ret = 0.0;
            int available = n - Integer.bitCount(mask);
            double p = 1.0 / (available + 0.0);
            for (int i = 0; i < n; ++i)
                if ((mask & (1 << i)) == 0) {
                    if (!can[last][i]) {
                        ret += calc(pos + 1, i, mask | (1 << i)) * p;
                    }
                }
            dp[pos][last][mask] = ret;
        }
        return dp[pos][last][mask];
    }

    public double getProbability(int keyCount, int[] badLane1, int[] badLane2) {
        n = keyCount;
        l = badLane1;
        r = badLane2;
        can = new boolean[n + 1][n + 1];
        for (int i = 0; i < badLane1.length; ++i) {
            can[badLane1[i]][badLane2[i]] = can[badLane2[i]][badLane1[i]] = true;
        }
        dp = new double[n + 1][n + 1][1 << n];
        for (int i = 0; i < dp.length; ++i)
            for (int j = 0; j < dp[i].length; ++j)
                Arrays.fill(dp[i][j], -1.0);
        return calc(0, n, 0);
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0, (new RandomOption()).getProbability(5, new int[]{0}, new int[]{3}), 0.6);
            eq(1, (new RandomOption()).getProbability(5, new int[]{0, 1, 2}, new int[]{1, 2, 0}), 0.1);
            eq(2, (new RandomOption()).getProbability(5, new int[]{2, 2, 2, 2}, new int[]{0, 1, 3, 4}), 0.0);
            eq(3, (new RandomOption()).getProbability(7, new int[]{0, 1, 2}, new int[]{6, 5, 4}), 0.3904761904761904);
            eq(4, (new RandomOption()).getProbability(7, new int[]{3, 5, 1, 0, 2, 6, 4}, new int[]{0, 2, 4, 6, 1, 5, 3}), 0.09166666666666667);
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, int a, int b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }

    private static void eq(int n, char a, char b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected '" + b + "', received '" + a + "'.");
    }

    private static void eq(int n, long a, long b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected \"" + b + "L, received " + a + "L.");
    }

    private static void eq(int n, boolean a, boolean b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }

    private static void eq(int n, double a, double b) {
        if (eq(a, b))
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }

    private static void eq(int n, String a, String b) {
        if (a != null && a.equals(b))
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected \"" + b + "\", received \"" + a + "\".");
    }

    private static void eq(int n, int[] a, int[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, long[] a, long[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, double[] a, double[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (!eq(a[i], b[i])) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, String[] a, String[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (!a[i].equals(b[i])) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void print(int a) {
        System.err.print(a + " ");
    }

    private static void print(long a) {
        System.err.print(a + "L ");
    }

    private static void print(String s) {
        System.err.print("\"" + s + "\" ");
    }

    private static void print(int[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(long[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(double[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(String[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print("\"" + rs[i] + "\"");
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void nl() {
        System.err.println();
    }

    private static double eps = 1e-9;

    private static boolean eq(double a, double b) {
        return abs(a - b) <= eps;
    }
// END CUT HERE
}
