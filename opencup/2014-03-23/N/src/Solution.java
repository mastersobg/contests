import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename = "";

    class Node {

        int idx = -1;
        Node []next = new Node[26];

        Node next(char c) {
            return next[c - 'a'];
        }

        void next(char c, Node next) {
            this.next[c - 'a'] = next;
        }
    }

    void add(Node root, char []s, int idx) {
        for (int i = 0; i < s.length; ++i) {
            char c = s[i];
            if (root.next(c) == null) {
                Node next = new Node();
                root.next(c, next);
            }
            root = root.next(c);
        }
        if (root.idx == -1) {
            root.idx = idx;
        }
    }

    int go(Node root, char []s, int mask) {
        int n = s.length;
        for (int i = 0; i < n; ++i) {
            if ((mask & (1 << i)) != 0) {
                char c = s[i];
                root = root.next(c);
                if (root == null) {
                    return -1;
                }
            }
        }
        return root.idx;
    }

    void solve() throws IOException {
        Node root = new Node();
        int n = ni();
        String []dict = new String[n];
        for (int i = 0; i < n; ++i) {
            dict[i] = ns();
            char []s = dict[i].toCharArray();
            Arrays.sort(s);
            add(root, s, i);
        }

        n = ni();
        for (int it = 0; it < n; ++it) {
            int ret = -1;
            int len = -1;
            char []s = ns().toCharArray();
            Arrays.sort(s);
            for (int i = 0; i < (1 << s.length); ++i) {
                int ans = go(root, s, i);
                if (ans != -1) {
                    if (Integer.bitCount(i) > len || (Integer.bitCount(i) == len && dict[ans].compareTo(dict[ret]) < 0)) {
                        ret = ans;
                        len = Integer.bitCount(i);
                    }
                }
            }
            if (ret == -1) {
                out.println("IMPOSSIBLE");
            } else {
                out.println(dict[ret]);
            }
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

