import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    class Segment {
        int x1, x2, y1, y2;

        Segment(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        int size(Segment s) {
            int xmax = max(x1, s.x1);
            int xmin = min(x2, s.x2);
            int ymax = max(y1, s.y1);
            int ymin = min(y2, s.y2);
            if (xmax == xmin && ymax == ymin) {
                int ret = abs(s.x1 - xmax);
                ret = min(ret, abs(s.x2 - xmax));
                ret = min(ret, abs(y1 - ymax));
                ret = min(ret, abs(y2 - ymax));
                return ret;
            }
            return 0;
        }
    }

    int n, m;

    Segment []hor, ver;

    void solve() throws IOException {
        n = ni();
        m = ni();
        ver = new Segment[n];
        hor = new Segment[m];
        for (int i = 0; i < n; ++i) {
            int x = ni(), y = ni(), l = ni();
            ver[i] = new Segment(x, x, y, y + l);
        }
        for (int i = 0; i < m; ++i) {
            int x = ni(), y = ni(), l = ni();
            hor[i] = new Segment(x, x+ l, y, y);
        }

        int ret = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int sz = ver[i].size(hor[j]);
                ret = max(ret, sz);
            }
        }  
        out.println(ret);
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

