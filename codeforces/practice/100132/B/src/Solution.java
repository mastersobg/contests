import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "chaotic";

	void solve() throws IOException {
		// int n = 10;
		// brute(0, new int[n], new boolean[n]);
		int n = ni();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i)
			v[i] = ni();
		solve(v);
		out.println(result.size());
		for (int a : result)
			out.print(a + " ");
	}

	void brute(int n, int[] arr, boolean[] used) {
		if (n == arr.length) {
			int[] ret = solve(arr);
			if (!checkArray(ret)) {
				System.out.println(Arrays.toString(arr));
				System.out.println(Arrays.toString(ret));
				System.exit(0);
			}
			return;
		}
		for (int i = 0; i < arr.length; ++i)
			if (!used[i]) {
				used[i] = true;
				arr[n] = i;
				brute(n + 1, arr, used);
				used[i] = false;
			}
	}

	ArrayList<Integer> result = new ArrayList<Integer>();

	int[] solve(int[] V) {
		result = new ArrayList<Integer>();
		int[] v = V.clone();
		for (int i = 0; i < v.length; ++i) {
			if (!check(v, i)) {
				swap(v, i + 1, i + 2);
				result.add(i + 2);
			}
		}
		return v;
	}

	boolean checkArray(int[] v) {
		for (int i = 0; i < v.length; ++i)
			if (!check(v, i))
				return false;
		return true;
	}

	void swap(int[] v, int i, int j) {
		int t = v[i];
		v[i] = v[j];
		v[j] = t;
	}

	boolean check(int[] v, int i) {
		if (i + 2 >= v.length)
			return true;
		if (v[i] > v[i + 1] && v[i + 1] > v[i + 2])
			return false;
		if (v[i] < v[i + 1] && v[i + 1] < v[i + 2])
			return false;
		return true;
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
