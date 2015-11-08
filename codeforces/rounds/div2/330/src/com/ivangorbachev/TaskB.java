package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.numbers.NumbersUtil;
import com.ivangorbachev.util.ArraysUtil;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class TaskB {

    final int mod = 1000000000 + 7;

    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n / k);
        int[] b = in.readIntArray(n / k);
        int ret = 1;
        for (int i = 0; i < a.length; ++i) {
            ret = NumbersUtil.multiply(ret, go(k, a[i], b[i]), mod);
        }
        out.println(ret);
    }

    private int go(int len, int div, int digit) {
        long ret = 0;
        long pow = 1;
        for (int i = 1; i < len; ++i) {
            pow *= 10L;
        }
        for (int i = 0; i < 10; ++i)
            if (i != digit) {
                long x = i * pow % div;
                x = (div - x) % div;
                long y = pow - x;
                if (y < 0)
                    continue;
                long cnt = y / div + 1;
                if (y % div == 0)
                    --cnt;
                ret = (cnt + ret) % mod;
            }
        return (int) ret;
    }


}
