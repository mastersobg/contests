import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, m;
    char[][] v;

    int[][][][] dp;

    class ValueHolder {
        int x, y;
    }

    // 0 - left, 1 - up
    int rec(int x, int y, int came, int left) {
        if (x < 0 || y < 0 || x >= n || y >= m || v[x][y] == '#') {
            return -1;
        }
        int ret = dp[came][left][x][y];
        if (ret < 0) {
            ret = rec(x + 1, y, 1, left) + 1;
            ret = Math.max(ret, rec(x, y + 1, 0, left) + 1);
            if (left == 0) {
                if (came == 0) {
                    for (int i = x - 1; i >= 0; --i) {
                        if (v[i][y] == '#') break;
                        ret = Math.max(ret, x - i + rec(i, y + 1, 0, 1) + 1);
                    }
                } else {
                    for (int i = y - 1; i >= 0; --i) {
                        if (v[x][i] == '#') break;
                        ret = Math.max(ret, y - i + rec(x + 1, i, 1, 1) + 1);
                    }
                }
            }
            dp[came][left][x][y] = ret;
        }
        return ret;
    }

    void getFree(int x, int y, int dx, int dy, ValueHolder ret) {
        while (true) {
            int nx = x + dx;
            int ny = y + dy;

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || v[nx][ny] == '#') {
                ret.x = x;
                ret.y = y;
                return;
            }

            x = nx;
            y = ny;
        }
    }

    void solve() throws IOException {
        n = ni();
        m = ni();
        v = new char[n][];
        for (int i = 0; i < n; ++i)
            v[i] = ns().toCharArray();

        dp = new int[2][2][n][m];
        for (int i = 0; i < dp.length; ++i)
            for (int j = 0; j < dp[i].length; ++j)
                for (int k = 0; k < dp[i][j].length; ++k)
                    Arrays.fill(dp[i][j][k], -1);
        int ret = rec(0, 0, 0, 0) + 1;
        out.println(ret);
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("out.txt");
        int test = ni();
        for (int t = 1; t <= test; ++t) {
            out.print("Case #" + t + ": ");
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

