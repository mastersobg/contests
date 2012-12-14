import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	int m;
	boolean[][] map;

	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	void solve() throws IOException {
		n = ni();
		m = ni();
		map = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			String s = ns();
			for (int j = 0; j < m; ++j) {
				map[i][j] = s.charAt(j) == '+';
			}
		}
		int ret = 0;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				if (map[i][j])
					if (check(i, j))
						++ret;
		out.println(ret);
	}

	boolean check(int x, int y) {
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (inside(nx, ny))
				if (!map[nx][ny])
					return true;
		}
		return false;
	}

	boolean inside(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
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
