import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	char[] s;
	final int mod = 1000000007;
	int[][][] dp = new int[201][201][201];

	int rec(int p1, int p2, int p3) {
		if (p1 > p2)
			return 0;
		if (p3 >= s.length)
			return 0;
		int ret = dp[p1][p2][p3];
		if (ret == -1) {
			ret = 0;
			if (p1 == p2) {
				ret = (ret + (s[p2] == s[p3] ? 1 : 0)) % mod;
			}
			ret = (ret + rec(p1 + 1, p2, p3)) % mod;
			ret = (ret + rec(p1, p2, p3 + 1)) % mod;
			if (s[p1] == s[p3]) {
				ret = (ret + rec(p1 + 1, p2, p3 + 1)) % mod;
			}
			dp[p1][p2][p3] = ret;
		}
		return ret;
	}

	void solve() throws IOException {
		s = ns().toCharArray();
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		int ret = 0;
		for (int p1 = 0; p1 < s.length; ++p1)
			for (int p2 = p1; p2 < s.length; ++p2)
				// for (int p3 = p2 + 1; p3 < s.length; ++p3)
				ret = (ret + rec(p1, p2, p2 + 1)) % mod;
		out.println(ret);
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = ni();
		for (int i = 0; i < n; ++i) {
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
