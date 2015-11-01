import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    long mod;
    int len;
    long n;
    List<Integer> ret = new ArrayList<Integer>();

    void solve() throws IOException {
        Timer t = new Timer();
        n = read();
        t.start();
        int ret = count(1, 9);
        ret += count(10, 99);
        ret += count(100, 999);
        ret += count(1000, 9999);
        ret += count(10000, 99999);
        ret += count(100000, 999999);
        ret += count(1000000, 9999999);
        ret += count(10000000, 99999999);
        if (ret == 0) out.println("No solution");
        else {
            for (int a : this.ret)
                out.println(a);
        }
        t.print();
    }

    int count(int f, int l) {
        int mn = 1;
        int qwe = f;
        while (qwe > 0) {
            qwe /= 10;
            mn *= 10;
        }
        mn /= 10;
        int cnt = 0;
        for (int i = f; i <= l; ++i) {
            long ret = (long) i * n;
            long a = ret / mod;
            if (a > l) break;
            if (ret % mod != 0) continue;
            if (check((int) a, i, mn)) {
                ++cnt;
                this.ret.add(i);
            }
        }
        return cnt;
    }

    boolean check(int a, int b, int mn) {
        int mod = a % 10;
        a /= 10;
        a = mod * mn + a;
        return a == b;
    }

    long read() throws IOException {
        String s = ns();
        int idx = s.indexOf('.');
        mod = 1;
        if (idx >= 0) {
            int len = s.length() - idx - 1;
            this.len = len;
            while (len > 0) {
                mod *= 10L;
                --len;
            }
            s = s.substring(0, idx) + s.substring(idx + 1, s.length());
        }
        return Long.valueOf(s);
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

