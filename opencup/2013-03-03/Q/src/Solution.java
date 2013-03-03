import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int a, b;

	int[] v;

	boolean[] square;
	boolean[] used;

	boolean check(int value) {
		return square[value];
	}

	void rec(int pos) {
		if (pos == v.length) {
			throw new RuntimeException();
		}
		if (v[pos] != -1)
			rec(pos + 1);
		for (int i = a; i <= b; ++i)
			if (!used[i]) {
				int next = i - a;
				used[i] = true;
				v[pos] = i;
				if ((v[next] == -1 || check(i + v[next]))
						&& (check(i + a + pos))) {
					rec(pos + 1);
				}
				v[pos] = -1;
				used[i] = false;
			}
	}

	void solve() throws IOException {
		a = ni();
		b = ni();
		v = new int[b - a + 1];
		used = new boolean[b + 1];
		square = new boolean[1000000];
		for (int i = 0; i < 1000; ++i)
			square[i * i] = true;
		Arrays.fill(v, -1);
		try {
			rec(0);
		} catch (RuntimeException e) {
			for (int i = 0; i < v.length; ++i) {
				out.print(v[i] + " ");
			}
			return;
		}
		out.println(-1);
	}

	public Solution() throws IOException {
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
		new Solution();
	}
}
