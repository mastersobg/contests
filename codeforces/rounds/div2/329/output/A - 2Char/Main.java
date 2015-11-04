import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = in.readLine();
            }
            int ret = 0;
            for (int i = 0; i < 26; ++i)
                for (int j = 0; j < 26; ++j) {
                    char c1 = (char) (i + 'a');
                    char c2 = (char) (j + 'a');
                    ret = Math.max(ret, count(words, c1, c2));
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

    static class Reader {
        private BufferedReader in;
        private StringTokenizer st;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

        public String readString() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            return st.nextToken();
        }

        public int readInt() {
            return Integer.valueOf(readString());
        }

        public String readLine() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

