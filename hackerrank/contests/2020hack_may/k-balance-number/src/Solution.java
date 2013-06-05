import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	final long mod = 1000000007L;
	long l, r;
	int k;
	long[][][][] dp;
	long[][][][] cnt;

	long f1(int[] digits, int idx, int k, int cur, int less, int diff) {
		if (idx == digits.length)
			return 0;
		long ret = dp[less][cur][idx][diff];
		if (ret == -1) {
			ret = 0;
			if (idx != digits.length) {
				int n = digits.length;
				int nless = less == 1 ? 1 : (cur < digits[idx] ? 1 : 0);
				int ndiff = diff;
				if (idx < k)
					ndiff = diff + cur;
				else if (idx >= n - k)
					ndiff = diff - cur;
				if (ndiff >= 0 && (less == 1 || cur <= digits[idx])) {
					for (int i = 0; i < 10; ++i) {
						long nextRec = f1(digits, idx + 1, k, i, nless, ndiff);
						long nextCnt = f2(digits, idx + 1, k, i, nless, ndiff);
						ret = (ret + nextCnt * cur * pow10(n - idx - 1) + nextRec)
								% mod;
					}
				}
			}
			dp[less][cur][idx][diff] = ret;

		}
		return ret;
	}

	long f2(int[] digits, int idx, int k, int cur, int less, int diff) {
		if (idx == digits.length) {
			if (diff == 0 && cur == 0)
				return 1;
			return 0;
		}
		long ret = cnt[less][cur][idx][diff];
		if (ret == -1) {
			ret = 0;
			if (idx != digits.length) {
				int n = digits.length;
				int nless = less == 1 ? 1 : (cur < digits[idx] ? 1 : 0);
				int ndiff = diff;
				if (idx < k)
					ndiff = diff + cur;
				else if (idx >= n - k)
					ndiff = diff - cur;
				if (ndiff >= 0 && (less == 1 || cur <= digits[idx])) {
					for (int i = 0; i < 10; ++i) {
						ret = (ret + f2(digits, idx + 1, k, i, nless, ndiff))
								% mod;
					}
				}
			}
			cnt[less][cur][idx][diff] = ret;

		}
		return ret;
	}

	long pow10(int pow) {
		long ret = 1;
		for (int i = 0; i < pow; ++i) {
			ret = (ret * 10L) % mod;
		}
		return ret;
	}

	long sum(int[] digits, int k) {
		// less, index diff
		int n = digits.length;
		dp = new long[2][10][n][9 * n + 1];
		cnt = new long[2][10][n][9 * n + 1];
		for (int i = 0; i < dp.length; ++i)
			for (int j = 0; j < dp[i].length; ++j) {
				for (int j2 = 0; j2 < dp[i][j].length; ++j2) {
					Arrays.fill(dp[i][j][j2], -1L);
					Arrays.fill(cnt[i][j][j2], -1L);
				}
			}
		long ret = 0;
		for (int i = 1; i < 10; ++i)
			if (i <= digits[0])
				ret = (ret + f1(digits, 0, k, i, 0, 0)) % mod;
		return ret;
	}

	long solve(long r) {
		if (r == 0)
			return 0;
		long ret = 0;
		long value = 9;
		long prev = 1;
		int len = 1;
		while (value < r) {
			if (len <= this.k) {
				ret = (ret + progressSum(prev, value));
			} else {
				ret = (ret + sum(digits(value), k * 2 <= len ? k
						: (k - (k + k - len))))
						% mod;
			}
			++len;
			prev *= 10L;
			value = value * 10L + 9L;
		}
		if (len <= this.k) {
			return (ret + progressSum(prev, r)) % mod;
		} else {
			return (ret + sum(digits(r), k * 2 <= len ? k : (k - (k + k - len))))
					% mod;
		}
	}

	long progressSum(long l, long r) {
		long n = r - l + 1;
		if ((n & 1) == 1) {
			return (progressSum(l, r + 1) - (r % mod) - 1 + mod) % mod;
		}
		return (l + r) * (n / 2);
	}

	void solve() throws IOException {
		l = nl();
		r = nl();
		k = ni();
		long ret = (solve(r) - solve(l - 1) + mod) % mod;
		out.println(ret);
	}

	int[] digits(long n) {
		List<Integer> arr = new ArrayList<Integer>();
		while (n > 0) {
			arr.add((int) (n % 10L));
			n /= 10L;
		}
		Collections.reverse(arr);
		int[] ret = new int[arr.size()];
		for (int i = 0; i < arr.size(); ++i)
			ret[i] = arr.get(i);
		return ret;
	}

	public void run() throws IOException {
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
		new Solution().run();
	}
}
