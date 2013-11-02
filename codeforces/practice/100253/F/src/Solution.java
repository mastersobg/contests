import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, m;
    int[] t;
    double[] p;

    static class Pair implements Comparable<Pair> {
        int t, test;

        Pair(int t, int test) {
            this.t = t;
            this.test = test;
        }

        @Override
        public int compareTo(Pair o) {
            if (t == o.t) {
                return test - o.test;
            }
            return t - o.t;
        }
    }

    void solve() throws IOException {
        n = ni();
        m = ni();
        t = new int[n];
        p = new double[n];
        for (int i = 0; i < n; ++i) {
            t[i] = ni();
            p[i] = nd();
        }
        TreeSet<Pair> set = new TreeSet<Pair>();
        for (int i = 0; i < Math.min(m, n); ++i) {
            set.add(new Pair(t[i], i));
        }

        int[] end = new int[n];
        Pair[] p = new Pair[n];
        int idx = 0;
        for (int i = Math.min(m, n); i < n; ++i) {
            Pair pair = set.pollFirst();
            p[idx++] = pair;
            end[pair.test] = pair.t;
            set.add(new Pair(pair.t + t[i], i));
        }
        while (!set.isEmpty()) {
            Pair pair = set.pollFirst();
            end[pair.test] = pair.t;
            p[idx++] = pair;
        }

        int[] full = new int[n];
        double[] pfull = new double[n];
        full[0] = end[0];
        pfull[0] = this.p[0];
        for (int i = 1; i < n; ++i) {
            full[i] = Math.max(full[i - 1], end[i]);
            pfull[i] = pfull[i - 1] * this.p[i];
        }

        double ret = (1.0 - this.p[0]) * full[0];
        for (int i = 1; i < n; ++i) {
            ret += (1.0 - this.p[i]) * pfull[i - 1] * full[i];
        }
        ret += pfull[n - 1] * full[n - 1];
        out.println(ret);
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        in.close();
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

