import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int k;
	char[] s;
	char[] s1, s2;

	int[][] dp;

	int rec(int l, int r) {
		if (r == s.length) {
			return k;
		}
		int ret = dp[l][r];
		if (ret < 0) {
			int a = rec(l + 1, r + 1);
			if (s[l] == s[r]) {
				ret = a + 1;
			} else {
				ret = find(l, r, a);
			}
			dp[l][r] = ret;
		}
		return ret;
	}

	int find(int i, int j, int len) {
		while (len > 0) {
			if (s1[i + len] != s2[j + len]) {
				break;
			}
			--len;
		}
		return len;
	}
	
  void solve() throws IOException {
		k = ni();
		String str = ns();
		s = str.toCharArray();
		s1 = getString(str, k + 10, 'A');
		s2 = getString(str, k + 10, 'B');
		dp = new int[s.length + 1][s.length + 1];
		for (int i = 0; i < dp.length; ++i) {
			Arrays.fill(dp[i], -1);
		}
		long ret = 0;
		for (int i = 0; i < s.length; ++i) {
			for (int j = i + 1; j < s.length; ++j) {
				int a = rec(i, j);
				if (j + a > s.length) {
					a = s.length - j;
				}
				ret += a;
			}
		}
		out.println(ret);
	}

	char[] getString(String s, int k, char ch) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < k; ++i) {
			sb.append(ch);
		}
		return sb.toString().toCharArray();
	}

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
}
