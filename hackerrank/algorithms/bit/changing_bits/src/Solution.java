import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[] a, b, ret;

	void solve() throws IOException {
		int n = ni();
		int q = ni();
		a = read(n);
		b = read(n);
		ret = add(a, b);
		for (int it = 0; it < q; ++it) {
			String s = ns();
			if ("set_a".equals(s)) {
				int idx = ni();
				int bit = ni();
				set(a, b, idx, bit);
			}
			if ("set_b".equals(s)) {
				int idx = ni();
				int bit = ni();
				set(b, a, idx, bit);
			}
			if ("get_c".equals(s)) {
				int idx = ni();
				out.print(ret[idx]);
			}
		}
		out.println();
	}
	void set(int[] a, int[] b, int idx, int bit) {
		if (a[idx] == bit) {
			return;
		}
		if (bit == 0) {
			if (b[idx] == 0) {
				if (ret[idx] == 1) {
					ret[idx] = 0;
				} else {
					int cur = idx;
					while (ret[cur] == 0) {
						ret[cur] = 1;
						++cur;
					}
					ret[cur] = 0;
				}
			} else {
				int cur = idx;
				while (ret[cur] == 0) {
					ret[cur] = 1;
					++cur;
				}
				ret[cur] = 0;
			}
		} else {
			if (b[idx] == 0) {
				if (ret[idx] == 1) {
					int cur = idx;
					while (ret[cur] == 1) {
						ret[cur] = 0;
						++cur;
					}
					ret[cur] = 1;
				} else {
					ret[idx] = 1;
				}
			} else {
				int cur = idx;
				while (ret[cur] == 1) {
					ret[cur] = 0;
					++cur;
				}
				ret[cur] = 1;
			}
		}
		a[idx] = bit;
	}

	int[] read(int n) throws IOException {
		int[] ret = new int[n];
		String s = ns();
		for (int i = 0; i < s.length(); ++i)
			ret[i] = s.charAt(s.length() - i - 1) - '0';
		return ret;
	}

	int[] add(int[] a, int[] b) {
		int mx = max(a.length, b.length);
		int mn = min(a.length, b.length);
		int[] ret = new int[mx + 1];
		int carry = 0;
		for (int i = 0; i < mn; ++i) {
			carry += a[i] + b[i];
			ret[i] = carry % 2;
			carry /= 2;
		}
		for (int i = mn; i < mx; ++i) {
			carry += (mn == a.length ? b[i] : a[i]);
			ret[i] = carry % 2;
			carry /= 2;
		}
		if (carry != 0)
			ret[ret.length - 1] = carry;
		return ret;
	}

	boolean DEBUG = true;

	void dbg(Object... args) {
		if (!DEBUG)
			return;
		for (Object o : args)
			System.err.print(o + " ");
		System.err.println();
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		 in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	Random rnd = new Random(1234560L);

	void generate() throws IOException {
		PrintWriter out = new PrintWriter("test.txt");
		int n = 29;
		int q = 1000;
		out.println(n + " " + q);
		for (int i = 0; i < n; ++i) {
			out.print(rnd.nextBoolean() ? 1 : 0);
		}
		out.println();
		for (int i = 0; i < n; ++i) {
			out.print(rnd.nextBoolean() ? 1 : 0);
		}
		out.println();
		for (int i = 0; i < q; ++i) {
			out.print(rnd.nextBoolean() ? "set_a " : "set_b ");
			out.print(rnd.nextInt(n) + " " + (rnd.nextBoolean() ? 1 : 0));
			out.println();
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
		new Solution().run();
	}
}
