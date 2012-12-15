import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "lan";

	static class Hub implements Comparable<Hub> {
		int con, idx;

		public Hub(int con, int idx) {
			super();
			this.con = con;
			this.idx = idx;
		}

		@Override
		public int compareTo(Hub o) {
			if (con != o.con)
				return o.con - con;
			return idx - o.idx;
		}

	}

	void solve() throws IOException {
		int n = ni();
		int m = ni();
		Hub[] v = new Hub[m];
		for (int i = 0; i < m; ++i)
			v[i] = new Hub(ni(), i + 1);
		Arrays.sort(v);
		int connected = v[0].con;
		int i = 1;
		for (; i < m && connected < n; ++i) {
			connected += v[i].con - 2;
		}
		if (connected < n) {
			out.println("Epic fail");
			return;
		}
		out.println(i);
		for (int j = 0; j < i; ++j)
			out.print(v[j].idx + " ");
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
