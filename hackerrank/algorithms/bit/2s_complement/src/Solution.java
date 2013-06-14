import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long[][] dp;
	long[][] cnt;
	int[] bits;

	long rec(int idx, int less, int bit) {
		if (idx == 32) {
			return 0L;
		}
		long ret = dp[less][idx];
		if (ret == -1) {
			ret = 0;
			for (int i = 0; i < 2; ++i) {
				int nless = less == 1 ? 1 : (i < bits[idx] ? 1 : 0);
				if (nless == 1 || (bits[idx] >= i)) {
					ret += rec(idx + 1, nless, bit);
					if (bit == i) {
						ret += count(idx + 1, nless, bit);
					}
				}
			}
			dp[less][idx] = ret;
		}
		return ret;
	}

	long count(int idx, int less, int bit) {
		if (idx == 32) {
			return 1L;
		}
		long ret = cnt[less][idx];
		if (ret == -1) {
			ret = 0;
			for (int i = 0; i < 2; ++i) {
				int nless = less == 1 ? 1 : (i < bits[idx] ? 1 : 0);
				if (nless == 1 || (bits[idx] >= i)) {
					long cnt = count(idx + 1, nless, bit);

					ret += cnt;
				}
			}
			cnt[less][idx] = ret;
		}
		return ret;
	}

	long solve(long r, int bit) {
		dp = new long[2][32];
		cnt = new long[2][32];
		Arrays.fill(dp[0], -1L);
		Arrays.fill(dp[1], -1L);
		Arrays.fill(cnt[0], -1L);
		Arrays.fill(cnt[1], -1L);
		bits = toBits(r);
		return rec(0, 0, bit);
	}

	int[] toBits(long value) {
		int[] ret = new int[32];
		for (int i = 31; i >= 0; --i)
			if ((value & (1L << (long) i)) != 0) {
				ret[31 - i] = 1;
			} else {
				ret[31 - i] = 0;
			}
		return ret;
	}

	void solve() throws IOException {

		long l = nl();
		long r = nl();
		long ret = 0;
		if (r >= 0 && l < 0) {
			ret = solve(r, 1) + solve(abs(l + 1), 0);
		} else {
			if (r >= 0)
				ret = solve(r, 1) - solve(max(l - 1, 0), 1);
			else {
				long a = r == -1 ? 0 : solve(abs(r + 2), 0);
				ret = solve(abs(min(l + 1, 0)), 0) - a;
			}
		}
		out.println(ret);
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
