import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long count(int lines, int cur) {
		long ret = 0;
		for (int i = 2; i <= cur; ++i) {
			ret += i - 1;
		}
		return (cur + 1) * lines + 1 + ret;
	}

	void solve() throws IOException {
		int n = ni();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) {
			v[i] = ni();
		}
		BigInteger res = BigInteger.ONE;
		int lines = 0;
		for (int i = 0; i < n; ++i) {
			res = res.add(BigInteger.valueOf(count(lines, v[i])));
			lines += v[i] + 1;
		}
		out.println(res.toString());
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
			solve();
		}
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
