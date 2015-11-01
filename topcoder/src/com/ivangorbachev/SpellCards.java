package com.ivangorbachev;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class SpellCards {

    public int maxDamage(int[] level, int[] damage) {
        int ret = 0;
        int n = level.length;
        long mask = (1L << (long) n) - 1L;
//        for (int i = 0; i < n; ++i)
//            if (level[i] == 1) {
//                ret += damage[i];
//                mask ^= (1L << (long) i);
//            }
        return f(level, damage, mask, 0) + ret;
    }

    int f(int[] l, int[] d, long mask, int p) {
        int bits = Long.bitCount(mask);
        int ret = 0;
        for (int i = p; i < l.length; ++i)
            if ((mask & (1L << (long) i)) != 0L) {
                if (bits >= l[i]) {
                    long nmask = delete(l, d, mask, i);
                    ret = max(ret, f(l, d, nmask, i + 1) + d[i]);
                }
            }
        return ret;
    }

    boolean canDelete(int[] l, int[] d, long mask, int pos) {
        int total = Long.bitCount(mask);
        if (total < d[pos])
            return false;
        return true;
    }

    long delete(int[] l, int[] d, long mask, int pos) {
        int cnt = l[pos];
        long ret = mask;
        for (int i = pos, j = 0; j < cnt; i = (i + 1) % l.length) {
            if ((mask & (1L << (long) i)) != 0l) {
                ret ^= (1L << (long) i);
                ++j;
            }
        }
        return ret;
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0, (new SpellCards()).maxDamage(new int[]{1, 1, 1}, new int[]{10, 20, 30}), 60);
            eq(1, (new SpellCards()).maxDamage(new int[]{3, 3, 3}, new int[]{10, 20, 30}), 30);
            eq(2, (new SpellCards()).maxDamage(new int[]{4, 4, 4}, new int[]{10, 20, 30}), 0);
            eq(3, (new SpellCards()).maxDamage(new int[]{50, 1, 50, 1, 50}, new int[]{10, 20, 30, 40, 50}), 60);
            eq(4, (new SpellCards()).maxDamage(new int[]{2, 1, 1}, new int[]{40, 40, 10}), 80);
            eq(5, (new SpellCards()).maxDamage(new int[]{1, 2, 1, 1, 3, 2, 1}, new int[]{10, 40, 10, 10, 90, 40, 10}), 170);
            eq(6, (new SpellCards()).maxDamage(new int[]{1, 2, 2, 3, 1, 4, 2}, new int[]{113, 253, 523, 941, 250, 534, 454}), 1918);
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
