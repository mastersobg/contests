import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int n = ni();
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] v = new int[5010];
		Arrays.fill(v, -1);
		for (int i = 0; i < 2 * n; ++i) {
			int a = ni();
			if (v[a] == -1) {
				v[a] = i + 1;
			} else {
				ret.add(new int[] { i + 1, v[a] });
				v[a] = -1;
			}
		}
		boolean all = true;
		for (int i = 0; i < v.length; ++i)
			if (v[i] != -1) {
				all = false;
				break;
			}
		if (all) {
			for (int[] a : ret)
				out.println(a[0] + " " + a[1]);
		} else {
			out.println(-1);
		}
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
