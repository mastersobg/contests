import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename = "monkey";

    int [][]words;
    String []w;

    void solve() throws IOException {
        int n = ni();
        words = new int[n][26];
        w = new String[n];
        for (int i = 0; i < n; ++i) {
            w[i] = ns();
        }
        Arrays.sort(w);
        for (int i = 0; i < n; ++i) {
            process(w[i], words[i]);
        }
        n = ni();
        int []text = new int[26];
        for (int it = 0; it < n; ++it) {
            process(ns(), text);
            boolean printed = false;
            for (int i = 0; i < w.length; ++i)
                if (match(text, words[i])) {
                    if (!printed) {
                        printed = true;
                        out.println("Did you mean:");
                    }
                    out.print(w[i]);
                    out.println("?");
                }
            if (!printed) {
                out.println("No matches found.");
            }
            Arrays.fill(text, 0);
        }
    }

    boolean match(int []a, int []b) {
        for (int i = 0; i < 26; ++i)
            if (a[i] != b[i]) 
                return false;
        return true;
    }

    void process(String s, int []cnt) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                cnt[s.charAt(i - 1) - 'A']++;
            }
        }
        cnt[s.charAt(s.length() - 1) - 'A']++;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader(filename + ".in"));
        out = new PrintWriter(filename + ".out");
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

