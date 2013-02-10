import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long n, k;
	long p;

	boolean ok(long cur, long m) {
		long left = k * 100l * m;
		long right = (cur + m * k) * p;
		return left >= right;
	}

	long getNext(long cur) {
		long l = 1;
		long r = (n - cur) / k + 2;
		while (l + 1 < r) {
			long m = (l + r) / 2l;
			if (ok(cur, m))
				r = m;
			else
				l = m;
		}
		long ret = -1;
		if (ok(cur, l))
			ret = l;
		else if (ok(cur, r))
			ret = r;
		else
			throw new RuntimeException();
		return ret * k + cur;
	}

	void solve() throws IOException {
		n = nl();
		k = nl();
		p = nl();
		long start = n % k;
		while (true) {
			try {
				long next = getNext(start);
				if (next > n) {
					break;
				}
				// System.out.println(next);
				start = next;
			} catch (RuntimeException e) {
				break;
			}
		}
		long ret = (n - start) / k + 1;
		out.println(ret);
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		gen();
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
			solve();
		}
		in.close();
		out.close();
	}

	void gen() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test");
		for (int i = 190; i < 200; ++i)
			for (int j = 185; j <= i; ++j)
				for (int k = 1; k <= 100; ++k) {
					out.println(i + " " + j + " " + k);
				}
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
