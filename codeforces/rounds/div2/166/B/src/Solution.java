import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	TreeSet<Integer> sieve() {
		boolean[] prime = new boolean[500000];
		prime[0] = prime[1] = true;
		for (int i = 2; i < prime.length; ++i)
			if (prime[i] == false) {
				for (int j = i + i; j < prime.length; j += i)
					prime[j] = true;
			}
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < prime.length; ++i) {
			if (prime[i] == false)
				set.add(i);
		}
		return set;
	}

	void solve() throws IOException {
		TreeSet<Integer> set = sieve();
		int n = ni();
		int m = ni();
		int[][] v = new int[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				int a = ni();
				int b = set.ceiling(a);
				v[i][j] = b - a;
			}
		int ret = 1 << 30;
		for (int i = 0; i < n; ++i) {
			int a = 0;
			for (int j = 0; j < m; ++j)
				a += v[i][j];
			ret = min(ret, a);
		}

		for (int j = 0; j < m; ++j) {
			int a = 0;
			for (int i = 0; i < n; ++i)
				a += v[i][j];
			ret = min(ret, a);
		}
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
