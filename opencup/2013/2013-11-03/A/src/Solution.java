import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    static class Pair implements Comparable<Pair> {
        String dist;
        int v;

        Pair(String dist, int v) {
            this.dist = dist;
            this.v = v;
        }

        @Override
        public int compareTo(Pair o) {
            return dist.length() - o.dist.length();
        }
    }

    int n;
    int[][] v;

    String[] dist;
    boolean[] used;

    void dijkstra(int value) {
        dist = new String[n + 1];
        used = new boolean[n + 1];

        dist[value] = "0";
        Collection<Integer> got = new HashSet<Integer>();


        PriorityQueue<Pair> q = new PriorityQueue<Pair>();
        for (q.add(new Pair(dist[value], value)); !q.isEmpty(); ) {
            Pair p = q.poll();
            int v = p.v;
            String d = p.dist;

            got.add(v);

            if (used[v])
                continue;
            used[v] = true;

            for (int a : got) {
                if (a < n && v < n) {
                    int len = 2 + d.length() + 1 + dist[a].length() + 1;
                    //String left = "a[" + d + "," + dist[a] + "]";
                    int next = this.v[v][a];
                    if (dist[next] == null || dist[next].length() > len) {
                        dist[next] = "a[" + d + "," + dist[a] + "]";
                        q.add(new Pair(dist[next], next));
                    }

//                    String right = "a[" + dist[a] + "," + d + "]";
                    next = this.v[a][v];
                    if (dist[next] == null || dist[next].length() > len) {
                        dist[next] = "a[" + dist[a] + "," + d + "]";
                        q.add(new Pair(dist[next], next));
                    }
                }
            }


        }

    }


    void solve() throws IOException {
        Timer t = new Timer();
        t.start();
        n = ni();
        v = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) {
                v[i][j] = ni();
            }

        dijkstra(0);
        if (dist[n] == null)
            out.println("IMPOSSIBLE");
        else out.println(dist[n]);
        t.print();
    }

    public void run() throws IOException {
//        gen();
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
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

    void gen() throws IOException {
        PrintWriter out = new PrintWriter("test.txt");
        out.println(22);
        Random rnd = new Random();
        for (int i = 0; i < 22; ++i) {
            for (int j = 0; j < 22; ++j)
                out.print(rnd.nextInt(23) + " ");
            out.println();
        }
        out.close();
    }

}

