package com.ivangorbachev;

import com.ivangorbachev.misc.IntIntPair;

import java.util.*;
import java.io.*;

import static com.ivangorbachev.geom.GeomUtil.sqrDist;
import static java.lang.Math.*;

public class PointDistance {
    public int[] findPoint(int x1, int y1, int x2, int y2) {
        IntIntPair p1 = new IntIntPair(x1, y1);
        IntIntPair p2 = new IntIntPair(x2, y2);
        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; ++j) {
                IntIntPair p3 = new IntIntPair(i, j);
                if (!p3.equals(p1) && !p3.equals(p2)) {
                    if (sqrDist(p1, p3) > sqrDist(p2, p3)) {
                        return new int[] {i, j};
                    }
                }
            }
        }
        return new int[0];
    }
}
