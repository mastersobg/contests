import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        int[] v;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            v = in.readIntArray();
            int p1 = 0, p2 = v.length - 1;
            int len = v.length;
            while (len > 2) {
                int abs1 = Math.abs(v[p1] - v[p1 + 1]);
                int abs2 = Math.abs(v[p2] - v[p2 - 1]);
                if (abs1 > abs2) ++p1;
                else
                    --p2;
                len -= 2;
            }
            Dbg.dbg("my solution", p1, p2);
            out.println(Math.abs(v[p1] - v[p2]));
//        out.println(abs(v[0] - v[a]));
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

    static class Reader {
        private BufferedReader in;
        private StringTokenizer st;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

        public String readString() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            return st.nextToken();
        }

        public int readInt() {
            return Integer.parseInt(readString());
        }

        public int[] readIntArray(int size) {
            int[] ret = new int[size];
            for (int i = 0; i < size; i++) {
                ret[i] = readInt();
            }
            return ret;
        }

        public int[] readIntArray() {
            int n = readInt();
            return readIntArray(n);
        }

    }
}

