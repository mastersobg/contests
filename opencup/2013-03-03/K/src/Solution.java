import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long p, n;

	long pow(long a, long b) {
		long ret = 1;
		for (int i = 0; i < b; ++i) {
			ret *= a;
		}
		return ret;
	}

	void solve() throws IOException {
		p = nl();
		n = nl();
		if (p == 1) {
			out.println(0);
			return;
		}
		long p1 = 1, p2 = n - 1;
		long ret = 0;
		while (p1 <= p2) {
			long a = pow(p, p1 + 1) - pow(p, p1);
			long b = pow(p, p2 + 1) - pow(p, p2);
			ret += a * b;
			if (p1 == p2) {
				long cnt = a - 1;
				long an = 1 + cnt - 1;
				ret -= (1l + an) * cnt / 2l;
			}
			++p1;
			--p2;
		}
		long total = pow(p, n + 1) - pow(p, n);
		out.println(total - ret);
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
