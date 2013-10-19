import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, p, q;

    int []v;

    void solve() throws IOException {
        n = ni();
        p = ni();
        q = ni();
        v = new int[n];
        for (int i = 0; i < n; ++i) {
            v[i] = ni();
        }
        int l = 0, r = 1000000010;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (can(m))
                r = m;
            else l = m;
        }
        if (can(l))
            out.println(l);
        else out.println(r);
    }

    boolean can(int hits) {
        long ret = 0;
        for (int i = 0; i < n; ++i) {
            long a = (long)v[i] - (long)hits * (long) q;
            if (a > 0) {
                long b = p - q;
                if (b == 0) return false;
                long need = a / (long) b;
                if (a % b != 0) ++need;
                ret += need;
            }
        }
        return ret <= hits;
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
        if (args.length > 0) {
            if ("LOCAL_DEBUG".equals(args[0])) {
                DEBUG = true;
            }
        }
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

