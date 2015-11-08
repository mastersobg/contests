package com.ivangorbachev;

import com.ivangorbachev.io.Reader;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class A {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        String s = in.readLine();
        int a = 0;
        for (char c : s.toCharArray())
            if (c == '(') ++a;
            else if (c == ')') --a;
        out.println(-a);
    }
}
