import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	final int MAX_PRECALC_LENGTH = 45;
	final int MOD = 100000007;

	int letters, length;
	int[][] dp;

	int rec(int let, int pos) {
		int ret = dp[let][pos];
		if (ret == -1) {
			if (pos == 0)
				ret = 1;
			else {
				ret = 0;
				for (int i = let; i <= letters; ++i) {
					if (2 * i > letters) {
						ret = (ret + rec(1, pos - 1)) % MOD;
					} else {
						if (pos + 1 < length) {
							ret = (ret + rec(2 * i, pos - 1)) % MOD;
						}
					}
				}
			}
			dp[let][pos] = ret;
		}
		return ret;
	}

	int[] gauss(int[][] arr) {
		return null;
	}

	void solve() throws IOException {
		letters = ni();
		length = ni();
		dp = new int[letters + 1][MAX_PRECALC_LENGTH];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		final int EQUATIONS_COUNT = 19;
		int[][] arr = new int[EQUATIONS_COUNT][EQUATIONS_COUNT + 1];
		for (int i = 0; i < EQUATIONS_COUNT; ++i) {
			int idx = 20 + i;
			for (int j = 0; j < EQUATIONS_COUNT; ++j) {
				arr[i][j] = rec(letters, idx - j - 1);
			}
			arr[i][EQUATIONS_COUNT] = rec(letters, idx);
		}
		int[] ret = gauss(arr);
		int[] d = new int[length + 1];
		for (int i = 1; i < MAX_PRECALC_LENGTH && i <= length; ++i)
			d[i] = rec(letters, i);
		for (int i = MAX_PRECALC_LENGTH; i <= length; ++i) {
			for (int j = 0; j < EQUATIONS_COUNT; ++j) {
				int sum = (d[i - j - 1] * ret[j]) % MOD;
				d[i] = (d[i] + sum) % MOD;
			}
		}
		out.println(d[length]);
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
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
