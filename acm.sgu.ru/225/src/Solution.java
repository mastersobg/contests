import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[][] m2id;
	int[][] id2m;

	int n, k;
	long[][][] dp;

	void solve() throws IOException {
		n = ni();
		k = ni();
		Timer timer = new Timer();
		timer.start();
		m2id = new int[1 << n][1 << n];
		id2m = new int[2][30000];
		int id = 0;
		for (int m1 = 0; m1 < (1 << n); ++m1) {
			for (int m2 = 0; m2 < (1 << n); ++m2) {
				if (check(m1, m2)) {
					m2id[m1][m2] = id;
					id2m[0][id] = m1;
					id2m[1][id] = m2;
					++id;
				}
			}
		}
		dp = new long[2][k + 1][id];
		dp[0][0][0] = 1L;
		int cur = 0;
		for (int i = 0; i < n; ++i) {
			int next = cur ^ 1;
			for (int j = 0; j <= k; ++j)
				Arrays.fill(dp[next][j], 0);
			boolean was = false;
			for (int j = 0; j <= k; ++j) {
				for (int q = 0; q < id; ++q) {
					if (dp[cur][j][q] > 0) {
						was = true;
						go(cur, next, j, q, 0, 0, 0);
					}
				}
			}
			if (!was)
				break;
			cur = next;
		}
		long ret = 0;
		for (int i = 0; i < id; ++i) {
			ret += dp[cur][k][i];
		}
		out.println(ret);
		timer.print();
	}

	void go(int cur, int next, int set, int id, int col, int add, int mask) {
		if (col == n) {
			int m2 = id2m[1][id];
			int id1 = m2id[m2][mask];
			dp[next][set + add][id1] += dp[cur][set][id];
			return;
		}
		go(cur, next, set, id, col + 1, add, mask);
		int m1 = id2m[0][id];
		int m2 = id2m[1][id];
		if (set + add < k && !checkBit(m1, col - 1) && !checkBit(m1, col + 1)
				&& !checkBit(m2, col - 2) && !checkBit(m2, col + 2)) {
			go(cur, next, set, id, col + 1, add + 1, mask | (1 << col));
		}
	}

	boolean check(int m1, int m2) {
		for (int i = 0; i < n; ++i) {
			if (checkBit(m1, i)) {
				if (checkBit(m2, i - 2) || checkBit(m2, i + 2)) {
					return false;
				}
			}
		}
		return true;
	}

	boolean checkBit(int m, int bit) {
		if (bit >= n || bit < 0)
			return false;
		return (m & (1 << bit)) != 0;
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
