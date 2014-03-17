import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Program {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    class Pt {
        double x, y;
        Pt(double a, double b) {
            this.x = a;
            this.y = b;
        }
    }

    int n;
    Pt p1, p2;

    void solve() throws IOException {
        double s = nd();
        n = ni();
        p1 = new Pt(nd(), nd());
        p2 = new Pt(1e+10, p1.y + 1);
        for (int i = 1;; ++i) {
            if (check(s * i)) {
                out.println(i - 1);
                return ;
            }
        }
    }

    boolean check(double s) {
        double r = sqrt(2 * s / (n * sin(2 * PI / n)));
        Pt []pts = points(r);

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            int j = (i + 1) % n;
            if (intersect(p1, p2, pts[i], pts[j])) {
                ++cnt;
            }
        }
        return cnt == 1;
    }

    Pt [] points(double r) {
        Pt []pts = new Pt[n];
        pts[0] = new Pt(r, 0);
        double a = 2 * PI / n;
        for (int i = 1; i < n; ++i) {
            double x = pts[i - 1].x * cos(a) + pts[i - 1].y * sin(a);
            double y = -pts[i - 1].x * sin(a) + pts[i - 1].y * cos(a);
            pts[i] = new Pt(x, y);  
        }
        return pts;
    }

    boolean intersect(Pt a, Pt b, Pt c, Pt d) {
        return box (a.x, b.x, c.x, d.x)
                && box (a.y, b.y, c.y, d.y)
                && area(a,b,c) * area(a,b,d) <= 0
                && area(c,d,a) * area(c,d,b) <= 0;
    }

    boolean box (double a, double b, double c, double d) {
        if (a > b)  {
            double t = a;
            a = b;
            b = t;
        }
        if (c > d) {
            double t = c;
            c = d;
            d = t;
        }
        return max(a,c) <= min(b,d);
    }

    double area(Pt a, Pt b, Pt c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
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
        new Program().run();
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

