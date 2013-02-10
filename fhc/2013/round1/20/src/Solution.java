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

	int[][] dp;

	int rec(int i, int j) {
		if (j == k)
			return v[i - 1];
		if (i == n)
			return 0;
		int ret = dp[i][j];
		if (ret < 0) {
			ret = rec(i + 1, j + 1);
			ret = (ret + rec(i + 1, j)) % mod;
			dp[i][j] = ret;
		}
		return ret;
	}

	void solve() throws IOException {
		n = ni();
		k = ni();
		v = new int[n];
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < n; ++i) {
			int value = ni();
			if (set.contains(value))
				throw new RuntimeException();
			set.add(value);
			v[i] = value % mod;
		}
		Arrays.sort(v);
		dp = new int[n + 1][k + 1];
		for (int i = 0; i < dp.length; ++i)
			Arrays.fill(dp[i], -1);
		out.println(rec(0, 0));
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
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
		new Solution();
	}
}
