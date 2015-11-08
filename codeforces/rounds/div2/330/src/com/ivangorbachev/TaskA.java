package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class TaskA {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; ++j) {
                int a = in.readInt();
                int b = in.readInt();
                if (a == 1 || b == 1) {
                    ++ret;
                }
            }
        }
        out.println(ret);
    }
}
