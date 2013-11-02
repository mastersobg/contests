import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, d, a, b;

    void solve() throws IOException {
        n = ni();
        d = ni();
        a = ni();
        b = ni();
        int inc = 0, dec = 0;
        int shares = 0;
        int prev = ni();
        for (int i = 1; i < n; ++i) {
            int cur = ni();

            if (cur > prev) {
                dec = 0;
                ++inc;

                int wantBuy = a * inc;
                int canBuy = d / cur;
                int buy = min(wantBuy, canBuy);

                d -= cur * buy;
                shares += buy;

            } else if (cur < prev) {
                inc = 0;
                ++dec;

                int wantSell = b * dec;
                int canSell = shares;
                int sell = min(wantSell, canSell);

                d += cur * sell;
                shares -= sell;

            } else {
                inc = dec = 0;
            }

            prev = cur;
        }
        out.println(d + " " + shares);
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

