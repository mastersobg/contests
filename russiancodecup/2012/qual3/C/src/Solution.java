import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	boolean inside(int a, int b, int c) {
		if (a <= b) {
			return a <= c && c <= b;
		} else {
			return c >= a || c <= b;
		}
	}

	boolean eq(int a, int b) {
		if (a == -1)
			return true;
		return a == b;
	}

	boolean can(int a, int pos, int l, int r) {
		if (a == pos)
			return true;
		if (a == -1) {
			return !inside(l, r, pos);
		}
		return false;
	}

	void solve() throws IOException {
		for (int t = ni(); t > 0; --t) {
			int a = ni();
			int b = ni();
			int h = ni();
			int m = ni();
			int ret = 0;
			for (int H = 0; H < 12; ++H)
				for (int M = 0; M < 60; ++M) {
					int mPos = M;
					int hPos = 5 * H;
					hPos += mPos / 12;
					if (can(h, hPos, a, b) && can(m, mPos, a, b)) {
						++ret;
					}
					// if (eq(h, hPos) && eq(m, mPos)) {
					// if (!inside(a, b, hPos) && !inside(a, b, mPos)) {
					// ++ret;
					// }
					// }
				}
			out.println(ret);
		}
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
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
