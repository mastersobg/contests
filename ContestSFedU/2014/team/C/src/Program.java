import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Program {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, m, k;

    class Edge {
        int v, u;
        Edge(int a, int b) {
            v = a;
            u = b;
        }
    }

    int solve() throws IOException {
        n = ni();
        m = ni();
        k = ni();
        if (m == 0 && k == 0) {
            if (n == 0 || n == 1) {
                out.println(0);
            } else {
                out.println(n);
                for (int i = 1; i <= n; ++i) {
                    int next = i + 1;
                    if (next == n + 1)
                        next = 1;
                    out.println(i + " " + next);
                }
            }
            return 0;
        }
        if (k + 2 > n) {
            return fail();
        }
        if (m > k + 1) {
            return fail();
        }
        if (m == k + 1 && n > k + 2) {
            return fail();
        }

        List<Edge> edges = new ArrayList<Edge> ();
        int v = 1;
        if (n > k + 2) {
            int []arr = new int[n - k];
            arr[0] = 1;
            arr[1] = 2;
            int idx = 2;
            for (int i = k + 3; i <= n; ++i)
                arr[idx++] = i;
            for (int i = 0; i < arr.length; ++i) {
                int j = (i + 1) % arr.length;
                edges.add(new Edge(arr[i], arr[j]));
            }
            v = 2;
        }

        int u = k + 2;
        for (int i = 0; i < m; ++i) {
            edges.add(new Edge(u, u - 1));
            --u;
        }

        while (u != v) {
            edges.add(new Edge(u, u - 1));
            edges.add(new Edge(u - 1, u));
            --u;
        }

        out.println(edges.size());
        for (Edge e : edges) {
            out.println(e.v + " " + e.u);
        }
        return 0;
    }

    int fail() throws IOException {
        out.println("FAIL");
        return 0;
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
        new Program().run();
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

