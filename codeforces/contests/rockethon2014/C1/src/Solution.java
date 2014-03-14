import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, k;
    int []p, e;

    void solve() throws IOException {
        n = ni();
        k = ni();
        p = new int[n];
        e = new int[n];
        arr = new boolean[n];
        for (int i = 0; i < n; ++i) {
            p[i] = ni();
            e[i] = ni();
        }

        int ret = -1;
        for (int mask = 0; mask < (1 << n); ++mask) {
            int a = can(mask);
            if (a != -1) {
                if (ret == -1 || ret > a) {
                    ret = a;
                }
            }
        }
        out.println(ret);
    }
    boolean []arr;

    int can(int mask) {
        Arrays.fill(arr, false);
        int total = 0;
        int points = 0;
        for (int i = 0; i < n; ++i)
            if ((mask & (1 << i)) != 0) {
                total += e[i];
                arr[i] = true;
                ++points;
            }
        int place = 1;
        for (int i = 0; i < n; ++i) {
            int pts = p[i] + (!arr[i] ? 1 : 0);
            if (pts > points || (pts == points && !arr[i])) {
                ++place;
            }
        }
        return place <= k ? total : -1;
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

