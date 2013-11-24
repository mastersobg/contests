import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, m;
    final int INF = 1000000000 + 10;
    
    int [][]input;
    long []v;

    void solve() throws IOException {
        n = ni(); 
        m = ni();
        v = new long[n];
        Arrays.fill(v, INF);
        input = new int[m][4];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < 4; ++j)
                input[i][j] = ni();
        }

        for (int it = m - 1; it >= 0; --it) {
            int l = input[it][1] - 1;
            int r = input[it][2] - 1;
            int d = input[it][3];
            if (input[it][0] == 1) {
                sub(l, r, d);
            } else {
                updateMax(l, r, d);
            }
            dbg(v);
        }

        for (int i = 0; i < n; ++i) 
            if (v[i] == INF) 
                v[i] = 0;
        dbg("check = ", v);
        long []ret = v.clone();
        if (check(v)) {
            out.println("YES");
            for (int i = 0; i < n; ++i)
                out.print(ret[i] + " ");
        } else {
            out.println("NO");
        }
    }

    boolean check(long []v) {
        for (int i = 0; i < n; ++i)
            if (abs(v[i]) > 1000000000) return false;

        for (int it = 0; it < m; ++it) {
            int l = input[it][1] - 1;
            int r = input[it][2] - 1;
            int d = input[it][3];

            if (input[it][0] == 1) {
                for (;l <= r; ++l)
                    v[l] += d;
            } else {
                long max = -1L << 60L;
                for (;l <= r; ++l)
                    max = max(max, v[l]);
                if (max != d) return false;
            }
            dbg(v);
        }
        return true;
    }

    void sub(int l, int r, int d) {
        for (; l <= r; ++l)
            if (v[l] != INF) {
                if (v[l] < -1000000000); 
                else
                    v[l] -= d;
            }
    }

    void updateMax(int l, int r, int d) {
        for (; l <= r; ++l) 
            v[l] = Math.min(v[l], d);
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

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
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

