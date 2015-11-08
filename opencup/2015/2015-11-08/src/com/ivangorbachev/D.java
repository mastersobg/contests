package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.IntIntPair;
import com.ivangorbachev.misc.Pair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class D {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        int []v1 = in.readIntArray(n);
        int []v2 = in.readIntArray(n);
        int r1 = f(v1, v2);
        int r2 = f(v2, v1);
        out.println(r1 + " " + r2);
    }

    private int f(int[] v1, int[] v2) {
        TreeSet<IntIntPair> set = new TreeSet<>();
        for (int i = 0; i < v2.length; ++i)
            set.add(new IntIntPair(v2[i], i));
        int ret = 0;
        for (int a : v1) {
            IntIntPair p = new IntIntPair(a, Integer.MAX_VALUE);
            IntIntPair found = set.lower(p);
            if (found != null) {
                ++ret;
                set.remove(found);
            }
        }
        return ret;
    }
}
