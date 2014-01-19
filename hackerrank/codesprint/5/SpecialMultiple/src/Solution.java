import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	final int MAX_LEN = 50;

	String[][] dp;

	void solve() throws IOException {
		for (int t = ni(); t > 0; --t) {
			int n = ni();
			dp = new String[MAX_LEN][n];
			String ret = calc(n);
			out.println(ret);
		}
	}

	String calc(int n) {
		if (9 % n == 0)
			return "9";
		dp[1][9 % n] = "9";
		int[] arr = new int[] { 0, 9 };
		for (int i = 1; i < MAX_LEN - 1; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dp[i][j] != null) {
					for (int k = 0; k < arr.length; ++k) {
						int rest = (j * 10 + arr[k]) % n;
						String next = dp[i][j] + arr[k];
						if (dp[i + 1][rest] == null || dp[i + 1][rest].compareTo(next) > 0)
						dp[i + 1][rest] = next;
						if (rest == 0)
							return next;
					}
				}
			}
		}
		return "";
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
