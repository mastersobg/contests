import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "blot";

	int n, m;
	boolean[][] map;

	int[] qx = new int[1000010];
	int[] qy = new int[1000010];
	int b, e;
	boolean[][] was;

	void push(int x, int y) {
		qx[e] = x;
		qy[e++] = y;
	}

	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	boolean inside(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	int bfs(int x, int y) {
		b = e = 0;
		int ret = 0;
		was[x][y] = true;
		for (push(x, y); b < e; ++b) {
			x = qx[b];
			y = qy[b];
			++ret;
			for (int i = 0; i < 4; ++i) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (inside(nx, ny) && map[nx][ny]) {
					if (!was[nx][ny]) {
						was[nx][ny] = true;
						push(nx, ny);
					}
				}
			}
		}
		return ret;
	}

	void print(boolean[][] was) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j)
				System.err.print(was[i][j] ? 1 : 0);
			System.err.println();
		}
		System.err.println("----------");
	}

	void solve() throws IOException {
		n = ni();
		m = ni();
		map = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			String s = ns();
			for (int j = 0; j < s.length(); ++j)
				map[i][j] = s.charAt(j) == '1';
		}
		was = new boolean[n][m];
		int total = 0;
		int max = 0;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				if (map[i][j] && !was[i][j]) {
					int a = bfs(i, j);
					// print(was);
					++total;
					max = max(max, a);
				}
			}
		out.println(total + " " + max);
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
