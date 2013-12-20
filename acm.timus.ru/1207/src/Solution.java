import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    class Point {
        double x, y;
        int id;
        Point(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id + 1;
        }

        public String toString() {
            return "x=" + x + " y=" + y;
        }
    }

    class Cmp implements Comparator<Point> {
        Point b;

        public int compare(Point p1, Point p2) {
            double area = area(b, p1, p2);
            return area > 0 ? 1 : -1;
        }
    }

    double area(Point p1, Point p2, Point p3) {
        return area(p1, p2) + area(p2, p3) + area(p3, p1);
    }

    double area(Point p1, Point p2) {
        return ((double)p1.y + (double)p2.y) * ((double)p2.x - (double)p1.x);
    }

    void solve() throws IOException {
        int n = ni();
        Point []pts = new Point[n];
        for (int i = 0; i < n; ++i)
            pts[i] = new Point(ni(), ni(), i);
        Cmp cmp = new Cmp();
        for (int i = 1; i < n; ++i) {
            if (pts[i].x < pts[0].x || 
                    (pts[i].x == pts[0].x && pts[i].y < pts[0].y)) {
                        Point t = pts[i];
                        pts[i] = pts[0];
                        pts[0] = t;
                    }
        }

        cmp.b = pts[0];
        for (int i = 1; i < n; ++i) {
            pts[i].x -= pts[0].x;
            pts[i].y -= pts[0].y;
        }
        pts[0].x = pts[0].y = 0;
        Arrays.sort(pts, 1, n, cmp);
        out.println(pts[0].id + " " + pts[n / 2].id);
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

