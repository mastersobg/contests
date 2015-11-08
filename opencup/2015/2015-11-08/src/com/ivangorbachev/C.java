package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.IntIntPair;
import com.ivangorbachev.misc.LongLongPair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import static com.ivangorbachev.util.Dbg.dbg;
import static java.lang.Math.*;

public class C {

    Set<LongLongPair> set = new HashSet<>();
    final long max = 1L << 20L;

    public void solve(int testNumber, Reader in, PrintWriter out) {
        go(0L, 0L, 1L);
        int cnt = 0;
        for (int i = -50; i <= 50; ++i)
            for (int j = -50; j <= 50; ++j)
                if (set.contains(new LongLongPair(i, j))) {
                    ++cnt;
                    dbg(i, j);
                }
        dbg("total", cnt);
    }

    void go(long x, long y, long step) {
        if (set.contains(new LongLongPair(x, y))) {
            return;
        }
        if (step * 3 < max)
            go(x, y, step * 3);
        set.add(new LongLongPair(x, y));
        if (x + step < max && step * 3 < max)
            go(x + step, y, step * 3);
        if (x - step > -max && step * 3 < max)
            go(x - step, y, step * 3);
        if (y + step < max && step * 3 < max)
            go(x, y + step, step * 3);
        if (y - step > -max && step * 3 < max)
            go(x, y - step, step * 3);
    }
}
