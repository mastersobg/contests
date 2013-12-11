import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    void solve() throws IOException {
        int n = ni();
        int []v = new int[n];
        for (int i = 0; i < n; ++i)
            v[i] = ni();
        int [][]dp = new int[2][n + 1];
        for (int i = 0; i < 2; ++i)
            Arrays.fill(dp[i], -1 << 30);
        dp[0][0] = 0;
        int pred = 0;
        int cur = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                int a = dp[pred][j];
                if (j > 0) {
                    a = max(a, dp[pred][j - 1] - v[i - 1]);
                }
                for (int k = j + 1; k <= n; ++k)
                    if (dp[pred][k] != -1 << 30) {
//                        dbg((v[i - 1] * (k - j)));
                        a = max(a, dp[pred][k] + (v[i - 1] * (k - j)));
                    }
                dp[cur][j] = a;

            }
            int t = pred;
            pred = cur;
            cur = t;
            Arrays.fill(dp[cur], -1 << 30);
            
        }
        int ret = -1 << 30;
	    for (int i = 0; i <= n; ++i)
            ret = max(ret, dp[pred][i]);
//        dbg("ok");
//        dbg(dp);
        if (ret == -1 << 30) ret = 0;
        out.println(ret);    
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        for (int t = ni(); t > 0; --t) {
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

    static boolean DEBUG = true;

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

