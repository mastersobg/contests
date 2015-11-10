import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Map;
import java.io.BufferedReader;
import java.util.List;
import java.util.Collections;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Map.Entry;

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
        Task1837_1 solver = new Task1837_1();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1837_1 {
        HashMap<String, Integer> names = new HashMap<>();
        int isenbaevIdx = -1;
        List<Integer>[] g;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            int[][] v = new int[n][3];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < 3; ++j) {
                    String s = in.readString();
                    int idx = getIdx(s);
                    isenbaev(s, idx);
                    v[i][j] = idx;
                }
            }
            g = new ArrayList[names.size()];
            for (int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<>();
            }
            for (int it = 0; it < n; ++it) {
                for (int i = 0; i < 3; ++i)
                    for (int j = 0; j < 3; ++j)
                        if (i != j) {
                            g[v[it][i]].add(v[it][j]);
                        }
            }

            int[] d = new int[names.size()];
            Arrays.fill(d, Integer.MAX_VALUE);
            if (isenbaevIdx != -1)
                bfs(d, isenbaevIdx);
//            dfs(g, d, isenbaevIdx, 0);
            List<Pair<String, Integer>> ret = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : names.entrySet()) {
                ret.add(new Pair<>(entry.getKey(), d[entry.getValue()]));
            }
            Collections.sort(ret, (a, b) -> a.getX().compareTo(b.getX()));
            for (Pair<String, Integer> p : ret) {
                if (p.getY() == Integer.MAX_VALUE) {
                    out.println(p.getX() + " undefined");
                } else
                    out.println(p.getX() + " " + p.getY());
            }
        }

        private void bfs(int[] d, int v) {
            d[v] = 0;
            int[] q = new int[500];
            int b = 0, e = 0;
            q[e++] = v;
            for (; b < e; ++b) {
                v = q[b];
                for (int u : g[v]) {
                    if (d[u] > d[v] + 1) {
                        d[u] = d[v] + 1;
                        q[e++] = u;
                    }
                }
            }
        }

        void isenbaev(String s, int idx) {
            if ("Isenbaev".equals(s)) {
                isenbaevIdx = idx;
            }
        }

        int getIdx(String s) {
            Integer i = names.get(s);
            if (i == null) {
                names.put(s, names.size());
                return names.size() - 1;
            }
            return i;
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
            return Integer.parseInt(readString());
        }

    }

    static class Pair<K, V> {
        public K x;
        public V y;

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


        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (x != null ? !x.equals(pair.x) : pair.x != null) return false;
            return !(y != null ? !y.equals(pair.y) : pair.y != null);

        }


        public int hashCode() {
            int result = x != null ? x.hashCode() : 0;
            result = 31 * result + (y != null ? y.hashCode() : 0);
            return result;
        }


        public String toString() {
            return "[x = " + (x == null ? "null" : x.toString()) +
                    " y = " + (y == null ? "null" : y.toString())
                    + "]";
        }

    }
}

