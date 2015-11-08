import java.io.InputStream;
import java.io.*;
import java.util.Locale;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.TreeSet;
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
        D solver = new D();
        solver.solve(1, in, out);
        out.close();
    }

    static class D {
        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            int[] v1 = in.readIntArray(n);
            int[] v2 = in.readIntArray(n);
            int r1 = f(v1, v2);
            int r2 = f(v2, v1);
            out.println(r1 + " " + r2);
        }

        private int f(int[] v1, int[] v2) {
            TreeSet<Pair<Integer, Integer>> set =
                    new TreeSet<Pair<Integer, Integer>>(
                            new Comparator<Pair<Integer, Integer>>() {

                                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                                    if (o1.getX().equals(o2.getX()))
                                        return o1.getY().compareTo(o2.getY());
                                    return o1.getX().compareTo(o2.getX());
                                }
                            });
            for (int i = 0; i < v2.length; ++i)
                set.add(new Pair<Integer, Integer>(v2[i], i));
            int ret = 0;
            for (int a : v1) {
                Pair<Integer, Integer> p = new Pair<>(a, Integer.MAX_VALUE);
                Pair<Integer, Integer> found = set.lower(p);
                if (found != null) {
                    ++ret;
                    set.remove(found);
                }
            }
            return ret;
        }

    }

    static class Pair<K, V> {
        private K x;
        private V y;

        public Pair() {
        }

        public Pair(K x, V y) {
            this.x = x;
            this.y = y;
        }

        public K getX() {
            return x;
        }

        public V getY() {
            return y;
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

        public int[] readIntArray(int size) {
            int[] ret = new int[size];
            for (int i = 0; i < size; i++) {
                ret[i] = readInt();
            }
            return ret;
        }

    }
}

