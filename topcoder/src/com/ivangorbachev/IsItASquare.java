package com.ivangorbachev;

import com.ivangorbachev.misc.IntIntPair;
import com.ivangorbachev.misc.Permutations;

import static com.ivangorbachev.geom.GeomUtil.sqrDist;
import static com.ivangorbachev.misc.P.initArray;
import static com.ivangorbachev.misc.P.nextPermutation;
import static com.ivangorbachev.util.Dbg.dbg;


public class IsItASquare {

    int []x;
    int []y;
    public String isSquare(int[] x, int[] y) {
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
