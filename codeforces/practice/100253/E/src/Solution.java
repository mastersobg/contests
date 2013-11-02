import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, m;
    int[] v;
    int[] p;

    static class Pair implements Comparable<Pair> {
        int pos;
        int value;

        Pair(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            return value - o.value;
        }
    }

    static int INF = 1 << 29;

    static class SegmentTree {

        int len;
        Pair[] t;

        SegmentTree(int len) {
            this.len = len;
            t = new Pair[len * 4];
            for (int i = 0; i < t.length; ++i) {
                t[i] = new Pair(i, INF);
            }
        }

        void update(Pair p) {
            update(0, 0, len - 1, p);
        }

        void update(int idx, int l, int r, Pair p) {
            //ass(0 <= l && l <= p.pos && p.pos <= r && r < t.length);
            if (l == r) {
                t[idx] = p;
            } else {
                int m = (l + r) >> 1;
                if (p.pos <= m)
                    update(idx * 2 + 1, l, m, p);
                else
                    update(idx * 2 + 2, m + 1, r, p);
                t[idx] = min(t[idx * 2 + 1], t[idx * 2 + 2]);
            }
        }

        void ass(boolean a) {
            if (!a)
                throw new RuntimeException();
        }

        Pair getMin(int pos) {
            return getMin(0, 0, len - 1, pos, len - 1);
        }

        Pair getMin(int idx, int l, int r, int lf, int rg) {
            //ass(0 <= l && l <= lf && lf <= rg && rg <= r && r < t.length);
            if (l == lf && r == rg) {
                return t[idx];
            } else {
                int m = (l + r) >> 1;
                if (rg <= m)
                    return getMin(idx * 2 + 1, l, m, lf, rg);
                else if (lf > m) {
                    return getMin(idx * 2 + 2, m + 1, r, lf, rg);
                } else {
                    Pair p1 = getMin(idx * 2 + 1, l, m, lf, m);
                    Pair p2 = getMin(idx * 2 + 2, m + 1, r, m + 1, rg);
                    return min(p1, p2);
                }
            }
        }

        Pair min(Pair p1, Pair p2) {
            if (p1.compareTo(p2) < 0) return p1;
            else return p2;
        }
    }

    void solve() throws IOException {
        n = ni();
        m = ni();
        v = new int[n];
        for (int i = 0; i < n; ++i)
            v[i] = ni();
        p = new int[m];
        for (int i = 0; i < m; ++i) {
            p[i] = ni();
        }

        SegmentTree t = new SegmentTree(n);
        Fenwick f = new Fenwick(1000010);
        long total = 0;
        int[] count = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            count[i] = f.count(v[i] - 1);
            total += count[i];
            f.add(v[i]);
            t.update(new Pair(i, v[i]));
        }

        out.println(total);
        for (int i = 0; i < m; ++i) {
            if (v[p[i] - 1] == INF) {
                out.println(total);
                continue;
            }
            int cur = v[p[i] - 1];
            while (true) {
                Pair pair = t.getMin(p[i] - 1);
                if (pair.value <= cur) {
                    total -= count[pair.pos];
                    v[pair.pos] = INF;
                    t.update(new Pair(pair.pos, INF));
                } else
                    break;
            }
            out.println(total);
        }
    }

    static class Fenwick {

        int[] t;

        Fenwick(int n) {
            t = new int[n];
        }

        void add(int idx) {
            for (; idx < t.length; idx += idx & -idx) {
                t[idx]++;
            }
        }

        int count(int idx) {
            if (idx == 0) return 0;
            int ret = 0;
            for (; idx > 0; idx -= idx & -idx)
                ret += t[idx];
            return ret;
        }
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        //gen();
//        in = new BufferedReader(new FileReader("in"));
//        out = new PrintWriter("out");
        solve();
        in.close();
        out.close();
    }

    void gen() throws IOException {
        PrintWriter out = new PrintWriter("in");
        int n = 500000;
        out.println(n + " " + n);
        Random rnd = new Random();
        for (int i = 0; i < n; ++i)
            out.println(1000000 - i);
        for (int i = 0; i < n; ++i) {
            out.println(rnd.nextInt(n) + 1);
        }
        out.close();
    }

    String ns() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(in.readLine());
        return st.nextToken();
    }

    int ni() throws IOException {
        return Integer.valueOf(ns());
    }

    long nl() throws IOException {
        return Long.valueOf(ns());
    }

    double nd() throws IOException {
        return Double.valueOf(ns());
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }

        long time() {
            return System.currentTimeMillis() - time;
        }

        void print() {
            print("Time spent = ");
        }

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object... objs) {
        if (!DEBUG) {
            return;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[])
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }


}

