import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int n = ni();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) {
			v[i] = ni();
		}
		Arrays.sort(v);
		int p1 = 0, p2 = 0;
		while (p2 < n && v[p1] * 2 >= v[p2]) {
			++p2;
		}
//		int ret = (p2 == n) ? 0 : n - p2 - 1;
		int ret = n - p2;
		while (p1 < n && p2 < n) {
			while (p1 < n && p2 < n && v[p1] * 2 < v[p2])
				++p1;
			ret = min(ret, p1 + n - p2);
			while (p2 < n && p1 < n && v[p1] * 2 >= v[p2]) {
				++p2;
			}
			int a = (p2 == n) ? 0 : n - p2 - 1;
			ret = min(ret, p1 + n - p2);
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
