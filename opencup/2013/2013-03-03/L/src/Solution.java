import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int a, b;

	void solve() throws IOException {
		String s = ns();
		int len = s.length();
		a = Integer.valueOf(s, 2);
		b = Integer.valueOf(ns(), 2);
		int size = 10000;
		double[][] dp = new double[1 << len][size];
		dp[0][0] = 1.0;
		int cut = (1 << len) - 1;
		double ret = 0.0;
		for (int i = 0; i < size - 1; ++i) {
			for (int j = 0; j < (1 << len); ++j) {
				int nmask = (j << 1) & cut;
				if (nmask == a) {
					ret += dp[j][i] * 0.5;
				} else if (nmask != b) {
					dp[nmask][i + 1] += dp[j][i] * 0.5;
				}
				nmask = ((j << 1) | 1) & cut;
				if (nmask == a) {
					ret += dp[j][i] * 0.5;
				} else if (nmask != b) {
					dp[nmask][i + 1] += dp[j][i] * 0.5;
				}
			}
		}
		out.println(ret);
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
