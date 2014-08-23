import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename;

    void solve() throws IOException {
        int r = ni(), c = ni(), m = ni();
        char [][]ret = print(r, c, m);
        int mines = 0;
        for (int i = 0; i < r; ++i)
            for (int j = 0; j < c; ++j)
                if (ret[i][j] == '*')
                    ++mines;
        // dbg(Arrays.deepToString(ret));
        if (mines > m)
            throw new IllegalStateException(mines + " " + m + " " + r + " " + c);
        if (mines < m) {
            out.println("Impossible");
        }
        else {
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j)
                    out.print(ret[i][j]);
                out.println();
            }
        }
        // if (r == 1 || c == 1) {
        //     if (r * c - m >= 2) {
        //         print(r, c, m);
        //     } else {
        //         out.println("Impossible");
        //     }
        // }
        // if (r * c - m >= 3) {
        //     print(r, c, m);
        // } else {
        //     out.println("Impossible");
        // }
    }

    char [][] print(int r, int c, int m) {
        char [][]ret = new char[r][c];
        
        for (int i = r - 1; i >= 0; --i) {
            m = go(ret, i, c - 1, m);
        }
        
        for (int i = c - 2; i > 1; --i) {
            m = go(ret, 0, i, m);
        }

        ret[0][0] = 'c';
        set(ret, 0, 1, '.');
        set(ret, 1, 1, '.');
        set(ret, 1, 0, '.');

        return ret;
    }

    int go(char [][]ret, int x, int y, int mines) {
        int r = ret.length;
        int c = ret[0].length;
        while (x < r && y >= 0) {
            if (mines > 0 && !(x == 1 && y == 1)) {
                ret[x][y] = '*';
                --mines;
            } else
                ret[x][y] = '.';
            ++x;
            --y;
        }
        // dbg(mines);
        return mines;

    }

    void set(char [][]a, int x, int y, char c) {
        if (x >= a.length || y >= a[0].length)
            return ;
        a[x][y] = c;
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
            out.println("Case #" + i + ":");
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

