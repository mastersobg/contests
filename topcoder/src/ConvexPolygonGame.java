import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class ConvexPolygonGame {

    class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    Point[] pts;

    public String winner(int[] X, int[] Y) {
        pts = new Point[X.length];
        int minX = 1 << 30, maxX = -1 << 30, minY = 1 << 30, maxY = -1 << 30;
        for (int i = 0; i < pts.length; ++i) {
            minX = min(minX, X[i]);
            maxX = max(maxX, X[i]);
            minY = min(minY, Y[i]);
            maxY = max(maxY, Y[i]);
            pts[i] = new Point(X[i], Y[i]);
        }
        long area = 0;
        for (int i = 0; i < pts.length; ++i) {
            area += area(pts[i], pts[(i + 1) % pts.length]);
        }
        area = abs(area);
        List<Point> inside = new ArrayList<Point>();
        for (long x = minX; x <= maxX; ++x) {
            for (long y = minY; y <= maxY; ++y) {
                if (checkCorner(x, y))
                    continue;
                if (abs(calcArea(x, y)) == area) {
                    calcArea(x, y);
                    inside.add(new Point(x, y));
                    if (can(inside)) {
                        return "Masha";
                    }
                }
            }
        }
        return "Petya";
    }

    boolean can(List<Point> inside) {
        if (inside.size() < 3)
            return false;
        return abs(triangleArea(inside.get(0), inside.get(1), inside.get(inside.size() - 1))) > 0;
    }

    long triangleArea(Point p1, Point p2, Point p3) {
        return area(p1, p2) + area(p2, p3) + area(p3, p1);
    }

    boolean checkCorner(long x, long y) {
        for (Point p : pts)
            if (p.x == x && p.y == y) return true;
        return false;
    }

    long calcArea(long x, long y) {
        Point p = new Point(x, y);
        long ret = 0;
        for (int i = 0; i < pts.length; ++i) {
            Point p1 = pts[i];
            Point p2 = pts[(i + 1) % pts.length];
            ret += triangleArea(p, p1, p2);
        }
        return ret;
    }

    long area(Point p1, Point p2) {
        return (p1.y + p2.y) * (p2.x - p1.x);
    }


    // BEGIN CUT HERE
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

    public static void main(String[] args) {
        try {
            DEBUG = true;
//            eq(0, (new ConvexPolygonGame()).winner(new int[]{0, 1, 0}, new int[]{0, 0, 1}), "Petya");
//            eq(1, (new ConvexPolygonGame()).winner(new int[]{0, 4, 2}, new int[]{0, 0, 2}), "Masha");
//            eq(2, (new ConvexPolygonGame()).winner(new int[]{0, 100, 100, 0}, new int[]{0, 0, 100, 100}), "Masha");
            eq(3, (new ConvexPolygonGame()).winner(new int[]{0, 50, 100, 50}, new int[]{0, -1, 0, 1}), "Petya");
//            eq(4, (new ConvexPolygonGame()).winner(new int[]{-100000, 100000, 100000, -100000}, new int[]{-1, -1, 1, 1}), "Masha");
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, int a, int b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }

    private static void eq(int n, char a, char b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected '" + b + "', received '" + a + "'.");
    }

    private static void eq(int n, long a, long b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected \"" + b + "L, received " + a + "L.");
    }

    private static void eq(int n, boolean a, boolean b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }

    private static void eq(int n, double a, double b) {
        if (eq(a, b))
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }

    private static void eq(int n, String a, String b) {
        if (a != null && a.equals(b))
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected \"" + b + "\", received \"" + a + "\".");
    }

    private static void eq(int n, int[] a, int[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, long[] a, long[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, double[] a, double[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (!eq(a[i], b[i])) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, String[] a, String[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (!a[i].equals(b[i])) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void print(int a) {
        System.err.print(a + " ");
    }

    private static void print(long a) {
        System.err.print(a + "L ");
    }

    private static void print(String s) {
        System.err.print("\"" + s + "\" ");
    }

    private static void print(int[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(long[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(double[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(String[] rs) {
        if (rs == null) return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print("\"" + rs[i] + "\"");
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void nl() {
        System.err.println();
    }

    private static double eps = 1e-9;

    private static boolean eq(double a, double b) {
        return abs(a - b) <= eps;
    }
    // END CUT HERE
}
