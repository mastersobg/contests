import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class PointDistance {
    public int[] findPoint(int x1, int y1, int x2, int y2) {
        IntIntPair p1 = new IntIntPair(x1, y1);
        IntIntPair p2 = new IntIntPair(x2, y2);
        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; ++j) {
                IntIntPair p3 = new IntIntPair(i, j);
                if (!p3.equals(p1) && !p3.equals(p2)) {
                    if (GeomUtil.sqrDist(p1, p3) > GeomUtil.sqrDist(p2, p3)) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[0];
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
