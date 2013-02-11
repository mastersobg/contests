import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	Random rnd = new Random(123456789123l);

	int[] v = new int[3];

	boolean check(int a, int b, int c) {
		v[0] = a;
		v[1] = b;
		v[2] = c;
		Arrays.sort(v);
		return v[1] - v[0] != v[2] - v[1];
	}

	void solve() throws IOException {
		int n = ni();
		int k = ni();
		if (k * 3 > n) {
			out.println(-1);
			return;
		}

		int cur = 1;
		boolean step = true;
		int[] v = new int[n + 10];
		boolean[] used = new boolean[n + 10];
		for (int i = 1; i <= k; ++i) {
			if (step) {
				v[cur] = i;
				v[cur + 1] = i;
				v[cur + 3] = i;
				used[cur] = used[cur + 1] = used[cur + 3] = true;
				cur += 2;
			} else {
				v[cur] = i;
				v[cur + 2] = i;
				v[cur + 3] = i;
				used[cur] = used[cur + 2] = used[cur + 3] = true;
				cur += 4;
			}
			step = !step;
		}
		for (; cur <= n; ++cur)
			if (used[cur] == false)
				v[cur] = 1;
		int qwe = n / 3;
		if (qwe * 3 == n && qwe == k && (qwe & 1) == 1) {
			v[1] = k;
		}
		for (int i = 1; i <= n; ++i)
			out.print(v[i] + " ");
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
