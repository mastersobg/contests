import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    void sort(int []v, int l, int r) {
        if (l <= r) {
            int p = partition(v, l, r);
            sort(v, l, p - 1);
            sort(v, p + 1, r);
        }
    }

    int partition(int []v, int l, int r) {
        int idx = (l + r) >> 1;
        int base = v[idx];
        swap(v, idx, r);
        int p1 = l, p2 = l;
        for (; p1 < r; ++p1) {
            if (v[p1] < base) {
                swap(v, p1, p2++);
            }
        }
        swap(v, p2, r);
        return p2;
    }

    void swap(int []v, int p1, int p2) {
        int t = v[p1];
        v[p1] = v[p2];
        v[p2] = t;
    }

    void solve() throws IOException {
        int n = ni(), k = ni();
        String s = ns();
        int []v = new int[n - k + 1];
        int sz = 0;
        for (int i = 0; i < n - k + 1; ++i) {
            int sum = 0;
            for (int j = 0; j < k; ++j)
                sum = sum * 10 + (s.charAt(i + j) - '0');
            v[sz++] = sum;
        }
        dbg(v);
        sort(v, 0, sz - 1);
        dbg(v);
        for (int i = 1; i < sz; ++i)
            if (v[i] == v[i - 1]) {
                out.println("YES");
                return ;
            }

        out.println("NO");
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

