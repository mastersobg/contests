import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class BitwiseAnd {


    private final int BITS = 60;

    public long[] lexSmallest(long[] subset, int N) {
        if (!checkInit(subset)) return new long[]{};
        List<Integer>[] bits = new ArrayList[BITS];
        for (int i = 0; i < bits.length; ++i) {
            bits[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < subset.length; ++i) {
            long a = subset[i];
            for (int j = 0; j < BITS; ++j) {
                if (checkBit(a, j)) {
                    bits[j].add(i);
                }
            }
        }

        int add = N - subset.length;
        List<Long> ret = new ArrayList<Long>(add);
        for (int it = 0; it < add; ++it) {
            boolean notLast = it < add - 1;
            BitSet set = new BitSet(subset.length + it);

            long number = 0;
            for (int i = 0; i < BITS; ++i) {
                if (bits[i].size() == 1) {
                    int idx = bits[i].get(0);
                    if (!set.get(idx)) {
                        number = setBit(number, i);
                        set.set(idx);
                    }
                }
            }

            if (set.cardinality() != subset.length + it) {
                return new long[]{};
            }

            if (notLast) {
                int needToAdd = N - Long.bitCount(number) - 1;
                for (int i = 0; i < BITS; ++i) {
                    if (bits[i].size() == 0) {
                        number = setBit(number, i);
                        --needToAdd;
                        if (needToAdd == 0)
                            break;
                    }
                }
                if (needToAdd != 0) {
                    return new long[]{};
                }
            }
            ret.add(number);


            for (int j = 0; j < BITS; ++j) {
                if (checkBit(number, j)) {
                    bits[j].add(subset.length + it);
                }
            }
        }

        for (long a : subset)
            ret.add(a);
        Collections.sort(ret);
        long[] ans = new long[ret.size()];
        for (int i = 0; i < ret.size(); ++i) {
            ans[i] = ret.get(i);
        }
        return ans;

//      for (long a : subset) {
//          System.out.printf("%5d: %10s\n", a, Long.toString(a, 2));
//      }
//      System.out.println();
//      return new long[]{};
    }

    boolean checkBit(long a, long bit) {
        return (a & (1L << bit)) != 0;
    }

    long setBit(long a, long bit) {
        return a | (1L << bit);
    }

    boolean checkInit(long[] v) {
        for (int i = 0; i < v.length; ++i) {
            for (int j = i + 1; j < v.length; ++j) {
                if ((v[i] & v[j]) <= 0L)
                    return false;
                for (int k = j + 1; k < v.length; ++k) {
                    if ((v[i] & v[j] & v[k]) != 0L)
                        return false;
                }
            }
        }
        return true;
    }

    // BEGIN CUT HERE
    boolean DEBUG = false;

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
            boolean DEBUG = true;
            eq(0, (new BitwiseAnd()).lexSmallest(new long[]{14L, 20L}, 3), new long[]{14L, 18L, 20L});
            eq(1, (new BitwiseAnd()).lexSmallest(new long[]{11L, 17L, 20L}, 4), new long[]{});
            eq(2, (new BitwiseAnd()).lexSmallest(new long[]{99L, 157L}, 4), new long[]{99L, 157L, 262L, 296L});
            eq(3, (new BitwiseAnd()).lexSmallest(new long[]{1152921504606846975L}, 3), new long[]{});
            eq(4, (new BitwiseAnd()).lexSmallest(new long[]{}, 5), new long[]{15L, 113L, 402L, 676L, 840L});
            eq(5, (new BitwiseAnd()).lexSmallest(new long[]{1L, 3L, 5L, 7L, 9L, 11L}, 6), new long[]{});
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
