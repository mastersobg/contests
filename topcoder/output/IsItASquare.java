import java.io.*;
import java.util.*;

import static java.lang.Math.*;

import java.util.Arrays;

public class IsItASquare {
    int[] x;
    int[] y;

    public String isSquare(int[] x, int[] y) {
        testP();
        this.x = x;
        this.y = y;
        Permutations<Boolean> p = new Permutations<Boolean>(4, false);
        boolean ret = p.generate((order, r, terminate) -> {
            if (check(order)) {
                terminate.setValue(true);
                return true;
            }
            return false;

        });
        if (ret) {
            return "It's a square";
        } else {
            return "Not a square";
        }
    }

    private void testP() {
        int[] order = P.initArray(2);
        do {
            Dbg.dbg(order);
        } while (P.nextPermutation(order));
    }

    private boolean check(int[] order) {
        IntIntPair[] pts = new IntIntPair[4];
        for (int i = 0; i < 4; i++) {
            pts[i] = new IntIntPair(x[order[i]], y[order[i]]);
        }
        return GeomUtil.sqrDist(pts[0], pts[1]) == GeomUtil.sqrDist(pts[2], pts[3]) &&
                GeomUtil.sqrDist(pts[1], pts[2]) == GeomUtil.sqrDist(pts[0], pts[3]) &&
                GeomUtil.sqrDist(pts[0], pts[1]) == GeomUtil.sqrDist(pts[1], pts[2]) &&
                GeomUtil.sqrDist(pts[0], pts[2]) == GeomUtil.sqrDist(pts[1], pts[3]);
    }

}

class P {
    public static int[] initArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = i;
        }
        return ret;
    }

    public static boolean nextPermutation(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return false;
        }
        int i;
        for (i = arr.length - 2; i >= 0; --i) {
            if (arr[i] < arr[i + 1]) {
                break;
            }
        }
        if (i < 0) {
            return false;
        }
        int j;
        for (j = arr.length - 1; j >= 0; --j) {
            if (arr[j] > arr[i]) {
                break;
            }
        }
        ArraysUtil.swap(arr, i, j);
        ArraysUtil.reverse(arr, i + 1, arr.length - 1);
        return true;
    }

}

class Dbg {
    private static boolean DEBUG_ENABLED;

    static {
        try {
            DEBUG_ENABLED = System.getProperty("LOCAL_DEBUG_ENABLED") != null;
        } catch (Exception e) {
            DEBUG_ENABLED = false;
        }
    }

    public static void dbg(Object... objs) {
        if (!DEBUG_ENABLED) {
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
        System.err.flush();
    }

    public static String arrayToString(Object o) {
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

}

class GeomUtil {
    public static int sqr(int x) {
        return x * x;
    }

    public static int sqrDist(IntIntPair p1, IntIntPair p2) {
        return sqr(p1.getX() - p2.getX()) + sqr(p1.getY() - p2.getY());
    }

}

class ArraysUtil {
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void reverse(int[] arr, int i, int j) {
        for (int p0 = i, p1 = j; p0 < p1; ++p0, --p1) {
            swap(arr, p0, p1);
        }
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

class BooleanValue {
    private boolean value;

    public BooleanValue() {
    }

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

}

class Permutations<T> {
    private BooleanValue terminate;
    private int n;
    private T result;

    public Permutations(int n, T defaultResult) {
        this.n = n;
        this.result = defaultResult;
        terminate = new BooleanValue(false);
    }

    public T generate(Callback<T> f) {
        go(0, n, 0, new int[n], f);
        return result;
    }

    private boolean go(int idx, int n, int mask,
                       int[] order, Callback<T> f) {
        if (idx == n) {
            result = f.test(order, result, terminate);
            return terminate.isValue();
        }
        for (int i = 0; i < n; i++) {
            if (!BitsUtil.checkBit(mask, i)) {
                order[idx] = i;
                if (go(idx + 1, n, BitsUtil.setBit(mask, i), order, f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public interface Callback<T> {
        T test(int[] order, T ret, BooleanValue terminate);

    }

}

class BitsUtil {
    public static boolean checkBit(int number, int bit) {
        return (number & (1 << bit)) != 0;
    }

    public static int setBit(int number, int bit) {
        return number | (1 << bit);
    }

}
