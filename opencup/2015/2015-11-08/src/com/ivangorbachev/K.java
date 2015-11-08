package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class K {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        int xbase = in.readInt();
        int ybase = in.readInt();
        for (int i = 0; i < n; ++i) {
            int x = in.readInt() - xbase;
            int y = in.readInt() - ybase;
            int nx = y;
            int ny = -x;
            nx = nx - ny;
            out.println((nx + xbase) + " " +(ny + ybase));
        }
    }
}
