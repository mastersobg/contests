import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.InputStreamReader;

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
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        public void solve(int testNumber, Reader in, PrintWriter out) {
            Dbg.dbg("lol");
        }

    }

    static class Reader {
        private BufferedReader in;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
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
            System.err.flush();
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
}

