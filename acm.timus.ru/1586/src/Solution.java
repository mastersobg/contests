import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    boolean []primes;

    void primes() {
        primes = new boolean[1000];
        primes[0] = primes[1] = true;
        int all = 0;
        for (int i = 2; i < 1000; ++i)
            if (!primes[i]) {
                if (i >= 100)
                    ++all;
                for (int j = i * i; j < 1000; j += i)
                    primes[j] = true;
            }
    }

	void solve() throws IOException {
        primes();
        int n = ni();
        int [][][]dp = new int[10][10][n + 1];
        dp[0][0][0] = 1;
        int mod = 1000000000 + 9;
        int all = 0;
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 10; ++j)
                for (int k = 0; k < 10; ++k) {
                    for (int next = 0; next < 10; ++next) {
                        if (j > 0 && !primes[j * 100 + k * 10 + next] && i >= 2) {
                            ret = (ret + dp[j][k][i]) % mod;
                        } else {
                            dp[k][next][i + 1] = (dp[k][next][i + 1] + dp[j][k][i]) % mod;
                        }
                        
                    }
                }
        }
        int a = 0;
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < 10; ++j)
                a += dp[i][j][n];
        dbg(a);
        dbg(ret);
	}
	
	public Solution() throws IOException {
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
		if (args.length > 0 && args[0].equals("LOCAL_DEBUG")) {
			DEBUG = true;
		}
		new Solution();
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
