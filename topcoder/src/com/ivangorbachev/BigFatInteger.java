package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class BigFatInteger {


    List<Integer> primes() {
        boolean b[] = new boolean[1000001];
        b[0] = b[1] = true;
        for (int i = 2; i < b.length; ++i)
            if (!b[i]) {
                for (int j = i + i; j < b.length; j += i) {
                    b[j] = true;
                }
            }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < b.length; ++i)
            if (!b[i])
                ret.add(i);
        return ret;
    }

    List<Integer> squares() {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 2; i * i <= 1000000; ++i) {
            ret.add(i * i);
        }
        return ret;
    }

    public int minOperations(int A, int B) {
        long time = System.currentTimeMillis();
        List<Integer> primes = primes();
        int[] d = new int[max(A, B) + 1];
        Arrays.fill(d, 1 << 30);
        d[1] = 0;
//        for (int i = 1; i < d.length; ++i) {
//            if (d[i] < (1 << 30)) {
//                for (int a : primes) {
//                    int q = a * i;
//                    if (q >= d.length) break;
//                    d[q] = min(d[q], d[i] + 1);
//                }
//
//                for (int j = 2; j * j <= i; ++j)
//                    if (i % j == 0) {
//                        if (i * j >= d.length) continue;
//                        d[i * j] = min(d[i * j], d[i] + 1);
//                    }
//            }
//        }
        for (int i = 2; i < d.length; ++i) {
            for (int a : primes) {
                if (a > i) break;
                if (i % a == 0) {
                    d[i] = min(d[i], d[i / a] + 1);
                }
            }

            for (int j = 2; j * j <= i; ++j)
                if (i % j == 0 && (i / j) % j == 0) {
                    d[i] = min(d[i], d[i / j] + 1);
                }
        }
        System.out.println(System.currentTimeMillis() - time);
        return d[A] + count(B);
    }

    int count(int b) {
        int ret = 0;
        while (b > 1) {
            int a = b / 2;
            b = a + b % 2;
            ++ret;
        }
        return ret;
    }


    // BEGIN CUT HERE
    static boolean DEBUG = false;

    void dbg(Object... objs) {
        if (!DEBUG) {
            return;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[])
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        try {
            DEBUG = true;
            eq(0, (new BigFatInteger()).minOperations(6, 1), 2);
            eq(1, (new BigFatInteger()).minOperations(162, 1), 4);
            eq(2, (new BigFatInteger()).minOperations(999983, 9), 5);
            eq(3, (new BigFatInteger()).minOperations(360, 8), 8);
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
