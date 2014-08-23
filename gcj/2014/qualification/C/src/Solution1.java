import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution1 {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename;

    int r, c;
    int [][]map;
    boolean [][]was;

    void solve() throws IOException {
        r = ni();
        c = ni();
        map = new int[r][c];
        was = new boolean[r][c];
        int m = ni();
        for (int mask = 0; mask < (1 << (r * c)); ++mask)  {
            if (Integer.bitCount(mask) == m && !getBit(mask, 0, 0)) {
                if (check(mask)) {
                    out.print('c');
                    for (int i = 0; i < r; ++i) {
                        for (int j = 0; j < c; ++j) {
                            if (i == 0 && j == 0) 
                                continue;
                            out.print(getBit(mask, i, j) ? '*' : '.');
                        }
                        out.println();
                    }
                    return ;
                }
            }
        }
        out.println("Impossible");
    }

    boolean check(int mask) {
        for (int i = 0; i < r; ++i)
            for (int j = 0; j < c; ++j) {
                map[i][j] = count(mask, i, j);
            }
        for (int i = 0; i < r; ++i)
            Arrays.fill(was[i], false);
        go(mask, 0, 0);
        for (int i = 0; i < r; ++i)
            for (int j = 0; j < c; ++j)
                if (!getBit(mask, i, j)) {
                    if (!was[i][j]) return false;
                }
        return true;
    }

    void go(int mask, int x, int y) {
        if (getBit(mask, x, y))
            return ;
        if (was[x][y])
            return ;
        was[x][y] = true;
        if (map[x][y] == 0) {
            for (int i = -1; i <= 1; ++i)
                for (int j = -1; j <= 1; ++j)
                    if (!(i == 0 && j == 0) && inside(x + i, y + j)) {
                        go(mask, x + i, y + j);
                    }
        }
    }

    int count(int mask, int x, int y) {
        int ret = 0;
        for (int i = -1; i <= 1; ++i)
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) 
                    continue;
                if (inside(x + i, y + j)) {
                    ret += getBit(mask, x + i, y + j) ? 1 : 0;
                }
            }
        return ret;
    }

    boolean inside(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    boolean getBit(int mask, int x, int y) {
        int bit = x * c + y;
        return (mask & (1 << bit)) != 0;
    }

    Solution1(String fn) {
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
        new Solution1(args[0]).run();
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

