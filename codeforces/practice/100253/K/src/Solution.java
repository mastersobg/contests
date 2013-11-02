import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n;
    int[] v;

    static class Pair {
        int l, r;

        Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    void solve() throws IOException {
        n = ni();
        v = new int[n];
        for (int i = 0; i < n; ++i)
            v[i] = ni();
        int blockP = 0;
        int p = 0;
        List<Pair> list = new ArrayList<Pair>();

        for (int i = 1; i < n; ++i) {
            if (p == blockP) {
                int a = i - p + 1;
                if (v[i] < a) {
                    if (checkPrev(blockP, i)) {
                        p = i;
                    } else {
                        list.add(new Pair(blockP, i - 1));
                        blockP = i;
                        p = i;
                    }
                }
            } else {
                if (checkPrev(blockP, i)) {
                    p = i;
                } else {
                    list.add(new Pair(p, blockP));
                    blockP = i;
                    p = i;
                }
            }
        }

        if (blockP == p) {
            list.add(new Pair(blockP, n - 1));
        } else {
            list.add(new Pair(n - 1, blockP));
        }
        out.println(list.size());
        for (Pair pq : list)
            out.println((pq.l + 1) + " " + (pq.r + 1));
    }

    int cBlockP = -1, cP = -1, cMin = -1;

    boolean checkPrev(int blockP, int p) {
        if (blockP == cBlockP) {
            int diff = p - cP;
            boolean ret = cMin - diff >= 0;
            cMin -= diff;
            cP = p;
            cMin = Math.min(v[p] - 1, cMin);
            return ret;
        }

        int min = Integer.MAX_VALUE;
        for (int i = p, step = 0; i >= blockP; --i, ++step) {
            int a = v[i] - step - 1;
            min = Math.min(a, min);
        }

        cBlockP = blockP;
        cP = p;
        cMin = min;
        return min >= 0;
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


}

