import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int x = ni();
		int y = ni();

		String a = sget(1, x, 'E', 'W');
		String b = sget(a.length() + 1, y, 'N', 'S');

		String c = sget(1, y, 'N', 'S');
		String d = sget(c.length() + 1, x, 'E', 'W');

		if (a.length() + b.length() < c.length() + d.length())
			out.println(a + b);
		else
			out.println(c + d);
	}

	int get(int start, int pos) {
		int cur = 0;
		int total = 0;
		int min;
		if (pos >= 0) {
			while (cur + start <= pos) {
				cur += start;
				++start;
				++total;
			}
			min = min(pos - cur, cur + start - pos);
		} else {
			while (cur - start >= pos) {
				cur -= start;
				++start;
				++total;
			}
			min = min(abs(pos - cur), abs(cur - start - pos));
		}
		return total + min;
	}

	String sget(int start, int pos, char f, char r) {
		int cur = 0;
		int total = 0;
		int min;
		StringBuilder ret = new StringBuilder();
		if (pos >= 0) {
			while (cur + start <= pos) {
				cur += start;
				++start;
				++total;
				ret.append(f);
			}
			int a = pos - cur, b = cur + start - pos;
			if (a < b + 1) {
				for (int i = 0; i < a; ++i) {
					ret.append(r);
					ret.append(f);
				}
			} else {
				ret.append(f);
				for (int i = 0; i < b; ++i) {
					ret.append(f);
					ret.append(r);
				}
			}
		} else {
			while (cur - start >= pos) {
				cur -= start;
				++start;
				++total;
				ret.append(r);
			}
			int a = abs(pos - cur);
			int b = abs(cur - start - pos);
			if (a < b + 1) {
				for (int i = 0; i < a; ++i) {
					ret.append(f);
					ret.append(r);
				}
			} else {
				ret.append(r);
				for (int i = 0; i < b; ++i) {
					ret.append(r);
					ret.append(f);
				}
			}
		}
		return ret.toString();
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input-large.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
			solve();
		}
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
