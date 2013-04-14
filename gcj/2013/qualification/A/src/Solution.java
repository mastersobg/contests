import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	char[][] v;

	boolean check(int x, int y, int dx, int dy, char c) {
		for (int i = 0; i < 4; ++i) {
			if (v[x][y] == c || v[x][y] == 'T')
				;
			else
				return false;
			x += dx;
			y += dy;
		}
		return true;
	}

	boolean check(char c) {
		boolean ret = false;
		for (int i = 0; i < 4; ++i)
			ret |= check(i, 0, 0, 1, c);
		for (int i = 0; i < 4; ++i)
			ret |= check(0, i, 1, 0, c);
		ret |= check(0, 0, 1, 1, c);
		ret |= check(0, 3, 1, -1, c);
		return ret;
	}

	boolean hasEmpty() {
		for (int i = 0; i < 4; ++i)
			for (int j = 0; j < 4; ++j)
				if (v[i][j] == '.')
					return true;
		return false;
	}

	void solve() throws IOException {
		v = new char[4][];
		for (int i = 0; i < 4; ++i)
			v[i] = ns().toCharArray();
		if (check('X'))
			out.println("X won");
		else if (check('O'))
			out.println("O won");
		else if (hasEmpty())
			out.println("Game has not completed");
		else
			out.println("Draw");
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int test = ni();
		for (int t = 1; t <= test; ++t) {
			out.print("Case #" + t + ": ");
			solve();
		}
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
