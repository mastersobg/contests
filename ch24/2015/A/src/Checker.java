import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Checker {

    PrintWriter out;
    Reader input, answer;

    void solve() throws IOException {
        int tests = input.ni();
        for (int it = 0; it < tests; ++it) {
            check(it);
        }
        System.out.println("ok");
    }

    private void check(int testNo) throws IOException {
        int n = input.ni();
        int k = input.ni();
        int t = input.ni();
        int []fish = new int[t];
        for (int i = 0; i < t; ++i)
            fish[i] = input.ni();
        int [][][]ponds = read(n, k);
        int [][][]plates = read(n, k);

        boolean []used = new boolean[k];
        int[] ans = new int[t];
        for (int test = 0; test < k; ++test) {
            int idx = answer.ni() - 1;
            if (used[idx])
                throw new RuntimeException("used " + testNo);
            used[idx] = true;
            boolean mirror = answer.ni() == 1;
            int rotate = answer.ni();
            int[][] matrix = plates[idx];
            if (mirror) {
                matrix = mirror(matrix);
            }
            matrix = rotate(matrix, rotate);

            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    if (matrix[i][j] == 1 && ponds[test][i][j] != 0) {
                        ans[ponds[test][i][j] - 1]++;
                    }
        }
        for (int i = 0; i < t; ++i) {
            if (fish[i] != ans[i])
                throw new RuntimeException("not match");
        }
    }

    int [][]mirror(int [][]v) {
        int [][]ret = new int[v.length][v.length];
        for (int i = 0; i < v.length; ++i)
            for (int j = 0; j < v.length; ++j) {
                ret[i][j] = v[i][v.length - j - 1];
            }
        return ret;
    }

    int [][]rotate(int [][]v, int angle) {
        for (int it = 0; it < angle; ++it) {
            int [][]ret = new int[v.length][v.length];
            for (int i = 0; i < v.length; ++i)
                for (int j = 0; j < v.length; ++j) {
                    ret[i][j] = v[v.length - j - 1][i];
                }
            v = ret;
        }
        return v;
    }

    int [][][]read(int n, int k) throws IOException {
        int [][][]v = new int[k][n][n];
        for (int it = 0; it < k; ++it) {
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    v[it][i][j] = input.ni();
        }
        return v;
    }

    public Checker() throws IOException {
        Locale.setDefault(Locale.US);
        input = new Reader(new BufferedReader(new FileReader("input.txt")));
        answer = new Reader(new BufferedReader(new FileReader("output.txt")));
        solve();
    }

    static class Reader {
        BufferedReader in;
        StringTokenizer st;

        Reader(BufferedReader br) {
            in = br;
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
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("LOCAL_DEBUG")) {
            DEBUG = true;
        }
        new Checker();
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
