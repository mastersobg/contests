import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Program {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	long m;
	long[] t;
	long[] ts, te;

	void solve() throws IOException {
		n = ni();
		m = nl();
		t = new long[n];
		for (int i = 0; i < n; ++i) {
			t[i] = ni();
		}
		ts = new long[n];
		te = new long[n];
		ts[0] = 0;
		te[0] = m * t[0];
		for (int i = 1; i < n; ++i)
			if (t[i - 1] <= t[i]) {
				ts[i] = ts[i - 1] + t[i - 1];
				te[i] = ts[i] + m * t[i];
			} else {
				te[i] = te[i - 1] + t[i];
				ts[i] = te[i] - m * t[i];
			}
		for (int i = 0; i < n; ++i)
			out.println(te[i]);
	}

	public Program() throws IOException {
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
		new Program();
	}
}
