package main;

import java.util.*;

import static java.lang.Math.*;

public class LittleElephantAndString {


    public int getNumber(String s1, String s2) {
        int[] cnt = new int[26];
        for (int i = 0; i < s1.length(); ++i)
            cnt[s1.charAt(i) - 'A']++;
        for (int i = 0; i < s2.length(); ++i)
            cnt[s2.charAt(i) - 'A']--;
        for (int i = 0; i < 26; ++i)
            if (cnt[i] != 0)
                return -1;

        int pos = s1.length() - 1;
        int count = 0;
        for (int i = s2.length() - 1; pos >= 0; --i, --pos) {
            if (s1.charAt(pos) != s2.charAt(i)) {
                while (pos >= 0 && s1.charAt(pos) != s2.charAt(i)) {
                    --pos;
                    ++count;
                }
            }
        }
        return count;
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
            eq(0, (new LittleElephantAndString()).getNumber("ABC", "CBA"), 2);
            eq(1, (new LittleElephantAndString()).getNumber("A", "B"), -1);
            eq(2, (new LittleElephantAndString()).getNumber("AAABBB", "BBBAAA"), 3);
            eq(3, (new LittleElephantAndString()).getNumber("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ZYXWVUTSRQPONMLKJIHGFEDCBA"), 25);
            eq(4, (new LittleElephantAndString()).getNumber("A", "A"), 0);
            eq(5, (new LittleElephantAndString()).getNumber("DCABA", "DACBA"), 2);
            eq(5, (new LittleElephantAndString()).getNumber("CCAC", "ACCC"), 1);
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
