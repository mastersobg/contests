import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[][] v = new int[40001][40001];
	int w, h, p, q, n, x, y, a, b, c, d;

	void solve() throws IOException {
		w = ni();
		h = ni();
		p = ni();
		q = ni();
		n = ni();
		x = ni();
		y = ni();
		a = ni();
		b = ni();
		c = ni();
		d = ni();
		int curx = x, cury = y;
		clear(h, w);
		put(curx, cury);
		for (int i = 1; i < n; ++i) {
			int nx = (curx * a + cury * b + 1) % w;
			int ny = (curx * c + cury * d + 1) % h;
			put(nx, ny);
			curx = nx;
			cury = ny;
		}
		for (int i = 2; i <= w; ++i)
			v[1][i] += v[1][i - 1];
		for (int i = 2; i <= h; ++i)
			v[i][1] += v[i - 1][1];
		for (int i = 2; i <= h; ++i)
			for (int j = 2; j <= w; ++j)
				v[i][j] = v[i - 1][j] + v[i][j - 1] - v[i - 1][j - 1] + v[i][j];
		int ret = 0;
		for (int i = q; i <= h; ++i)
			for (int j = p; j <= w; ++j)
				if (count(i, j) == 0)
					++ret;
		out.println(ret);
	}

	int count(int x, int y) {
		return v[x][y] - v[x - q][y] - v[x][y - p] + v[x - q][y - p];
	}

	void put(int x, int y) {
		v[y + 1][x + 1] = 1;
	}

	void clear(int n, int m) {
		for (int i = 0; i <= n; ++i)
			for (int j = 0; j <= m; ++j)
				v[i][j] = 0;
	}

	public Solution() throws IOException {
		long time = System.currentTimeMillis();
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
			solve();
		}
		in.close();
		out.close();
		System.out.println(System.currentTimeMillis() - time);
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
