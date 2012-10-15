import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	String filename = "phi";

	final int MAX = (int) (2 * 1e+6);

	boolean[] sieve(int n) {
		boolean[] ret = new boolean[n];
		ret[0] = ret[1] = true;
		for (int i = 0; i < n; ++i)
			if (ret[i] == false)
				for (int j = i + i; j < n; j += i)
					ret[j] = true;
		return ret;
	}

	void solve() throws IOException {
		long a = nl();
		long b = nl();
		boolean[] p = sieve(MAX);
		int diff = (int) (b - a + 1);
		long[] value = new long[diff], phi = new long[diff];
		for (int i = 0; i < diff; ++i)
			value[i] = phi[i] = a + i;
		for (int i = 2; i < p.length; ++i) {
			if (!p[i]) {
				// The first number after A which is divisible by i
				long start = a + (i - a % i) % i;
				for (; start <= b; start += i) {
					int idx = (int) (start - a);
					while (value[idx] % i == 0)
						value[idx] /= i;
					phi[idx] -= phi[idx] / i;
				}
			}
		}
		for (int i = 0; i < value.length; ++i)
			if (value[i] > 1) {
				phi[i] -= phi[i] / value[i];
			}
		long ret = 0;
		for (int i = 0; i < phi.length; ++i)
			ret += phi[i];
		out.println(ret);
	}

	long timer = 0;

	void ts() {
		timer = System.currentTimeMillis();
	}

	long gs() {
		return System.currentTimeMillis() - timer;
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
