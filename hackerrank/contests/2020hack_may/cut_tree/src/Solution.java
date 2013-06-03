import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;

	long[][] dp;
	HashSet<Integer>[] g;

	long rec(int v, int t) {
		long ret = dp[v][t];
		if (ret == -1L) {
			if (g[v].size() == 0) {
				ret = t == 0 ? 1 : 0;
			} else {
				ret = calc(v, t);
			}
			dp[v][t] = ret;
		}
		return ret;
	}

	long calc(int parent, int k) {
		long ret = 0;
		int n = g[parent].size();
		List<Integer> list = new ArrayList<Integer>();
		for (int u : g[parent])
			list.add(u);
		long[][] dp = new long[n][k + 1];
		for (int i = 0; i < dp.length; ++i)
			Arrays.fill(dp[i], -1l);
		
		for(int i = 0; i <= k; ++i)
			ret += f(0, i, list, dp);
		return ret;
	}

	long f(int idx, int k, List<Integer> list, long[][] dp) {
		if (idx == list.size()) {
			return k == 0 ? 1 : 0;
		}
		long ret = dp[idx][k];
		if (ret == -1) {
			ret = 0;
			for (int i = 0; i <= k; ++i)
				ret += f(idx + 1, i, list, dp) * rec(list.get(idx), k - i);
			if (k > 0) {
				ret = (ret + f(idx + 1, k - 1, list, dp));
			}
			dp[idx][k] = ret;
		}
		return ret;
	}

	void dfs(int v, int p) {
		g[v].remove(p);
		for (int u : g[v])
			dfs(u, v);
	}

	void solve() throws IOException {
		n = ni();
		k = ni();
		g = new HashSet[n];
		for (int i = 0; i < n; ++i) {
			g[i] = new HashSet<Integer>();
		}

		for (int i = 1; i < n; ++i) {
			int v = ni() - 1;
			int u = ni() - 1;
			g[v].add(u);
			g[u].add(v);
		}
		dfs(0, -1);

		dp = new long[n][k + 1];
		for (int i = 0; i < n; ++i)
			Arrays.fill(dp[i], -1l);
		long ret = 0;
		for (int i = 0; i < n; ++i) {
			ret += rec(i, k);
		}
		out.println(ret + 1);
	}

	boolean DEBUG = true;

	void dbg(Object... args) {
		if (!DEBUG)
			return;
		for (Object o : args)
			System.err.print(o + " ");
		System.err.println();
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
