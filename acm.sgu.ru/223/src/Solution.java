import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;

	long[][][] dp;

	void go(int row, int kings, int mask, int nmask, int col, int set) {
		if (col >= n) {
			dp[row + 1][kings - set][nmask] += dp[row][kings][mask];
			return;
		}
		go(row, kings, mask, nmask, col + 1, set);
		if (!checkBit(mask, col - 1) && !checkBit(mask, col)
				&& !checkBit(mask, col + 1) && kings - set > 0) {
			go(row, kings, mask, nmask | (1 << col), col + 2, set + 1);
		}
	}

	boolean checkBit(int mask, int bit) {
		if (bit < 0 || bit >= n)
			return false;
		return (mask & (1 << bit)) != 0;
	}

	void solve() throws IOException {
		n = ni();
		k = ni();
		dp = new long[n + 1][k + 1][1 << n];
		dp[0][k][0] = 1L;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= k; ++j)
				for (int q = 0; q < (1 << n); ++q) {
					if (dp[i][j][q] > 0) {
						go(i, j, q, 0, 0, 0);
					}
				}
		}
		long ret = 0;
		for (int i = 0; i < (1 << n); ++i)
			ret += dp[n][0][i];
		out.println(ret);
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
