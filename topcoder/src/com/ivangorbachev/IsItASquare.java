package com.ivangorbachev;

import com.ivangorbachev.misc.IntIntPair;

import static com.ivangorbachev.geom.GeomUtil.sqrDist;
import static com.ivangorbachev.misc.Permutations.initArray;
import static com.ivangorbachev.misc.Permutations.nextPermutation;


public class IsItASquare {

    int []x;
    int []y;
    public String isSquare(int[] x, int[] y) {
        this.x = x;
        this.y = y;
        int []order = initArray(4);
        boolean found = false;
        do {
            if (check(order)) {
                found = true;
                break;
            }
        } while (nextPermutation(order));
        if (found) {
            return "It's a square";
        } else {
            return "Not a square";
        }
    }

    private boolean check(int[] order) {
        IntIntPair[]pts = new IntIntPair[4];
        for (int i = 0; i < 4; i++) {
            pts[i] = new IntIntPair(x[order[i]], y[order[i]]);
        }
        return sqrDist(pts[0], pts[1]) == sqrDist(pts[2], pts[3]) &&
                sqrDist(pts[1], pts[2]) == sqrDist(pts[0], pts[3]) &&
                sqrDist(pts[0], pts[1]) == sqrDist(pts[1], pts[2]) &&
                sqrDist(pts[0], pts[2]) == sqrDist(pts[1], pts[3]);
    }
}
