package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.util.ArraysUtil;
import com.ivangorbachev.util.BitsUtil;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import static com.ivangorbachev.util.BitsUtil.checkBit;
import static com.ivangorbachev.util.BitsUtil.isEven;
import static com.ivangorbachev.util.BitsUtil.setBit;
import static com.ivangorbachev.util.Dbg.dbg;
import static java.lang.Math.*;

public class TaskC {

    int[] v;

    public void solve(int testNumber, Reader in, PrintWriter out) {
        v = in.readIntArray();
        int p1 = 0, p2 = v.length - 1;
        int len = v.length;
        while (len > 2) {
            int abs1 = abs(v[p1] - v[p1 + 1]);
            int abs2 = abs(v[p2] - v[p2 - 1]);
            if (abs1 > abs2) ++p1;
            else
                --p2;
            len -= 2;
        }
        dbg("my solution", p1, p2);
        out.println(abs(v[p1] - v[p2]));
//        out.println(abs(v[0] - v[a]));
    }

    int naive(int[] v) {
        this.v = v;
        d = new int[2][1 << v.length];
        ArraysUtil.fill(d, -1);
        return rec(0, 0);
    }

    int[][] d;

    int rec(int mask, int step) {
        if (d[step][mask] != -1)
            return d[step][mask];
        if (v.length - Integer.bitCount(mask) == 2) {
            int i1 = -1, i2 = -1;
            for (int i = 0; i < v.length; i++) {
                if (!checkBit(mask, i)) {
                    if (i1 == -1)
                        i1 = i;
                    else i2 = i;
                }
            }
            if (abs(v[i1] - v[i2]) == 23)
                dbg(abs(v[i1] - v[i2]), i1, i2);
            return d[step][mask] = abs(v[i1] - v[i2]);
        }
        int ret = isEven(step) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (int i = 0; i < v.length; i++) {
            if (!checkBit(mask, i)) {
                int a = rec(setBit(mask, i), step ^ 1);
                if (isEven(step))
                    ret = min(ret, a);
                else ret = max(ret, a);
            }
        }
        return d[step][mask] = ret;
    }
}
