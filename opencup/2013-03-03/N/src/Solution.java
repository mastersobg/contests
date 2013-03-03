import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[] v;
	int n, k;

	final long INF = 1l << 60l;

	long rec(int pos, int got) {
		long[][] dp = new long[2][n + 1];
		int idx = 0;
		for (int i = 0; i < k; ++i) {
			int next = (idx + 1) % 2;
			Arrays.fill(dp[next], INF);
			for (int j = 0; j < n; ++j) {
				if (j < n - 1 && dp[idx][j] < INF) {
					dp[next][j + 2] = min(dp[next][j + 2], dp[idx][j]
							+ (long) (v[j + 1] - v[j]));
				}
				dp[idx][j + 1] = min(dp[idx][j + 1], dp[idx][j]);
			}
			idx = (idx + 1) % 2;
		}
		long min = INF;
		for (int i = 0; i <= n; ++i)
			min = min(min, dp[idx][i]);
		return min;
		// if (pos == v.length) {
		// if (got == k)
		// return 0;
		// return 1l << 50l;
		// }
		// long ret = dp[pos][got];
		// if (ret < 0) {
		// ret = rec(pos + 1, got);
		// if (pos + 2 <= v.length) {
		// ret = min(ret, rec(pos + 2, got + 1) + v[pos + 1] - v[pos]);
		// }
		// dp[pos][got] = ret;
		// }
		// return ret;
	}

	void solve() throws IOException {
		n = ni();
		k = ni();
		v = new int[n];
		for (int i = 0; i < n; ++i) {
			v[i] = ni();
		}
		Arrays.sort(v);
		long max = 0;
		int p1 = 0, p2 = v.length - 1;
		for (int i = 0; i < k; ++i) {
			max += (long) (v[p2] - v[p1]);
			++p1;
			--p2;
		}
		out.println(max + " " + rec(0, 0));
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
