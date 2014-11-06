import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    int [][][]dp;

	void solve() throws IOException {
        for (int t = ni(); t > 0; --t) {
            int n = ni();
            int k = ni();
            dp = new int[n + 1][k + 1][1 << n];
            for (int i = 0; i <= n; ++i)
                for (int j = 0; j <= k; ++j)
                    Arrays.fill(dp[i][j], -1);

            out.println(rec(0, 0, n, 0, k));
        }
	}

    int rec(int pos, int mask, int n, int k, int maxK) {
        if (k > maxK) {
            return 0;
        }
        if (pos == n) {
            return k == maxK ? 1 : 0;
        }
        int ret = dp[pos][k][mask];
        if (ret == -1) {
            int more = Integer.bitCount(mask);
            ret = 0;
            for (int i = 0; i < n; ++i)
                if ((mask & (1 << i)) == 0) {
                    ret += rec(pos + 1, mask | (1 << i), n, k + more, maxK);
                } else {
                    --more;
                }
            dp[pos][k][mask] = ret;
        }
        return ret;
    }
	
	public Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
        out.flush();
        // runtime error if trying to close on spoj
		// in.close();
		// out.close();
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
		new Main();
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
