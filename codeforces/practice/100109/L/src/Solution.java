import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int n = ni();
		int t1 = ni();
		int t2 = ni();
		int total = 0;
		int s1 = t1, s2 = t2;
		int i;
		for (i = 0;; ++i) {
			if (s1 == i) {
				++total;
			}
			if (s2 == i) {
				++total;
			}
			if (total >= n)
				break;
			if (s1 == i && total < n)
				s1 = i + t1;
			if (s2 == i && total < n)
				s2 = i + t2;
		}
		if (s1 > i) {
			++total;
		}
		if (s2 > i)
			++total;
		i = max(i, s1);
		i = max(i, s2);
		out.println(total + " " + i);
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
