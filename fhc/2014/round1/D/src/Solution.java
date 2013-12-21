import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    int[] primes() {
        int[] primes = new int[1000];
        int p = 0;
        primes[p++] = 1;
        for (int i = 2; p < primes.length; ++i) {
            boolean prime = true;
            for (int j = 2; j * j <= i; ++j)
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            if (prime) primes[p++] = i;
        }
        return primes;
    }

    void solve() throws IOException {
        int n = ni();
        int k = ni();
        int[] v = new int[n];
        int s1 = 0;
        for (int i = 0; i < n; ++i) {
            v[i] = ni();
            s1 += v[i];
        }
        int[] p = primes();
        Arrays.sort(v);
        int ptr = 0;
        int s2 = 0;
        for (int i = 0; i < n; ++i) {
            while (p[ptr] * k < v[i])
                ++ptr;
            s2 += p[ptr++] * k;
        }
        out.println(s2 - s1);
//        int n = ni();
//        int k = ni();
//        int[] v = new int[n];
//        for (int i = 0; i < n; ++i) {
//            v[i] = ni();
//        }
//
//        for (int i = 0; i < n; ++i) {
//            while (v[i] % k != 0) ++v[i];
//            while (true) {
//                boolean need = false;
//                for (int j = 0; j < i; ++j) {
//                    if (gcd(v[i], v[j]) != k) {
//                        need = true;
//                        break;
//                    }
//                }
//                if (need) {
//                    v[i] += k;
//                } else break;
//            }
//        }
//        int ret = 0;
//        for (int i = 0; i < n; ++i) {
//            out.print(v[i] + " ");
//            ret = Math.max(ret, factor(v[i] / k));
//        }
//        out.println();
//        out.println(ret);
    }

    int factor(int n) {
        int ret = 0;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                ret = Math.max(ret, i);
                while (n % i == 0) n /= i;
            }
        }
        if (n > 1) ret = Math.max(ret, n);
        return ret;
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

