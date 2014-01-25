import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.text.AbstractDocument.LeafElement;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;
	long[][] dp;

	void solve() throws IOException {
		n = ni();
		k = ni();
		List<Integer> list1 = new ArrayList<Integer>(), list2 = new ArrayList<Integer>();
		int cnt = 1;
		boolean black = true;
		for (int i = 0; i < n; ++i, cnt++) {
			if (black) {
				list1.add(cnt);
			} else {
				list2.add(cnt);
			}
			black = !black;
		}
		cnt -= 2;
		for (int i = 1; i < n; ++i, --cnt) {
			if (black) {
				list1.add(cnt);
			} else {
				list2.add(cnt);
			}
			black = !black;
		}
		int[] arr1 = transform(list1);
		int[] arr2 = transform(list2);
		BigInteger[][] d1 = calc(arr1);
		BigInteger[][] d2 = calc(arr2);
		BigInteger ret = BigInteger.ZERO;
		for (int i = 0; i <= k; ++i) {
			ret = ret.add(d1[0][i].multiply(d2[0][k - i]));
		}
		out.println(ret);
	}

	BigInteger[][] calc(int[] arr) {
		int n = arr.length;
		BigInteger[][] dp = new BigInteger[n + 1][k + 1];
		for (int i = 0; i < dp.length; ++i) {
			for (int j = 0; j < dp[i].length; ++j)
				dp[i][j] = BigInteger.ZERO;
		}
		dp[n][0] = BigInteger.ONE;
		for (int i = n; i > 0; --i) {
			for (int j = 0; j <= k; ++j) {
				dp[i - 1][j] = dp[i - 1][j].add(dp[i][j]);
				if (j + 1 <= k && arr[i - 1] - j > 0) {
					dp[i - 1][j + 1] = dp[i - 1][j + 1].add(dp[i][j]
							.multiply(BigInteger.valueOf(arr[i - 1] - j)));
				}
			}
		}
		return dp;
	}

	int[] transform(List<Integer> list) {
		int max = 0;
		for (int a : list) {
			max = max(a, max);
		}
		int[] ret = new int[max];
		for (int i = 0; i < max; ++i) {
			int value = i + 1;
			int cnt = 0;
			for (int a : list)
				if (value <= a)
					++cnt;
			ret[i] = cnt;
		}
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

	class Timer {

		long time;

		void start() {
			time = System.currentTimeMillis();
		}

		long time() {
			return System.currentTimeMillis() - time;
		}

		void print() {
			print("Time spent = ");
		}

		void print(String message) {
			dbg(message, time());
		}

	}

	static boolean DEBUG = true;

	void dbg(Object... objs) {
		if (!DEBUG) {
			return;
		}
		for (Object o : objs) {
			String printLine;
			if (o.getClass().isArray()) {
				printLine = arrayToString(o);
			} else {
				printLine = o.toString();
			}
			System.err.print(printLine + " ");
		}
		System.err.println();
	}

	String arrayToString(Object o) {
		if (o instanceof long[])
			return Arrays.toString((long[]) o);
		if (o instanceof int[])
			return Arrays.toString((int[]) o);
		if (o instanceof short[])
			return Arrays.toString((short[]) o);
		if (o instanceof char[])
			return Arrays.toString((char[]) o);
		if (o instanceof byte[])
			return Arrays.toString((byte[]) o);
		if (o instanceof double[])
			return Arrays.toString((double[]) o);
		if (o instanceof float[])
			return Arrays.toString((float[]) o);
		if (o instanceof boolean[])
			return Arrays.toString((boolean[]) o);
		if (o instanceof Object[])
			return Arrays.deepToString((Object[]) o);
		throw new IllegalStateException();
	}

}
