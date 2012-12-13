import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, m, k;
	int[] w, c, lw, lc;

	void solve() throws IOException {
		n = ni();
		m = ni();
		k = ni();
		w = new int[n];
		c = new int[n];
		lw = new int[n];
		lc = new int[n];
		for (int i = 0; i < n; ++i) {
			w[i] = ni();
			c[i] = ni();
		}
		lw[0] = w[0];
		lc[0] = c[0];
		for (int i = 1; i < n; ++i) {
			lw[i] += lw[i - 1] + w[i];
			lc[i] += lc[i - 1] + c[i];
		}
		int p1 = -1, p2 = m;
		int leftWeight = 0, rightWeight = lw[m - 1];
		int ret = -1;
		int left = -1, right = -1;
		while (true) {
			if (p1 >= 0)
				leftWeight -= w[p1];
			++p1;
			while (p2 < n && leftWeight * k < rightWeight) {
				rightWeight += w[p2];
				rightWeight -= w[p2 - m];
				leftWeight += w[p2 - m];
				++p2;
			}
			if (leftWeight * k < rightWeight)
				break;

			ass(checkSegment(p1, p2 - m, p2));

			int price = get(0, p1 - 1) + get(p2, n - 1);
			if (price > ret) {
				ret = price;
				left = p1;
				right = n - p2;
			}
		}
		out.println((left + right) + " " + ret);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < right; ++i)
			sb.append('H');
		for (int i = 0; i < left; ++i)
			sb.append('T');
		out.println(sb.toString());
//		check(sb.toString(), left + right, ret);
	}

	boolean checkSegment(int p1, int p2, int p3) {
		int lw = 0, rw = 0;
		for (int i = p1; i < p2; ++i)
			lw += w[i];
		for (int i = p2; i < p3; ++i)
			rw += w[i];
		return lw * k >= rw;
	}

	int get(int l, int r) {
		return get(r) - get(l - 1);
	}

	int get(int r) {
		if (r >= n)
			return lc[n - 1];
		return r < 0 ? 0 : lc[r];
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
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

	boolean check(String s, int killed, int sum) {
		int leftWeight = 0, rightWeight = 0;

		int ptr = n - 1;
		for (int i = 0; i < m; ++i, --ptr)
			rightWeight += w[ptr];
		for (int i = 0; i <= ptr; ++i)
			leftWeight += w[i];

		int p1 = 0, p2 = n - 1;
		for (int i = 0; i < s.length(); ++i) {
			ass(leftWeight * k >= rightWeight);
			if (s.charAt(i) == 'H') {
				rightWeight -= w[p2];
				rightWeight += w[p2 - m];
				leftWeight -= w[p2 - m];
				--p2;
			} else {
				leftWeight -= w[p1];
				++p1;
			}
		}
		ass(leftWeight * k >= rightWeight);
		return true;
	}

	void ass(boolean value) {
		if (!value)
			throw new RuntimeException();
	}
}
