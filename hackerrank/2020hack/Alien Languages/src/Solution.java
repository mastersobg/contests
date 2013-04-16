import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int letters, length;
	long[][] dp;

	long rec(int let, int pos) {
		long ret = dp[let][pos];
		if (ret == -1) {
			if (pos == length)
				ret = 1l;
			else {
				ret = 0;
				for (int i = let; i <= letters; ++i) {
					if (2 * i > letters) {
						ret += rec(1, pos + 1);
					} else {
						if (pos + 1 < length) {
							ret += rec(2 * i, pos + 1);
						}
					}
				}
			}
			dp[let][pos] = ret;
		}
		return ret;
	}

	void solve() throws IOException {
		// letters = ni();
		// length = ni();
		for (letters = 1; letters <= 10; ++letters) {
			for (length = 1; length < 10; ++length) {
				dp = new long[letters + 1][length + 1];
				for (int i = 0; i < dp.length; i++) {
					Arrays.fill(dp[i], -1);
				}
				long ret = 0;
				ret += rec(1, 0);
				out.println("letters=" + letters + " length=" + length
						+ " ret=" + ret);
			}
			out.println();
		}
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		// for (int t = ni(); t > 0; --t) {
		solve();
		// }
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
