import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, m;
	byte[][] v;

	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	boolean checkIn(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	boolean isFree(int x, int y) {
		if (!checkIn(x, y))
			return false;
		return v[x][y] == 1;
	}

	int getId(int x, int y) {
		return x * m + y;
	}

	// ArrayList<Integer>[] g;

	int ret = 0;

	int dfs(char x, char y, char px, char py) {
		// List<Integer> lst = new ArrayList<Integer>();
		int mx1 = 0, mx2 = 0;
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx == px && ny == py)
				continue;
			if (isFree(nx, ny)) {
				int a = dfs((char) nx, (char) ny, x, y);
				if (a > mx1) {
					mx2 = mx1;
					mx1 = a;
				} else if (a > mx2) {
					mx2 = a;
				}
			}
		}
		ret = max(ret, mx1 + mx2);
		return mx1 + 1;
	}

	void solve() throws IOException {
		m = ni();
		n = ni();
		v = new byte[n][m];
		int x = -1, y = -1;
		for (int i = 0; i < n; ++i) {
			String s = ns();
			for (int j = 0; j < m; ++j) {
				v[i][j] = s.charAt(j) == '#' ? (byte) 0 : (byte) 1;
				if (x == -1 && v[i][j] == 1) {
					x = i;
					y = j;
				}
			}
		}
		dfs((char) x, (char) y, (char) -1, (char) -1);
		out.println(ret);
	}

	void gen() throws FileNotFoundException {
		int n = 820;
		PrintWriter out = new PrintWriter("test.txt");
		out.println(n + " " + n);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				out.print(".");
			}
			out.println();
		}
		out.close();
	}

	public Solution() throws IOException {
		// gen();
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		// in = new BufferedReader(new FileReader("test.txt"));
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
