import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	int[] v;

	int move(int r1, int c1, int r2) {
		int pos = c1;
		int add = (r1 < r2) ? 1 : -1;
		for (int i = r1; i != r2; i += add)
			pos = min(pos, v[i]);
		pos = min(pos, v[r2]);
		return pos;
	}

	void solve() throws IOException {
		n = ni();
		v = new int[n + 1];
		for (int i = 1; i <= n; ++i)
			v[i] = ni() + 1;
		int r1 = ni();
		int c1 = ni();
		int r2 = ni();
		int c2 = ni();

		int ret = 1 << 29;

		for (int i = 1; i <= n; ++i) {
			int a = abs(r1 - i);
			int pos = move(r1, c1, i);
			a += abs(i - r2);
			pos = move(i, pos, r2);
			a += abs(pos - c2);
			ret = min(ret, a);
		}
		out.println(ret);
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
