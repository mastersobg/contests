import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, m, k;
	int[] w, c;
	int[] lw, lc;

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
		int pos = calc(n - m - 1, lw[n - 1] - lw[n - m - 1]);
		if (pos == -1) {
			out.println("0 0");
			return;
		}
		int ret = price(pos, 0);
		int right = n, left = pos;
		int add = 0;
		for (int i = n - 1; i >= m + 1; --i) {
			add += c[i];
			pos = calc(i - m - 1, i - m - 1 >= 0 ? lw[i - 1] - lw[i - m - 1]
					: lw[i - 1]);
			if (pos == -1)
				continue;
			int pr = price(pos, add);
			if (pr > ret) {
				ret = pr;
				right = i;
				left = pos;
			}
		}
		out.println((left + n - right) + " " + ret);
		for (int i = n - 1; i >= right; --i)
			out.print("H");
		for (int i = 0; i < left; ++i)
			out.print("T");
	}

	int price(int pos, int add) {
		return add + (pos <= 0 ? 0 : lc[pos - 1]);
	}

	int calc(int last, int w) {
		int l = 0, r = last;
		while (l + 1 < r) {
			int mid = (l + r) >> 1;
			if (can(last, mid, w))
				l = mid;
			else
				r = mid;
		}
		if (can(last, r, w))
			return r;
		if (can(last, l, w))
			return l;
		return -1;
	}

	boolean can(int last, int pos, int w) {
		return (get(last) - get(pos - 1)) * k >= w;
	}

	int get(int pos) {
		if (pos < 0)
			return 0;
		return lw[pos];
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
}
