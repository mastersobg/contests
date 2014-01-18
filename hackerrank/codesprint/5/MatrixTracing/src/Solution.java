import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    final long mod = 1000000007L;
    
    static class Pair {
        int a, b;
        Pair (int a, int b) {
            this.a = a;
            this.b = b;
        }
        public String toString() {
            return "a=" + a + " b=" + b;
        }
    }

    int []primes(int n) {
        boolean []p = new boolean[n + 1];
        p[0] = p[1] = true;
        int cnt = 0;
        for (int i = 2; i <= n; ++i) {
            if (p[i] == false) {
                ++cnt;
                for (int j = i + i; j <= n; j += i) {
                    p[j] = true;
                }
            }
        }
        int []ret = new int[cnt];
        int ptr = 0;
        for (int i = 0; i <= n; ++i) 
            if (p[i] == false) 
                ret[ptr++] = i;
        return ret;
    }
    List []factors = new List [2000001];
    
    List<Pair> factor(int n, int []primes) {
        if (factors[n] != null) return factors[n];
        List<Pair> ret = new ArrayList<Pair>();
        for (int ptr = 0; ptr < primes.length; ++ptr) {
            int i = primes[ptr];
            if (i * i > n) break;
            if (n % i == 0) {
                int cnt = 0;
                while (n % i == 0) {
                    ++cnt;
                    n /= i;
                }
                ret.add(new Pair(i, cnt));
            }
        }
        if (n > 1) {
            ret.add(new Pair(n, 1));
        }
        factors[n] = ret;
        return ret;
    }
    void solve() throws IOException {
        int []primes = primes(2000001);
        int []arr = new int[2000001];
        for (int t = ni(); t > 0; --t) {
            int n = ni() - 1;
            int m = ni() - 1;
            n += m;
            Arrays.fill(arr, 0);
            for (int i = 2; i <= n; ++i) {
                f(factor(i, primes), true, arr);
            }
            for (int i = 2; i <= m; ++i) {
                f(factor(i, primes), false, arr);
            }
            for (int i = 2; i <= n - m; ++i) {
                f(factor(i, primes), false, arr);
            }
            long ret = 1;
            for (int i = 0; i < arr.length; ++i) {
                if (arr[i] > 0) {
                    for (int j = 0; j < arr[i]; ++j)
                        ret = (ret * i) % mod;
                }
            }
            out.println(ret);
        }
    }

    void f(List<Pair> factors, boolean add, int []arr) {
        for (Pair p : factors) {
            if (add) arr[p.a] += p.b;
            else arr[p.a] -= p.b;
        }
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

    static boolean DEBUG = true;

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

