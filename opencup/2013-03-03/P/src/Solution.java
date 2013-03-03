import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int a = ni();
		int b = ni();
		int A = ni();
		int B = ni();
		boolean[] v = new boolean[B + 1];
		v[0] = true;
		for (int i = 0; i < v.length; ++i)
			if (v[i]) {
				if (i + a < v.length)
					v[i + a] = true;
				if (i + b < v.length)
					v[i + b] = true;
			}
		int ret = 0;
		for (int i = A; i <= B; ++i)
			if (v[i])
				++ret;
		out.println(ret);
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
