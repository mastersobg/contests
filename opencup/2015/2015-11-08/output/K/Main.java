import java.io.InputStream;
import java.io.*;
import java.util.Locale;
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
        Locale.setDefault(Locale.US);
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        K solver = new K();
        solver.solve(1, in, out);
        out.close();
    }

    static class K {
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
                out.println((nx + xbase) + " " + (ny + ybase));
            }
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

    }
}

