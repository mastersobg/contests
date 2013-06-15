import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;
	int[] v;

	final int mod = 1000000007;

	int rec(int idx, int k, int len, int[][] dp) {
		if (idx == len) {
			return 1;
		}
		int ret = dp[idx][k];
		if (ret == -1) {
			ret = 0;
			for (int i = 0; i < 2; ++i) {
				int index = idx;
				int total = 0;
				while (index < n) {
					if (v[index] != i)
						++total;
					index += len;
				}
				if (k - total >= 0) {
					ret = (ret + rec(idx + 1, k - total, len, dp)) % mod;
				}
			}
			dp[idx][k] = ret;
		}
		return ret;
	}

	int go(int len) {
		int[][] dp = new int[len][k + 1];
		for (int i = 0; i < len; ++i)
			Arrays.fill(dp[i], -1);
		return rec(0, k, len, dp);
	}

	void solve() throws IOException {
		// long start = System.currentTimeMillis();
		n = ni();
		k = ni();
		v = new int[n];
		String s = ns();
		for (int i = 0; i < s.length(); ++i)
			v[i] = s.charAt(i) - '0';
		int[] f = new int[n + 1];
		for (int i = 1; i <= n; ++i) {
			if (n % i == 0) {
				f[i] = go(i);
				for (int j = 1; j < i; ++j)
					if (i % j == 0) {
						f[i] = (f[i] - f[j] + mod) % mod;
					}
			}
		}
		out.println(f[n]);
		// System.err.println(System.currentTimeMillis() - start);
	}

	boolean DEBUG = false;

	void dbg(Object... args) {
		if (!DEBUG)
			return;
		for (Object o : args)
			System.err.print(o + " ");
		System.err.println();
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		 in = new BufferedReader(new InputStreamReader(System.in));
		 out = new PrintWriter(System.out);
		// in = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		for (int t = ni(); t > 0; --t) {
			solve();
		}
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
}
