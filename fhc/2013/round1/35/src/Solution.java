import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int m;
	char[] s1, s2;

	void solve() throws IOException {
		m = ni();
		s1 = ns().toCharArray();
		s2 = ns().toCharArray();
		for (int i = 0; i < s1.length; ++i) {
			if (s1[i] == '?') {
				boolean found = false;
				for (char ch = 'a'; ch <= 'f'; ++ch) {
					s1[i] = ch;
					if (can()) {
						found = true;
						break;
					}
				}
				if (!found) {
					out.println("IMPOSSIBLE");
					return;
				}
			}
		}
		if (!can()) {
			out.println("IMPOSSIBLE");
			return;
		}
		out.println(new String(s1));
	}

	boolean can() {
		Graph g = new Graph(s1, s2);
		return g.match();
	}

	class Graph {

		char[] s1, s2;
		ArrayList<Integer>[] g;

		Graph(char[] s1, char[] s2) {
			this.s1 = s1;
			this.s2 = s2;
			int n = m;
			g = new ArrayList[n];
			for (int i = 0; i < g.length; ++i)
				g[i] = new ArrayList<Integer>();
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (can(i, j)) {
						g[i].add(j);
					}
				}
			}
		}

		private boolean can(int p1, int p2) {
			int len = s1.length / g.length;
			for (int i = p1 * len, j = p2 * len, k = 0; k < len; ++i, ++j, ++k) {
				boolean ok = (s1[i] == '?' || s2[j] == '?' || s1[i] == s2[j]);
				if (!ok)
					return false;
			}
			return true;
		}

		boolean dfs(int v, boolean[] was, int[] mt) {
			if (was[v])
				return false;
			was[v] = true;
			for (int i = 0; i < g[v].size(); ++i) {
				int u = g[v].get(i);
				if (mt[u] == -1 || dfs(mt[u], was, mt)) {
					mt[u] = v;
					return true;
				}
			}
			return false;
		}

		private boolean match() {
			int n = g.length;
			int[] mt = new int[n];
			Arrays.fill(mt, -1);
			int ret = 0;
			boolean[] was = new boolean[n];
			for (int i = 0; i < n; ++i) {
				Arrays.fill(was, false);
				if (dfs(i, was, mt))
					++ret;
			}
			return ret == g.length;
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
