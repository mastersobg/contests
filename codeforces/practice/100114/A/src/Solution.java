import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	String s;

	String map(String s, char last) {
		StringBuilder ret = new StringBuilder();
		if (last == 'B') {
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c == 'A')
					ret.append('C');
				else if (c == 'C')
					ret.append('A');
				else
					ret.append('B');
			}
		} else {
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c == 'A')
					ret.append('A');
				else if (c == 'B')
					ret.append('C');
				else
					ret.append('B');
			}
		}
		return ret.toString();
	}

	boolean rec(String s) {
		if (s.length() == 0)
			return true;
		char c = s.charAt(s.length() - 1);
		if (c == 'C')
			return false;
		String next = map(s, c);
		return rec(next.substring(0, next.length() - 1));
	}

	void solve() throws IOException {
		int n = ni();
		String s = ns();
		out.println(rec(s) ? "YES" : "NO");
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
