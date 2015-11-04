package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.LongLongPair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class TaskB {
    int n;
    LongLongPair[] coef;
    long x1, x2;

    public void solve(int testNumber, Reader in, PrintWriter out) {
        n = in.readInt();
        x1 = in.readLong();
        x2 = in.readLong();
        coef = new LongLongPair[n];
        LongLongPair []y = new LongLongPair[n];
        for (int i = 0; i < n; i++) {
            coef[i] = new LongLongPair(in.readLong(), in.readLong());
        }
        for (int i = 0; i < n; i++) {
            y[i] = new LongLongPair();
            y[i].setX(coef[i].getX() * x1 + coef[i].getY());
            y[i].setY(coef[i].getX() * x2 + coef[i].getY());
        }

        Arrays.sort(y, (a, b) -> Long.compare(a.getX(), b.getX()));
        TreeSet<Long> set = new TreeSet<>();
        List<LongLongPair> list = new ArrayList<>();
        LongLongPair prev = null;
        boolean found = false;
        for (LongLongPair pair : y) {
            if (prev != null && pair.getX() != prev.getX()) {
                set.addAll(list.stream().map(LongLongPair::getY).collect(Collectors.toList()));
                list = new ArrayList<>();
            }
            if (set.size() > 0 && set.last() > pair.getY()) {
                found = true;
                break;
            }
            list.add(pair);
            prev = pair;
        }
        if (found)
            out.println("YES");
        else out.println("NO");

    }
}
