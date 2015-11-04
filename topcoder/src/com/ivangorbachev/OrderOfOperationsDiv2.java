package com.ivangorbachev;

import com.ivangorbachev.misc.IntValue;

import java.util.*;
import java.io.*;

import static com.ivangorbachev.geom.GeomUtil.sqr;
import static com.ivangorbachev.misc.Permutations.initArray;
import static com.ivangorbachev.misc.Permutations.nextPermutation;
import static com.ivangorbachev.util.BitsUtil.checkBit;
import static com.ivangorbachev.util.BitsUtil.setBit;
import static java.lang.Math.*;

public class OrderOfOperationsDiv2 {

    int []d;

    public int minTime(String[] s) {
        d = new int[1 << s.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        return rec(0, 0, s);
    }

    int rec(int mask, int used, String []s) {
        if (Integer.bitCount(mask) == s.length) {
            return 0;
        }
        if (d[mask] != Integer.MAX_VALUE)
            return d[mask];
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < s.length; ++i)
            if (!checkBit(mask, i)) {
                IntValue v = new IntValue();
                ret = min(ret, calc(s[i], used, v) +
                        rec(setBit(mask, i), v.getValue(), s)
                );
            }
        return d[mask] = ret;
    }

    private int calc(String instruction, int mask, IntValue v) {
        int cnt = 0;
        for (int j = 0; j < instruction.length(); ++j)
            if (instruction.charAt(j) == '1') {
                if (!checkBit(mask, j)) {
                    ++cnt;
                    mask = setBit(mask, j);
                }
            }
        v.setValue(mask);
        return sqr(cnt);
    }
}
