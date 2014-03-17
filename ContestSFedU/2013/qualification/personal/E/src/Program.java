import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class Program {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long w, h, n;

	long a, b;
	StringBuilder result = new StringBuilder();

	void solve() throws IOException {
		w = nl();
		h = nl();
		n = nl();
		long l = 1, r = min(w, h);
		while (l + 1 < r) {
			// long mid = (l + r) >> 1l;//
			// BigInteger.valueOf(l).add(BigInteger.valueOf(r))
			long mid = l + ((r - l) >> 1l);
			if (mid < 0 || (r - l) < 0)
				throw new RuntimeException();
			// .shiftRight(1).longValue();
			calc(mid);
			int cmp = compare();
			if (cmp < 0) {
				r = mid;
			} else
				l = mid;
		}
		calc(r);
		if (compare() < 0) {
			out.println(l);
		} else {
			out.println(r);
		}
	}

	long readLong() throws IOException {
		long result = 0;
		String s = ns();
		for (int i = 0; i < s.length(); ++i) {
			result = result * 10l + (s.charAt(i) - '0');
		}
		return result;
	}

	void calc(long mid) {
		a = w / mid;
		b = h / mid;
	}

	int compare() {
		long div = n / b;
		long mod = n % b;
		if (a < div)
			return -1;
		else if (a > div)
			return 1;
		else {
			if (mod == 0)
				return 0;
			else
				return -1;
		}
	}

	void gen() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test.txt");
		out.println(100000);
		for (int i = 0; i < 100000; ++i) {
			out.println("9223372036854775806 9223372036854775806 9223372036854775806");
		}
		out.close();
	}

	public Program() throws IOException {
		Locale.setDefault(Locale.US);
		InputStreamReader isr;
		in = new BufferedReader(new InputStreamReader(System.in));
		// in = new BufferedReader(new FileReader("test.txt"));
		out = new PrintWriter(System.out);
		// gen();
		long time = System.currentTimeMillis();
		int t = ni();
		for (int i = 0; i < t; ++i)
			solve();
		// out.println(result.toString());
		// out.println("time=" + (System.currentTimeMillis() - time));
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
		new Program();
	}
}
