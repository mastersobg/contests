package main;

import main.Reader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        in.ni();
        int n = in.ni();
        String s = in.ns();
        char []c = new char[26];
        for (int i = 0; i < 26; ++i) {
            c[i] = (char)(i + 'a');
        }
        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            char c1 = str.charAt(0);
            char c2 = str.charAt(2);

            for (int j = 0; j < 26; ++j) {
                if (c[j] == c1) {
                    c[j] = c2;
                } else if (c[j] == c2) {
                    c[j] = c1;
                }
            }
        }

        char []ret = s.toCharArray();
        for (int i = 0; i < ret.length; ++i) {
            char cs = ret[i];
            ret[i] = c[cs - 'a'];
        }
        out.println(new String(ret));
    }
}
