import java.io.InputStream;
import java.io.*;
import java.util.Locale;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        C solver = new C();
        solver.solve(1, in, out);
        out.close();
    }

    static class C {
        Set<LongLongPair> set = new HashSet<>();
        final long max = 1L << 20L;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            go(0L, 0L, 1L);
            int cnt = 0;
            for (int i = -50; i <= 50; ++i)
                for (int j = -50; j <= 50; ++j)
                    if (set.contains(new LongLongPair(i, j))) {
                        ++cnt;
                        Dbg.dbg(i, j);
                    }
            Dbg.dbg("total", cnt);
        }

        void go(long x, long y, long step) {
            if (set.contains(new LongLongPair(x, y))) {
                return;
            }
            if (step * 3 < max)
                go(x, y, step * 3);
            set.add(new LongLongPair(x, y));
            if (x + step < max && step * 3 < max)
                go(x + step, y, step * 3);
            if (x - step > -max && step * 3 < max)
                go(x - step, y, step * 3);
            if (y + step < max && step * 3 < max)
                go(x, y + step, step * 3);
            if (y - step > -max && step * 3 < max)
                go(x, y - step, step * 3);
        }

    }

    static class Dbg {
        private static boolean DEBUG_ENABLED;

        static {
            try {
                DEBUG_ENABLED = System.getProperty("LOCAL_DEBUG_ENABLED") != null;
            } catch (Exception e) {
                DEBUG_ENABLED = false;
            }
        }

        public static void dbg(Object... objs) {
            if (!DEBUG_ENABLED) {
                return;
            }
            System.out.println("=====Debug output=====");
            for (Object o : objs) {
                String printLine;
                if (o.getClass().isArray()) {
                    printLine = arrayToString(o);
                } else {
                    printLine = o.toString();
                }
                System.out.print(printLine + " ");
            }
            System.out.println();
            System.out.println("=====End of debug output=====");
            System.out.flush();
        }

        public static String arrayToString(Object o) {
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

    static class LongLongPair implements Comparator<LongLongPair> {
        public long x;
        public long y;

        public LongLongPair() {
        }

        public LongLongPair(long x, long y) {
            this.x = x;
            this.y = y;
        }


        public int compare(LongLongPair o1, LongLongPair o2) {
            if (o1.x == o2.x) {
                return o1.y == o2.y ? 0 : o1.y < o2.y ? -1 : 1;
            }
            return o1.x < o2.x ? -1 : 1;
        }


        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LongLongPair that = (LongLongPair) o;

            if (x != that.x) return false;
            return y == that.y;

        }


        public int hashCode() {
            int result = (int) (x ^ (x >>> 32));
            result = 31 * result + (int) (y ^ (y >>> 32));
            return result;
        }


        public String toString() {
            return "[x = " + x + " y = " + y + "]";
        }

    }

    static class Reader {
        private BufferedReader in;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

    }
}

