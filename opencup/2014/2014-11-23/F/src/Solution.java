import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename = "";

    static class Node {
        int x, y, r;
        List<Node> others = new ArrayList<Node>();
        boolean was;
        boolean cw;

        Node(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        private boolean touch(Node n) {
            long d = sqr(x - n.x) + sqr(y - n.y);
            return d == sqr(r + n.r);
        }
        long sqr(long a) {
            return a * a;
        }
    }

    Node []nodes;

    void solve() throws IOException {
        for (int t = ni(); t > 0; --t) {
            int m = ni();
            nodes = new Node[m];
            for (int i = 0; i < m; ++i) {
                nodes[i] = new Node(ni(), ni(), ni());
                for (int j = 0; j < i; ++j) {
                    if (nodes[i].touch(nodes[j])) {
                        nodes[i].others.add(nodes[j]);
                        nodes[j].others.add(nodes[i]);
                    }
                }
            }
            nodes[0].cw = true;
            go(nodes[0]);
            for (Node n : nodes) {
                if (n.was) {
                    int gcd = gcd(nodes[0].r, n.r);
                    int a = nodes[0].r / gcd;
                    int b = n.r / gcd;
                    out.print(a);
                    if (b > 1)
                        out.print("/" + b);
                    out.print(" ");
                    out.println(n.cw ? "clockwise" : "counterclockwise");
                }else
                    out.println("not moving");
            }
        }
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    void go(Node node) {
        if (node.was)
            return ;
        node.was = true;
        for (Node o : node.others) {
            o.cw = !node.cw;
            go(o);
        }
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

