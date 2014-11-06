import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        boolean first = true;
        for (int t = ni(); t > 0; --t) {
            if (!first)
                out.println();
            first = false;
            char []s1 = ns().toCharArray();
            char []s2 = ns().toCharArray();
            int [][]dp = new int[s1.length + 1][s2.length + 1];
            Set<String> [][]prev = new Set[s1.length + 1][s2.length + 1];
            for (int i = 0; i < dp.length; ++i) {
                Arrays.fill(dp[i], Integer.MIN_VALUE);
                for (int j = 0; j < dp[i].length; ++j) {
                    prev[i][j] = new TreeSet<String>();
                }
            }
            prev[0][0].add("");
            dp[0][0] = 0;
            for (int i = 0; i <= s1.length; ++i) {
                for (int j = 0; j <= s2.length; ++j) {
                    relax(dp, prev, i, j, i + 1, j, dp[i][j], s1);
                    relax(dp, prev, i, j, i, j + 1, dp[i][j], s1);
                    if (i < s1.length && j < s2.length && s1[i] == s2[j]) {
                        relax(dp, prev, i, j, i + 1, j + 1, dp[i][j] + 1, s1);
                    }
                }
            }
            for (String s : prev[s1.length][s2.length])
                out.println(s);
        }
	}
    void relax(int [][]dp, Set<String> [][]prev, int i, int j, int ni, int nj, int value, char []s1) {
        if (ni < dp.length && nj < dp[ni].length) {
            if (dp[ni][nj] <= value) {
                if (dp[ni][nj] < value)
                    prev[ni][nj].clear();    
                dp[ni][nj] = value;
                for (String a : prev[i][j]) {
                    String s = a;
                    if (ni != i && nj != j) {
                        s = s + s1[i];
                    }
                    prev[ni][nj].add(s);
                } 
            }
        }
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
