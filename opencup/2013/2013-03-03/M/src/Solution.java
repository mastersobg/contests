import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, c, m;

	boolean[][] can;

	int[][] dp;
	final int mod = 1000000000 + 7;

	int rec(int cur, int prev) {
		for (int i = 0; i < c; ++i)
			dp[i][0] = 1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < c; ++j)
				if (dp[j][i] > 0) {
					for (int k = 0; k < c; ++k) {
						if (!can[j][k]) {
							dp[k][i + 1] = (dp[k][i + 1] + dp[j][i]) % mod;
						}
					}
				}
		}
		int ret = 0;
		for (int i = 0; i < c; ++i) {
			ret = (ret + dp[i][n - 1]) % mod;
		}
		return ret;
	}

	void solve() throws IOException {
		n = ni();
		c = ni();
		m = ni();
		can = new boolean[c + 1][c + 1];
		for (int i = 0; i < m; ++i) {
			int a = ni() - 1;
			int b = ni() - 1;
			can[a][b] = can[b][a] = true;
		}
		dp = new int[c + 1][n + 1];
		out.println(rec(0, c));
	}

	public Solution() throws IOException {
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
		new Solution();
	}
}
