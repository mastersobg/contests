import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    BigInteger[] count;

    char[] s;
    long n;

    void solve() throws IOException {
        s = ns().toCharArray();

        n = nl();
        if (s.length == 1) {
            for (int i = 0; i < n; ++i)
                out.print(s[0]);
            out.println();
            return;
        }
        calcCount();
        int len = getLen(n);
        BigInteger number = BigInteger.valueOf(n).subtract(count[len - 1]).subtract(BigInteger.ONE);

        StringBuilder ret = new StringBuilder();
        BigInteger LEN = BigInteger.valueOf(s.length);
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger a = number.mod(LEN);
            number = number.divide(LEN);
            ret.append(s[a.intValue()]);
        }
        while (ret.length() < len)
            ret.append(s[0]);
        ret.reverse();
        out.println(ret);
    }

    int getLen(long N) {
        BigInteger n = BigInteger.valueOf(N);
        BigInteger cur = BigInteger.ZERO;
        for (int i = 1; i < count.length; ++i) {
            cur = cur.add(count[i]);
            if (cur.compareTo(n) >= 0) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    void calcCount() {
        count = new BigInteger[55];
        count[0] = BigInteger.ZERO;
        BigInteger prev = BigInteger.ONE;
        for (int i = 1; i < count.length; ++i) {
            prev = prev.multiply(BigInteger.valueOf(s.length));
            count[i] = count[i - 1].add(prev);
        }
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");

        int test = ni();
        for (int t = 1; t <= test; ++t) {
            out.print("Case #" + t + ": ");
            solve();
        }
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
        if ("true".equals(System.getProperty("LOCAL_DEBUG"))) {
            DEBUG = true;
        }
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

