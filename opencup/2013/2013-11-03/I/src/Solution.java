import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    char[] s1, s2;

    byte[][] dp;
    int n;

    Node[][] list;

    int L, R;

    byte rec(int l, int r) {
        if (l < L || r < R) return 0;
        byte ret = dp[l][r];
        if (ret < 0) {
            ret = rec(l - 1, r);
            ret = (byte) Math.max(ret, rec(l, r - 1));
            if (s1[l] == s2[r]) {
                ret = (byte) Math.max(ret, rec(l - 1, r - 1) + 1);
            }
            dp[l][r] = ret;
        }
        return ret;
    }

    static class Node {
        Node next;
        int id;
        byte l, r;

        Node(int id, byte l, byte r) {
            this.id = id;
            this.l = l;
            this.r = r;
        }
    }

    int bound(int a) {
        if (a < 0) return 0;
        if (a >= n) return n - 1;
        return a;
    }

    String process(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z')
                sb.append(c);
        }
        return sb.toString();
    }

    void solve() throws IOException {
//        Timer t = new Timer();
//        t.start();
        s1 = ns().toCharArray();
        s2 = ns().toCharArray();
//        if (s1.length != s2.length)return ;
//        if (s1.length != s2.length) return;
        n = Math.max(s1.length, s2.length);
        list = new Node[n + 1][n + 1];
//        for (int i = 0; i < n; ++i)
//            for (int j = 0; j < n; ++j)
//                list[i][j] = new ArrayList<int[]>();

        int m = ni();
        for (int i = 0; i < m; ++i) {
            int l1 = ni() - 1;
            int r1 = ni() - 1;
            int l2 = ni() - 1;
            int r2 = ni() - 1;
//            if (
//                    r2 < 0 || r2 >= n) return;

//            l1 = bound(l1);
//            l2 = bound(l2);
//            r1 = bound(r1);
//            r2 = bound(r2);

            Node node = new Node(i, (byte) r1, (byte) r2);
            if (list[l1][l2] == null) {
                list[l1][l2] = node;
            } else {
                node.next = list[l1][l2];
                list[l1][l2] = node;
            }
        }

        dp = new byte[n][n];
        byte[] ans = new byte[m];
        for (L = 0; L < n; ++L) {
            for (R = 0; R < n; ++R) {
                if (list[L][R] == null) continue;
                for (int i = 0; i < n; ++i)
                    Arrays.fill(dp[i], (byte) -1);

                for (Node node = list[L][R]; node != null; node = node.next) {
                    ans[node.id] = rec(node.l, node.r);
                }
            }
        }

        for (int a : ans)
            out.println(a);

//        t.print();
    }

    int readInt() throws IOException {
        int c = in.read();
        while (!isDigit(c)) {
            c = in.read();
        }
        int ret = c - '0';
        while (isDigit(c = in.read())) {
            ret = ret * 10 + (c - '0');
        }
        return ret;
    }

    boolean isDigit(int a) {
        return a >= '0' && a <= '9';
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
        PrintWriter out = new PrintWriter("input.txt");
        Random rnd = new Random();
        for (int i = 0; i < 100; ++i) {
            char c = (char) (rnd.nextInt(26) + 'a');
            out.print(c);
        }
        out.println();
        for (int i = 0; i < 100; ++i) {
            char c = (char) (rnd.nextInt(26) + 'a');
            out.print(c);
        }
        out.println();
        out.println(1000000);
        for (int i = 0; i < 1000000; ++i) {
            int a = rnd.nextInt(100) + 1;
            int b = rnd.nextInt(100) + 1;
            int c = rnd.nextInt(100) + 1;
            int d = rnd.nextInt(100) + 1;
            out.println(
                    Math.min(a, b) + " " +
                            Math.max(a, b) + "  " +
                            Math.min(c, d) + " " +
                            Math.max(c, d) + " "
            );
        }
//        int cnt = 0;
//        gl:
//
//        for (int c = 1; c <= 100; ++c)
//            for (int d = c; d <= 100; ++d)
//                for (int a = 1; a <= 100; ++a)
//                    for (int b = a; b <= 100; ++b) {
//                        out.println(a + " " + b + " " + c + " " + d);
//                        ++cnt;
//                        if (cnt == 1000000) break gl;
//                    }
        out.close();
    }


}

