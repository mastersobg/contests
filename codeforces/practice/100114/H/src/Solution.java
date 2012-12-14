import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, m;
	int k;
	int[] v;
	short[][] precalc;

	void solve() throws IOException {
		long time = System.currentTimeMillis();
		n = ni();
		k = ni();
		m = 0;
		v = new int[n];
		for (int i = 0; i < n; ++i) {
			v[i] = ni() - 1;
			m = max(m, v[i]);
		}
		++m;
		precalc = new short[m][n + 1];
		for (int it = 0; it < m; ++it) {
			short[] arr = precalc[it];
			for (int i = 0; i < n; ++i)
				arr[i + 1] = (short) (arr[i] + (v[i] == it ? 1 : 0));
		}
		StringBuilder sb = new StringBuilder();
		for (int it = 0; it < k; ++it) {
			sb.append(calc(ni(), ni()));
			sb.append('\n');
		}
		out.println(sb.toString());
		System.err.println(System.currentTimeMillis() - time);
	}

	int calc(int l, int r) {
		int ret = 0;
		for (int color = 0; color < m; ++color) {
			short[] arr = precalc[color];
			if (arr[r] - arr[l - 1] > 0)
				++ret;
		}
		return ret;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		// generateTest();
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
		String s = ns();
		int ret = 0;
		for (int i = 0; i < s.length(); ++i) {
			ret = ret * 10 + (s.charAt(i) - '0');
		}
		return ret;
	}

	// int ni() throws IOException {
	// return Integer.valueOf(ns());
	// }

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		new Solution();
	}

	void generateTest() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test");
		int k = 100000;
		out.println("10000 " + k);
		int color = 1;
		for (int i = 0; i < 10000; ++i) {
			out.print(color + " ");
			++color;
			if (color > 255)
				color = 1;
		}
		Random rnd = new Random();
		for (int i = 0; i < k; ++i) {
			out.println("1 10000");
		}
		out.close();
	}
}
