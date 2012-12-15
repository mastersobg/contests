import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "frustum";

	double l, r;

	double getH(double r1, double r2) {
		double a = l * l - (r1 - r2) * (r1 - r2);
		return a;
		// return sqrt(a);
	}

	double sqr(double a) {
		return a * a;
	}

	double getVolume(double r1, double r2) {
		return sqr(PI) * getH(r1, r2) * sqr((r1 * r1 + r1 * r2 + r2 * r2))
				/ 9.0;
	}

	void solve() throws IOException {
		l = nd();
		r = nd() / 2.0;
		double left = 0.0, right = l + r;
		for (int i = 0; i < 10000; ++i) {
			// double mid = (left + right) / 2.0;
			double m1 = (right - left) / 3.0 + left;
			double m2 = right - (right - left) / 3.0;
			double v1 = getVolume(r, m1);
			double v2 = getVolume(r, m2);
			if (v1 < v2)
				left = m1;
			else
				right = m2;
		}
		out.println(sqrt(getVolume(r, left)));
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
