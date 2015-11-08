package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.Pair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class B {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        long n = in.readLong();
        long m = in.readLong();
        if (n > m) {
            long a = n - m - 1;
            out.println(6L * (1L << a));
        } else {
            long a = m - n;
            out.println(6L * (1L << a));
        }

    }
}
