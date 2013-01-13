import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "forest";

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

	int n, m;
	int[][] v, v1;
	Pair[][] prec;

	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	boolean can(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	boolean needUpdate(int[][] map, int x, int y) {
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (can(nx, ny)) {
				if (map[x][y] + 1 == map[nx][ny])
					return true;
			}
		}
		return false;
	}

	void solve() throws IOException {
		n = ni();
		m = ni();

		prec = new Pair[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				prec[i][j] = new Pair(i, j);

		v = new int[n][m];
		v1 = new int[n][m];
		HashSet<Pair> s1 = new HashSet<Solution.Pair>(), s2 = new HashSet<Solution.Pair>();
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				v[i][j] = ni();
				s1.add(prec[i][j]);
			}

		int days = -1;
		while (s1.size() > 0) {
			s2 = new HashSet<Solution.Pair>();
			days++;
			for (Pair p : s1) {
				int x = p.x;
				int y = p.y;
				if (needUpdate(v, x, y)) {
					v1[x][y] = v[x][y] + 1;
					s2.add(prec[x][y]);
					for (int i = 0; i < 4; ++i) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						if (can(nx, ny)) {
							s2.add(prec[nx][ny]);
						}
					}
				} else
					v1[x][y] = v[x][y];
			}
			s1 = s2;
			int[][] t = v;
			v = v1;
			v1 = t;
		}
		out.println(days);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j)
				out.print(v[i][j] + " ");
			out.println();
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
