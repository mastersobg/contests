import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    void solve() throws IOException {
        int n = ni();
        char [][]v = new char[n][];
        for (int i = 0; i < n; ++i) {
            v[i] = ns().toCharArray();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                if (v[i][j] == '#') {
                    if (check(v, i, j)) {
                        out.println("YES");
                    } else {
                        out.println("NO");
                    }
                    return ;
                }
        }
        out.println("YES");
    }

    boolean check(char [][]v, int x, int y) {
        int len = 0;
        int n = v.length;
        for (int i = y; i < n && v[x][i] == '#'; ++i) {
            ++len;
        }
        if (x + len > n) return false;
        for (int i = x; i < x + len; ++i) {
            for (int j = y; j < y + len; ++j) {
                if (v[i][j] == '.') return false;
                v[i][j] = '.';
            }
        }
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (v[i][j] == '#') return false;
        return true;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
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

