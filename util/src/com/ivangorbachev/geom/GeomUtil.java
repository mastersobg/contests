package com.ivangorbachev.geom;

import com.ivangorbachev.misc.IntIntPair;

/**
 * @author Ivan Gorbachev
 */
public class GeomUtil {

    public static double sqr(double x) {
        return x * x;
    }

    public static int sqr(int x) {
        return x * x;
    }

    public static long sqr(long x) {
        return x * x;
    }

    public static int sqrDist(IntIntPair p1, IntIntPair p2) {
        return sqr(p1.getX() - p2.getX()) + sqr(p1.getY() - p2.getY());
    }

}
