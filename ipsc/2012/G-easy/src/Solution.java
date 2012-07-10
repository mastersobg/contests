import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "easy";

	boolean[] was;

	int solve(int a, int b, int c, int n, int v) {
		Arrays.fill(was, false);
		was[v] = true;
		int ret = 1;
		while (true) {
			int next = (a * v * v + b * v + c) % n;
			if (was[next])
				break;
			++ret;
			was[next] = true;
			v = next;
		}
		return ret;
	}

	void solve() throws IOException {
		for (int t = ni(); t > 0; --t) {
			int a = ni(), b = ni(), c = ni(), n = ni();
			was = new boolean[n];
			int ret = 0;
			for (int i = 0; i < n; ++i) {
				ret = max(ret, solve(a, b, c, n, i));
			}
			out.println(ret);
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
