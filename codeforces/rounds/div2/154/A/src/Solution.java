import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int n = ni();
		int m = ni();
		int p1 = 0, p2 = 0;
		boolean who = (n > m) ? true : false;
		StringBuilder ret = new StringBuilder();
		while (p1 < n && p2 < m) {
			if (who) {
				ret.append("B");
				++p1;
			} else {
				ret.append("G");
				++p2;
			}
			who = !who;
		}
		while (p1 < n) {
			ret.append("B");
			++p1;
		}
		while (p2 < m) {
			ret.append("G");
			++p2;
		}
		out.print(ret);
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
