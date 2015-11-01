import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    long[][] v = {
            {1999999963L, 2111111112L},
            {2499999985L, 2611111116L},
            {2999999917L, 3111111111L},
            {3999999997L, 4111111112L},
            {4499999965L, 4611111132L},
            {4999999969L, 5111111115L},
            {5199995116L, 5311111155L},
            {5399999956L, 5511111115L},
            {5599999936L, 5711111175L},
            {5799999556L, 5911111395L},
            {5999999536L, 6111111126L},
            {6499999945L, 6611111112L},
            {6999999985L, 7111111112L},
            {7999999372L, 8111111112L},
            {8499999889L, 8611111128L},
            {8999999929L, 9111111111L}
    };

    void precalc(long n) {
        for (long[] a : v) {
            if (n >= a[0] && n <= a[1]) {
                out.println(a[1]);
                out.flush();
                System.exit(0);
            }
        }
    }

    void s() throws IOException {
        long n = nl();
        precalc(n);
        for (; ; ++n) {
            n = get(n);
            if (check(n)) {
                out.println(n);
                return;
            }
        }
    }

    long get(long a) {
        List<Long> list = new ArrayList<Long>();
        while (a > 0) {
            long q = a % 10L;
            if (q == 0) ++q;
            list.add(q);
            a /= 10L;
        }
        long ret = 0;
        for (int i = list.size() - 1; i >= 0; --i)
            ret = ret * 10 + list.get(i);
        return ret;
    }

    boolean check(long n) {
        long old = n;
        while (n > 0) {
            int a = (int) (n % 10L);
            if (a == 0) return false;
            if (old % a != 0) return false;
            n /= 10;
        }
        return true;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        s();
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

