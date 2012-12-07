import static java.lang.Math.abs;

public class PastingPaintingDivTwo {

    boolean[][] copy(boolean[][] map) {
        boolean[][] ret = new boolean[map.length][map[0].length];
        for (int i = 0; i < ret.length; ++i)
            for (int j = 0; j < ret[i].length; ++j) ret[i][j] = map[i][j];
        return ret;
    }

    boolean[][] put(boolean[][] map, String[] arr, int p) {
        boolean[][] ret = copy(map);
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[i].length(); ++j) {
                boolean a = arr[i].charAt(j) == 'B';
                ret[i + p][j + p] |= a;
            }
        }
        return ret;
    }

    int count(boolean[][] map) {
        int ret = 0;
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[i].length; ++j)
                if (map[i][j]) ++ret;
        return ret;
    }

    int middle = 0;

    int model(String[] arr, int T) {
        int n = arr.length;
        int m = arr[0].length();
        boolean[][] map = new boolean[n + T - 1][m + T - 1];
        for (int i = 0; i < T; ++i) {
            boolean[][] nmap = put(map, arr, i);
            middle = count(nmap) - count(map);
            map = nmap;
        }
        return count(map);
    }

    public long countColors(String[] clipboard, int T) {
        if(T < 100)
            return model(clipboard, T);
        else {
            return model(clipboard, 100) + (long)middle * (long)(T - 100);
        }
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0, (new PastingPaintingDivTwo()).countColors(new String[]{
                    "..B",
                    "B..",
                    "BB."
            }, 3), 10L);
            eq(1, (new PastingPaintingDivTwo()).countColors(new String[]{
                    "B...",
                    "....",
                    "....",
                    "...B"
            }, 2), 4L);
            eq(2, (new PastingPaintingDivTwo()).countColors(new String[]{"BBB"}, 10000), 30000L);
            eq(3, (new PastingPaintingDivTwo()).countColors(new String[]{"."}, 1000000000), 0L);
            eq(4, (new PastingPaintingDivTwo()).countColors(new String[]{
                    "BB.",
                    ".B."
            }, 100), 201L);
            eq(5, (new PastingPaintingDivTwo()).countColors(new String[]{
                    "..........B..........",
                    ".........B.B.........",
                    "........B...B........",
                    ".......B.....B.......",
                    "......B..B.B..B......",
                    ".....B...B.B...B.....",
                    "....B...........B....",
                    "...B...B.....B...B...",
                    "..B.....BBBBBB....B..",
                    ".B..........BB.....B.",
                    "BBBBBBBBBBBBBBBBBBBBB"
            }, 1000000000), 21000000071L);
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
