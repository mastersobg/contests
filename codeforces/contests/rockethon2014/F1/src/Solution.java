import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;

	long[] v;

	void solve() throws IOException {
		n = ni();
		k = ni();
		v = new long[n];
		for (int i = 0; i < n; ++i)
			v[i] = nl();
		out.println(calc());
	}

	long calc() {
		long[][] dp = new long[k + 1][n + 1];
		long []sets = new long[k + 1];
		Arrays.fill(sets, Long.MIN_VALUE);
//		TreeSet<Long>[] sets = new TreeSet[k + 1];
//		for (int i = 0; i < sets.length; ++i)
//			sets[i] = new TreeSet<Long>();

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= k; ++j) {
				dp[j][i + 1] = max(dp[j][i + 1], dp[j][i]);
//				sets[j].add(dp[j][i] - v[i]);
				sets[j] = max(sets[j] ,dp[j][i] - v[i]);
				if (j < k && sets[j] != Long.MIN_VALUE) {
					long a = sets[j];
					dp[j + 1][i + 1] = max(dp[j + 1][i + 1], a + v[i]);
				}
				//
				// if (j < k) {
				// for (int next = i + 1; next < n; ++next) {
				// dp[j + 1][next + 1] = max(dp[j + 1][next + 1], dp[j][i]
				// + v[next] - v[i]);
				// }
				// }
			}
		}
		long max = 0;
		for (int i = 0; i <= k; ++i)
			max = max(dp[i][n], max);
		return max;
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
