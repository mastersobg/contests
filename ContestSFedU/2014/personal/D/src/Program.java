import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Program {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    final int mod = 1000000007;
    int []x;
    int [][]lower, upper;
    int [][]masks;

    void solve() throws IOException {
        Timer t = new Timer();
        t.start();
        int n = ni();
        long X = nl();
        x = toBits(X);
        lower = new int[n][62];
        upper = new int[n][62];
        long []l = new long[n], h = new long[n];
        for (int i = 0; i < n; ++i) {
            long a = nl();
            long b = nl();
            lower[i] = toBits(a);
            upper[i] = toBits(b);
            l[i] = a;
            h[i] = b;
        }

        genMasks(n);

        int ret = f(n);
        
        // int brute = brute(l, h, X);
        // if (brute != ret) {
        //     throw new IllegalStateException(brute + " " + ret);
        // }

        out.println(ret);
        t.print();
    }

    void genMasks(int n) {
        masks = new int[2][(1 << n) >> 1];
        int i1 = 0, i2 = 0;
        for (int i = 0; i < (1 << n); ++i) {
            int bits = Integer.bitCount(i);
            if ((bits & 1) == 0)
                masks[0][i1++] = i;
            else 
                masks[1][i2++] = i;
        }
    }

    int []toBits(long n) {
        ArrayList<Integer> list = new ArrayList<Integer> ();
        if (n == 0)
            list.add(0);
        else {
            while (n > 0) {
                list.add((int) (n % 2));
                n /= 2;
            }
        }
        int []ret = new int[62];
        int idx = 61;
        for (int a : list) {
            ret[idx--] = a;
        }
        return ret;
    }

    int [][][][]move;

    void genMove(int n) {
        n <<= 1;
        move = new int[2][2][2][n];
        for (int i = 0; i < n; ++i) {
            
        }
    }
    int f(int n) {
        int [][]dp = new int[63][1 << (n * 2)];
        dp[0][0] = 1;

        for (int i = 0; i < 62; ++i) {
            for (int j = 0; j < dp[i].length; ++j) {
                if (dp[i][j] == 0)
                    continue;
                for (int ptr = 0; ptr < masks[x[i]].length; ++ptr) {
                    int mask = masks[x[i]][ptr];
                    boolean can = true;
                    int nextMask = j;
                    for (int bit = 0; bit < 2 * n; bit += 2) {
                    	int b = bit >> 1;
                        int stateLow = j & (1 << bit);
                        int stateHigh = j & (1 << (bit + 1));
                        if (stateHigh == 0) {
                            int maskBit = (mask & (1 << b)) != 0 ? 1 : 0;
                            if (maskBit > upper[b][i]) {
                                can = false;
                                break;
                            }
                            if (maskBit < upper[b][i])
                                nextMask |= (1 << (bit + 1));
                        }
                        if(stateLow == 0) {
                            int maskBit = (mask & (1 << b)) != 0 ? 1 : 0;
                            if (maskBit < lower[b][i]) {
                                can = false;
                                break;
                            }
                            if (maskBit > lower[b][i])
                                nextMask |= (1 << (bit));
                        }
                    }

                    if (!can) 
                        continue;
                    dp[i + 1][nextMask] = (dp[i + 1][nextMask] + dp[i][j])
                        % mod;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < dp[62].length; ++i) {
            ret = (ret + dp[62][i]) % mod;
        }
        return ret;
    }

    int brute(long []l, long []h, long x) {
        return rec(l, h, x, 0, 0);
    }
    
    int rec(long []l, long []h, long x, int idx, long cur) {
        if (idx == l.length) {
            return x == cur ? 1 : 0;
        }
        int ret = 0;
        for (long i = l[idx]; i <= h[idx]; ++i) {
            ret = (ret + rec(l, h, x, idx + 1, cur ^ i)) % mod;
        }
        return ret;
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
        new Program().run();
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

