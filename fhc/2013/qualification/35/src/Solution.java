import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	byte[][] dp;
	char[] s;
	int n;

	byte rec(int pos, int opened) {
		if (pos == n) {
			return opened == 0 ? (byte) 1 : (byte) 0;
		}
		if (dp[pos][opened] < 0) {
			byte ret = 0;
			char c = s[pos];
			if (c == '(') {
				ret = (byte) max(ret, rec(pos + 1, opened + 1));
			} else if (c == ')') {
				if (opened > 0) {
					ret = (byte) max(ret, rec(pos + 1, opened - 1));
				}
			} else if (c == ':') {
				if (pos + 1 < n && s[pos + 1] == '(' || s[pos + 1] == ')') {
					ret = (byte) max(ret, rec(pos + 2, opened));
				}
				ret = (byte) max(ret, rec(pos + 1, opened));
			} else {
				ret = (byte) max(ret, rec(pos + 1, opened));
			}
			dp[pos][opened] = ret;
		}
		return dp[pos][opened];
	}

	void solve() throws IOException {
		s = in.readLine().toCharArray();
		n = s.length;
		dp = new byte[n + 1][n + 1];
		for (int i = 0; i < dp.length; ++i) {
			Arrays.fill(dp[i], (byte) -1);
		}
		byte ret = rec(0, 0);
		out.println(ret == 1 ? "YES" : "NO");
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int it = 0; it < t; ++it) {
			out.print("Case #" + (it + 1) + ": ");
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
