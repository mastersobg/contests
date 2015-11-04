import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.List;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.StringTokenizer;
import java.util.Arrays;

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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        int n;
        LongLongPair[] coef;
        long x1;
        long x2;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            n = in.readInt();
            x1 = in.readLong();
            x2 = in.readLong();
            coef = new LongLongPair[n];
            LongLongPair[] y = new LongLongPair[n];
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

        public long readLong() {
            return Long.valueOf(readString());
        }

    }

    static class LongLongPair {
        private long x;
        private long y;

        public LongLongPair() {
        }

        public LongLongPair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }

    }
}

