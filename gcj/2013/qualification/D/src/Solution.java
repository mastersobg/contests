import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int k, n;
	int[] keys;
	int[] need;
	List<Integer>[] opens;

	int[] dp;

	int rec(int mask) {
		if (Integer.bitCount(mask) == n)
			return dp[mask] = 1;
		int ret = dp[mask];
		if (ret == -1) {
			ret = 0;
			for (int i = 0; i < n; ++i)
				if ((mask & (1 << i)) == 0) {
					int toOpen = need[i];
					if (keys[toOpen] > 0) {
						keys[toOpen]--;
						for (int el : opens[i]) {
							keys[el]++;
						}

						ret = max(ret, rec(mask | (1 << i)));

						keys[toOpen]++;
						for (int el : opens[i]) {
							keys[el]--;
						}
					}
				}
			dp[mask] = ret;
		}
		return ret;
	}

	void solve() throws IOException {
		keys = new int[201];
		k = ni();
		n = ni();
		for (int i = 0; i < k; ++i)
			keys[ni()]++;
		need = new int[n];
		opens = new ArrayList[n];
		for (int i = 0; i < opens.length; i++) {
			opens[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; ++i) {
			need[i] = ni();
			int cnt = ni();
			for (int j = 0; j < cnt; ++j)
				opens[i].add(ni());
		}
		dp = new int[1 << n];
		Arrays.fill(dp, -1);
		rec(0);
		if (dp[0] == 0)
			out.println("IMPOSSIBLE");
		else {
			int mask = 0;
			List<Integer> ret = new ArrayList<Integer>();
			gl: for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j)
					if ((mask & (1 << j)) == 0) {
						if (dp[mask | (1 << j)] == 1) {
							int toOpen = need[j];
							if (keys[toOpen] > 0) {
								keys[toOpen]--;
								for (int el : opens[j]) {
									keys[el]++;
								}
								ret.add(j);
								mask |= 1 << j;
								continue gl;
							}
						}
					}
			}
			for (int i = 0; i < n; ++i) {
				if (i > 0)
					out.print(" ");
				out.print(ret.get(i) + 1);
			}
			out.println();
		}
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
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
