import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename;

    void solve() throws IOException {
        Set<Integer> []arr1 = new Set[4];
        Set<Integer> []arr2 = new Set[4];
        int row1 = read(arr1) - 1;
        int row2 = read(arr2) - 1;
        arr1[row1].retainAll(arr2[row2]);
        if (arr1[row1].size() == 1) {
            out.println(arr1[row1].iterator().next());
        } else if (arr1[row1].size() == 0) {
            out.println("Volunteer cheated!");
        } else {
            out.println("Bad magician!");
        }
    }

    int read(Set<Integer> []arr) throws IOException {
        int row = ni();
        for (int i = 0; i < 4; ++i) {
            Set<Integer> set = new HashSet<Integer> ();
            for (int j = 0; j < 4; ++j)
                set.add(ni());
            arr[i] = set;
        }
        return row;
    }

    Solution(String fn) {
        filename = fn;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader(filename));
        out = new PrintWriter(filename + ".out");
        int t = ni();
        for (int i = 1; i <= t; ++i) {
            out.print("Case #" + i + ": ");
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
        new Solution(args[0]).run();
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

