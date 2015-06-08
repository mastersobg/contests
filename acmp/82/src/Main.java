import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    void quickSort(int []v, int l, int r) {
        // dbg(v);
        if (l < r) {
            int idx = partition(v, l, r);
            quickSort(v, l, idx - 1);
            quickSort(v, idx + 1, r);
        }
    }

    int partition(int []v, int l, int r) {
        int mid = (l + r) >> 1;
        int base = v[mid];
        swap(v, mid, r);
        int p1, p2;
        for (p1 = l, p2 = l; p1 < r; ++p1) {
            if (v[p1] < base) {
                swap(v, p1, p2++);
            }
        }
        swap(v, r, p2);
        return p2;
    }

    void swap(int []v, int i, int j) {
        int t = v[i];
        v[i] = v[j];
        v[j] = t;
    }
    void solve() throws IOException {
        int n = ni();
        int m = ni();
        int []v1 = new int[n];
        int []v2 = new int[m];
        for (int i = 0; i < n; ++i)
            v1[i] = ni();
        for (int i = 0; i < m; ++i)
            v2[i] = ni();
            
        quickSort(v1, 0, n - 1);
        quickSort(v2, 0, m - 1);

        int p1 = 0, p2 = 0;
        // dbg(v1);
        // dbg(p2);
        while (p1 < n && p2 < m) {
            if (v1[p1] < v2[p2])
                ++p1;
            else if (v1[p1] > v2[p2])
                ++p2;
            else {
                out.print(v1[p1] + " ");
                int cur = v1[p1];
                while (p1 < n && v1[p1] == cur) {
                    ++p1;
                }
            }
        }
    }

    public void run() throws IOException {
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
        new Main().run();
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

