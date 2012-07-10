import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "hard";

	int[] g;
	ArrayList<Integer>[] rg;
	boolean[] was;
	int[] stack;

	int a, b, c, n;

	int calc(long a, long b, long c, long n, long v) {
		long ret = a * v * v + b * v + c;
		ret %= n;
		return (int) ret;
	}

	int pr(int v) {
		was[v] = true;
		int ret = 1;
		for (int u : rg[v]) {
			ret = max(ret, pr(u) + 1);
		}
		return ret;
	}

	int process(int v) {
		int sz = 0;
		while (true) {
			was[v] = true;
			stack[sz++] = v;
			int u = g[v];
			if (was[u]) {
				HashSet<Integer> set = new HashSet<Integer>();
				int cnt = 1;
				for (int cur = u, j = sz - 1;;) {
					set.add(cur);
					++cnt;
					cur = stack[j];
					--j;
					if (cur == u)
						break;

				}
				cnt = set.size();
				int mx = 0;
				for (int vertex : set) {
					for (int uv : rg[vertex])
						if (set.contains(uv) == false)
							mx = max(mx, pr(uv));
				}
				return cnt + mx;
			}
			v = u;
		}
	}

	void solve() throws IOException {
		for (int t = ni(); t > 0; --t) {
			a = ni();
			b = ni();
			c = ni();
			n = ni();
			g = new int[n + 1];
			rg = new ArrayList[n + 1];
			for (int i = 0; i < rg.length; i++) {
				rg[i] = new ArrayList<Integer>();
			}
			was = new boolean[n + 1];
			stack = new int[n + 1];
			for (int i = 0; i <= n; ++i) {
				int next = calc(a, b, c, n, i);
				g[i] = next;
				rg[next].add(i % n);
			}
			int ret = 0;
			for (int i = 0; i < n; ++i) {
				if (!was[i])
					ret = max(ret, process(i));
			}
			out.println(ret);
			out.flush();
			System.err.println(t);
		}
	}

	public Solution() throws IOException {
		in = new BufferedReader(new FileReader(filename + ".in"));
		out = new PrintWriter(filename + ".out");
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
