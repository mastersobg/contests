import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[] real;
	int[] req;

	void solve() throws IOException {
		int n = ni();
		int a = ni();
		int b = ni();
		if (a + b > n) {
			out.println(-1);
			return;
		}
		char[] s = ns().toCharArray();
		int zero = 0, one = 0;
		for (int i = 0; i < s.length; ++i) {
			if (s[i] == '0')
				++zero;
			if (s[i] == '1')
				++one;
		}
		if (zero == a && one == b) {
			out.println(0);
			out.println(new String(s));
			return;
		}
		int two = n - zero - one;
		real = new int[] { zero, one, two };
		req = new int[] { a, b, n - a - b };
		int total = 0;
		for (int i = 0; i < s.length; ++i) {
			int pos = s[i] - '0';
			if (real[pos] > req[pos]) {
				int ch = less();
				s[i] = (char) (ch + '0');
				real[pos]--;
				real[ch]++;
				++total;
			}
		}
		out.println(total);
		out.println(new String(s));
	}

	int less() {
		for (int i = 0; i < 3; ++i)
			if (real[i] < req[i])
				return i;
		return -1;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
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
