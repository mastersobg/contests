import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "roman";

	char[] chrs = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };
	int[] values = { 1000, 500, 100, 50, 10, 5, 1 };
	int[] div = { 1000, 100, 100, 10, 10, 1, 1 };

	static HashMap<Integer, String> predefined;
	static {
		predefined = new HashMap<Integer, String>();
		predefined.put(4, "IV");
		predefined.put(9, "IX");
		predefined.put(40, "XL");
		predefined.put(90, "XC");
		predefined.put(400, "CD");
		predefined.put(900, "CM");
	}

	int getPure(int n, int div) {
		return n - n % div;
	}

	String convert(int n) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < chrs.length; ++i) {
			int a = getPure(n, div[i]);
			if (predefined.containsKey(a)) {
				ret.append(predefined.get(a));
				n -= a;
			} else {
				int cnt = n / values[i];
				for (int j = 0; j < cnt; ++j)
					ret.append(chrs[i]);
				n %= values[i];
			}
		}
		return ret.toString();
	}

	int convertYear(String s) {
		String mod = s.substring(s.length() - 2, s.length());
		int year = Integer.valueOf(s.substring(0, s.length() - 2));
		int ret = 0;
		if (mod.equals("BC")) {
			for (int i = 753; i >= year; --i, ++ret)
				;
		} else {
			for (int i = 753; i >= 1; --i, ++ret)
				;
			for (int i = 1; i <= year; ++i, ++ret)
				;
		}
		return ret;
	}

	void solve() throws IOException {
		String[] v = ns().split("-");
		int y1 = convertYear(v[0]);
		int y2 = convertYear(v[1]);
		int mx = 0;
		for (int i = y1; i <= y2; ++i)
			mx = max(mx, convert(i).length());
		out.println(mx);
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
