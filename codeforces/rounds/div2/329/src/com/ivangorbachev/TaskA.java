package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class TaskA {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        String []words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.readLine();
        }
        int ret = 0;
        for (int i = 0; i < 26; ++i)
            for (int j = 0; j < 26; ++j) {
                char c1 = (char) (i + 'a');
                char c2 = (char) (j + 'a');
                ret = max(ret, count(words, c1, c2));
            }
        out.println(ret);
    }

    private int count(String[] words, char c1, char c2) {
        int ret = 0;
        for (String s : words) {
            boolean ok = true;
            for (int i = 0; i < s.length(); ++i)
                if (s.charAt(i) == c1 || s.charAt(i) == c2) {

                } else {
                    ok = false;
                    break;
                }
            if (ok)
                ret += s.length();
        }
        return ret;
    }
}
