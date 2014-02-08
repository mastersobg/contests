import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n;
    int m;
    int [][]edges;

    void solve() throws IOException {
        n = ni();
        m = ni();
        edges = new int[m][2];
        for (int i = 0; i < m; ++i) {
            edges[i][0] = ni();
            edges[i][1] = ni();
        }

        double all = gauss(buildMatrix(0));
        double edge = gauss(buildMatrix(1));
        dbg("all=", all, "edge", edge);
        out.println(1.0 - exp(edge - all));
    }

    double gauss(double[][] a) {
        double det = 0.0;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int k = i;
            for (int j = i + 1; j < n; ++j) {
                if (abs(a[j][i]) > abs(a[k][i])) {
                    k = j;
                }
            }
            if (abs(a[k][i]) < 1e-9) {
                det = 0;
                break;
            }
            
            double []t = a[i];
            a[i] = a[k];
            a[k] = t;

            det += log(a[i][i]);

            for (int j = i + 1; j < n; ++j)
                a[i][j] /= a[i][i];

            for (int j = 0; j < n; ++j)
                if (i != j && abs(a[j][i]) > 1e-9) {
                    for (int q = i + 1; q < n; ++q)
                        a[j][q] -= a[i][q] * a[j][i];
                }
        }
        return det;
    }

    double [][] buildMatrix(int idx) {
        double [][]g = new double[n][n];
        for (int i = idx; i < m; ++i) {
            int v = edges[i][0];
            int u = edges[i][1];
            g[v][u] = g[u][v] -= 1.0;
        }
        for (int i = 0; i < n; ++i) {
            int a = 0;
            for (int j = 0; j < n; ++j)
                if (g[i][j] < 0)
                    a += abs(g[i][j]);
            g[i][i] = a;
        }

        double [][]ret = new double [n - 1][n - 1];
        for (int i = 0; i < n - 1; ++i)
            for (int j = 0; j < n - 1; ++j)
                ret[i][j] = g[i][j];
        return ret;
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

