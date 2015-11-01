import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename = "nfs";

    class El implements Comparable<El> {
        int cnt;
        int pos;
        El(int a, int b) {
            cnt = a;
            pos = b;
        }

        @Override
        public int compareTo(El e) {
            if (cnt == e.cnt)
                return e.pos - pos;
            return e.cnt - cnt;
        }
    }

    void solve() throws IOException {
        int n = ni();
        int y = ni();
        int b = ni();
        int s = ni();
        int []v = new int[n];
        int pos = 0;
        for (int i = 0; i < b; ++i)
            for (int j = 0; j < y; ++j) {
                if (j + 1== s)
                    v[pos]++;
                pos = (pos + 1) % n;
            }
        ArrayList<El> list = new ArrayList<El> ();
        for (int i = 0; i < n; ++i) 
            list.add(new El(v[i], i));           
        Collections.sort(list);
        if (list.get(0).cnt == list.get(1).cnt) {
            out.println(list.get(0).pos + 1);
        } else {
            out.println(list.get(1).pos + 1);
        }

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

