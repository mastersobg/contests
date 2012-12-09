import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;

	int[] month = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	int convert(int M, int D) {
		int m = 1, d = 1;
		int ret = 0;
		while (m != M || d != D) {
			++d;
			if (d > month[m]) {
				d = 1;
				++m;
			}
			++ret;
		}
		return ret;
	}

	class Contest implements Comparable<Contest> {
		int day, people;
		boolean open;

		public Contest(int day, int people, boolean open) {
			this.day = day;
			this.people = people;
			this.open = open;
		}

		@Override
		public int compareTo(Contest o) {
			if (day != o.day)
				return day - o.day;
			return Boolean.valueOf(open).compareTo(o.open);
		}
	}

	Contest[] v;

	void solve() throws IOException {
		n = ni();
		v = new Contest[n * 2];
		for (int i = 0; i < n; ++i) {
			int day = convert(ni(), ni());
			int p = ni();
			int t = ni();
			v[i] = new Contest(day, p, false);
			v[n + i] = new Contest(day - t, p, true);
		}
		Arrays.sort(v);
		int free = 0;
		for (Contest c : v) {
			if (c.open) {
				free -= min(free, c.people);
			} else {
				free += c.people;
			}
		}
		out.println(free);
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
