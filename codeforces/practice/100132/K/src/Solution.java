import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "vampire";

	ArrayList<Integer> get(int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		while (n > 0) {
			ret.add(n % 10);
			n /= 10;
		}
		Collections.sort(ret);
		return ret;
	}

	static class Pair {
		String a, b, c;

		public Pair(int a, int b, int c) {
			super();
			this.a = "" + a;
			this.b = "" + b;
			this.c = "" + c;
		}
	}

	ArrayList<Pair> solve4() {
		HashSet<Integer> was = new HashSet<Integer>();
		ArrayList<Pair> pairs = new ArrayList<Solution.Pair>();
		for (int i = 10; i < 100; ++i)
			for (int j = 10; j <= i; ++j) {
				int a = i * j;
				ArrayList<Integer> ret = get(a);
				ArrayList<Integer> origin = get(i);
				origin.addAll(get(j));
				Collections.sort(origin);
				if (ret.equals(origin) && !was.contains(a)) {
					pairs.add(new Pair(i, j, a));
					was.add(a);
				}
			}
		return pairs;
	}

	ArrayList<Pair> solve6() {
		HashSet<Integer> was = new HashSet<Integer>();
		ArrayList<Pair> pairs = new ArrayList<Solution.Pair>();
		for (int i = 100; i < 1000; ++i)
			for (int j = 100; j <= i; ++j) {
				int a = i * j;
				ArrayList<Integer> ret = get(a);
				ArrayList<Integer> origin = get(i);
				origin.addAll(get(j));
				Collections.sort(origin);
				if (ret.equals(origin) && !was.contains(a)) {
					pairs.add(new Pair(i, j, a));
					was.add(a);
				}
			}
		return pairs;
	}

	String addZeroes(String s, int cnt) {
		StringBuilder ret = new StringBuilder(s);
		for (int i = 0; i < cnt; ++i)
			ret.append('0');
		return ret.toString();
	}

	void solve() throws IOException {
		int k = ni();
		int n = ni();
		ArrayList<Pair> ret;
		if (n == 4)
			ret = solve4();
		else if (n == 6)
			ret = solve6();
		else {
			ret = solve6();
			int add = n - 6;
			for (Pair p : ret) {
				p.a = addZeroes(p.a, add >> 1);
				p.b = addZeroes(p.b, add >> 1);
				p.c = addZeroes(p.c, add);
			}
		}
		for (int i = 0; i < k; ++i) {
			Pair p = ret.get(i);
			out.println(p.c + "=" + p.a + "x" + p.b);
		}
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
