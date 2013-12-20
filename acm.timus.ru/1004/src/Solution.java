import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    boolean solve() throws IOException {
        int n = ni();
        if (n == -1)
            return false;
        
        int m = ni();
        int INF = 1 << 29;
        int [][]g = new int [n][n];
        int [][]d = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
            Arrays.fill(d[i], INF);
        }
        for (int i = 0; i < m; ++i) {
            int v = ni() - 1;
            int u = ni() - 1;
            int cost = ni();
            if (cost < g[v][u]) {
	            g[v][u] = g[u][v] = cost;
	            d[v][u] = d[u][v] = g[v][u];
            }
        }

        int [][]p = new int[n][n];
        for (int i = 0; i < n; ++i)
            Arrays.fill(p[i], -1);

        for (int k = 0; k < n; ++k)
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    if (g[i][k] < INF && g[k][j] < INF) {
                        if (g[i][j] > g[i][k] + g[k][j]) {
                            g[i][j] = g[i][k] + g[k][j];
                            p[i][j] = k;
                        }
                    }
        int best = INF;
        int v = -1, u = -1;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (i != j && p[i][j] != -1 && d[j][i] < INF) {
                    if (g[i][j] + d[j][i] < best) {
                        best = g[i][j] + d[j][i];
                        v = i;
                        u = j;
                    }
                }
        dbg("vertex=" + v + " " + u + " " + best);
        if (best < INF) {
            List<Integer> list = restorePath(v, u, p);
            for (int a : list)
                out.print(a + " ");
            out.println();
        } else {
            out.println("No solution.");
        }
        return true;
    }

    List<Integer> restorePath(int v, int u, int [][]p) {
        if (p[v][u] == -1) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(v);
            list.add(u);
            return list;
        } else {
            List<Integer> l = restorePath(v, p[v][u], p);
            l.add(p[v][u]);
            List<Integer> other = restorePath(p[v][u], u, p);
            for (int i = 1; i < other.size(); ++i)
            	l.add(other.get(i));
            return l;
        }
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        do {
        } while(solve());
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

