import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "compression";

	int n;
	String[] v;
	long best = 1l << 62l;
	int[] g;
	int depth;

	int maxDepth(int x1, int x2) {
		for (int i = 39; i >= 0; --i)
			if (precalc[i][x1] == precalc[i][x2])
				return i;
		return -1;
	}

	long calc(int[] groups, int d) {
		int cnt = 1;
		long ret = 0;
		for (int i = 1; i < n; ++i) {
			if (groups[i] != groups[i - 1]) {
				if (cnt == 1) {
					ret += (1L << (40l - (long) v[i - 1].length()));
				} else {
					ret += (1l << (40l - d));
				}
				cnt = 1;
			} else
				++cnt;
		}
		if (cnt == 1) {
			ret += (1L << (40l - (long) v[n - 1].length()));
		} else {
			ret += (1l << (40l - d));
		}
		return ret;
	}

	void update(int[] groups, int d) {
		long c = calc(groups, d);
		if (c <= best) {
			best = c;
			g = groups.clone();
			depth = d;
		}
	}

	void recalc(int[] old, int[] next, int chr) {
		for (int i = 1; i < n; ++i) {
			if (old[i] != old[i - 1]) {
				next[i] = next[i - 1] + 1;
			} else {
				if (v[i - 1].length() <= chr || v[i].length() <= chr)
					next[i] = next[i - 1];
				else {
					char c1 = v[i - 1].charAt(chr);
					char c2 = v[i].charAt(chr);
					next[i] = next[i - 1] + (c1 == c2 ? 0 : 1);
				}
			}
		}
	}

	void print(String s, int depth) {
		for (int i = 0; i < depth && i < s.length(); ++i)
			out.print(s.charAt(i));
		out.println();
	}

	void print(int[] g, int depth) {
		out.println(best);
		out.println(g[n - 1] + 1);
		int cnt = 0;
		for (int i = 1; i < n; ++i)
			if (g[i] != g[i - 1]) {
				if (cnt == 1)
					out.println(v[i - 1]);
				else
					print(v[i - 1], depth);
				cnt = 1;
			} else
				++cnt;
		if (cnt == 1)
			out.println(v[n - 1]);
		else
			print(v[n - 1], depth);
	}

	int[][] precalc;

	void solve() throws IOException {
		n = ni();
		v = new String[n];
		for (int i = 0; i < n; ++i)
			v[i] = ns();
		Arrays.sort(v);
		int[] groups = new int[n];
		int[] next;
		update(groups, 0);
		precalc = new int[40][];
		for (int i = 1; i <= 40; ++i) {
			next = new int[n];
			recalc(groups, next, i - 1);
			// update(next, i);
			precalc[i - 1] = next;
			groups = next;
		}
		for (int i = 1; i <= 40; ++i) {
			update(precalc[i - 1], i);
		}
		print(g, depth);
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
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
