import java.io.InputStream;
import java.io.*;
import java.util.Locale;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.List;
import java.util.Collections;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

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
        G solver = new G();
        solver.solve(1, in, out);
        out.close();
    }

    static class G {
        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            HashMap<String, Integer> map = new HashMap<>();
            final HashMap<String, Integer> order = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String s = in.readString();
                StringTokenizer st = new StringTokenizer(s, ".");
                String s1 = st.nextToken();
                String s2 = st.nextToken();
                if (map.containsKey(s2)) {
                    map.put(s2, map.get(s2) + 1);
                } else {
                    map.put(s2, 1);
                    order.put(s2, i);
                }
            }
            List<Pair<String, Integer>> list = new ArrayList<>();
            for (String key : map.keySet()) {
                list.add(new Pair<String, Integer>(key, map.get(key)));
            }
            Collections.sort(list, new Comparator<Pair<String, Integer>>() {

                public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                    int p1 = order.get(o1.getX());
                    int p2 = order.get(o2.getX());
                    return p1 - p2;
                }
            });

            for (Pair p : list) {
                out.println(p.getX() + ": " + p.getY());
            }
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

    }
}

