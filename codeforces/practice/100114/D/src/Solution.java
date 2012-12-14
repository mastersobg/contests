import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	char[] s;

	int[] stars, dots;

	void solve() throws IOException {
		n = ni();
		s = ns().toCharArray();
		stars = new int[n + 1];
		dots = new int[n + 1];
		for (int i = 0; i < s.length; ++i) {
			stars[i + 1] = stars[i] + (s[i] == '*' ? 1 : 0);
			dots[i + 1] = dots[i] + (s[i] == '.' ? 1 : 0);
		}
		int ret = stars[n];
		int l = -1, r = -1;
		int cl = -1;
		int cst = 0;
		int cdo = 0;
		for (int i = 0; i < s.length; ++i) {
			if (cl != -1) {
				if (s[i] == '.')
					++cdo;
				else
					++cst;
				if (cst < 1 + cdo) {
					cl = -1;
				}
			} else {
				if (s[i] == '*') {
					cl = i;
					cdo = 0;
					cst = 1;
				}
			}

			if (cl != -1) {
				int cost = 2 + cdo + countStars(0, cl) + countStars(i + 1, n);
				if (ret > cost) {
					ret = cost;
					l = cl;
					r = i;
				}
			}
		}
		out.println(ret);
		if (l == -1 && r == -1) {
			outCtrl(0, n);
		} else {
			out.println((l + 1));
			out.println("Shift+" + (r + 1));
			for (int i = l; i <= r; ++i)
				if (s[i] == '.')
					out.println("Ctrl+" + (i + 1));
			outCtrl(0, l);
			outCtrl(r + 1, n);
		}
	}

	void outCtrl(int l, int r) {
		for (int i = l; i < r; ++i)
			if (s[i] == '*')
				out.println("Ctrl+" + (i + 1));
	}

	int countStars(int l, int r) {
		return stars[r] - stars[l];
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
