import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class IsItASquare {
    int[] x;
    int[] y;

    public String isSquare(int[] x, int[] y) {
        this.x = x;
        this.y = y;
        int[] order = Permutations.initArray(4);
        boolean found = false;
        do {
            if (check(order)) {
                found = true;
                break;
            }
        } while (Permutations.nextPermutation(order));
        if (found) {
            return "It's a square";
        } else {
            return "Not a square";
        }
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

class Permutations {
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
