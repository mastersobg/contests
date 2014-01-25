import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int m, n;
	int[][] v;
	int[][][][][] dp;

	void solve() throws IOException {
		m = ni();
		n = ni();
		v = new int[2][n];
		for (int i = 0; i < n; ++i) {
			v[0][i] = ni();
			v[1][i] = ni();
		}
		dp = new int[m + 1][n + 1][n + 1][n + 1][n + 1];
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j < dp[i].length; ++j)
				for (int j1 = 0; j1 < dp[i][j].length; ++j1)
					for (int j2 = 0; j2 < dp[i][j][j1].length; ++j2)
						Arrays.fill(dp[i][j][j1][j2], Integer.MAX_VALUE);
		}
		dp[0][0][0][0][0] = 0;
		for (int baloons = 0; baloons < m; ++baloons) {
			for (int i4 = n; i4 >= 0; --i4)
				for (int i3 = n; i3 >= 0; --i3)
					for (int i2 = n; i2 >= 0; --i2)
						for (int i1 = n; i1 >= 0; --i1) {
							if (dp[baloons][i1][i2][i3][i4] < Integer.MAX_VALUE) {
								for (int next = 1; next <= n; ++next) {
									if (can(next - 1, i1, i2, i3, i4)) {
										int nbaloons = min(m, baloons
												+ v[0][next - 1]);
										dp[nbaloons][i2][i3][i4][next] = min(
												dp[nbaloons][i2][i3][i4][next],
												dp[baloons][i1][i2][i3][i4] + 1);
									}
								}
								dp[baloons][i2][i3][i4][0] = min(
										dp[baloons][i2][i3][i4][0],
										dp[baloons][i1][i2][i3][i4] + 1);
							}
						}
		}
		int min = Integer.MAX_VALUE;
		for (int i1 = 0; i1 <= n; ++i1)
			for (int i2 = 0; i2 <= n; ++i2)
				for (int i3 = 0; i3 <= n; ++i3)
					for (int i4 = 0; i4 <= n; ++i4) {
						min = min(min, dp[m][i1][i2][i3][i4]);
					}
		out.println(min);
	}

	boolean can(int next, int i1, int i2, int i3, int i4) {
		if (next == i4 - 1)
			return false;
		if (next == i3 - 1)
			return v[1][next] < 2;
		if (next == i2 - 1)
			return v[1][next] < 3;
		if (next == i1 - 1)
			return v[1][next] < 4;
		return true;
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
