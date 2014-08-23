import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename;

    double c, f, x;

    void solve() throws IOException {
        c = nd();
        f = nd();
        x = nd();
        int l = 0, r = 100000;
        while (l + 2 < r) {
            int m1 = l + (r - l) / 3;
            int m2 = r - (r - l) / 3;
            double r1 = calc(m1);
            double r2 = calc(m2);
            if (r1 < r2)
                r = m2;
            else l = m1;
        }
        double ret = min(min(calc(l), calc(l + 1)), calc(l + 2));
        if (l + 5 >= 100000) {
            dbg("Danger!", c, f, x);
        }
        out.println(ret);
    }

    double calc(int factories) {
        double ret = 0.0;
        double rate = 2.0;
        for (int i = 0; i < factories; ++i) {
            double time = c / rate;
            ret += time;
            rate += f;
        }
        return ret + x / rate;
    }

    Solution(String fn) {
        filename = fn;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader(filename));
        out = new PrintWriter(filename + ".out");
        int t = ni();
        for (int i = 1; i <= t; ++i) {
            out.print("Case #" + i + ": ");
            solve();
        }
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
        if ("true".equals(System.getProperty("LOCAL_DEBUG"))) {
            DEBUG = true;
        }
        new Solution(args[0]).run();
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

