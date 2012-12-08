import java.io.*;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, m, k;
	byte[][] map;
	int[][] sum;
	int[][][] mask;

	void solve() throws IOException {
		long start = System.currentTimeMillis();
		n = ni();
		m = ni();
		k = ni();
		map = new byte[n][m];
		for (int i = 0; i < n; ++i) {
			String s = ns();
			for (int j = 0; j < m; ++j) {
				byte ch = (byte) (s.charAt(j) - 'a');
				map[i][j] = ch;
			}
		}
		precalcSum();
		createMask();
		long ret = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				for (int k = j + 1; k < m; ++k) {
					if (map[i][j] == map[i][k]) {
						// if (i == 31 && j == 0) {
						// int qwe = 0;
						// ++qwe;
						// }
						int pos = findLast(i, j, k);
						if (pos > i) {
							int cnt = countEq(j, k, pos, map[i][j]);
							cnt -= countEq(j, k, i, map[i][j]);
							// int dummy = checkDummy(i, pos, j, k);
							// if (cnt != dummy) {
							// System.err.println(i + " " + j + " " + k);
							// }
							ret += cnt;
						}
					}
				}
			}
		}
		out.println(ret);
		// int check = check();
		// if (ret != check) {
		// System.err.println("Fuck");
		// System.err.println("Expceted = " + check + " actual = " + ret);
		// }
		System.err.println(System.currentTimeMillis() - start);
	}

	int checkDummy(int r1, int r2, int c1, int c2) {
		int ret = 0;
		for (int i = r1 + 1; i <= r2; ++i)
			if (map[i][c1] == map[r1][c1] && map[i][c2] == map[r1][c1]) {
				ret += (countCheck(r1, c1, i, c2) <= k) ? 1 : 0;
			}
		return ret;
	}

	int check() {
		out.println("---------");
		int ret = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				for (int k = i + 1; k < n; ++k) {
					for (int l = j + 1; l < m; ++l) {
						if (map[i][j] == map[i][l] && map[i][j] == map[k][j]
								&& map[i][j] == map[k][l]) {
							int a = countCheck(i, j, k, l);
							ret += (a <= this.k) ? 1 : 0;
							if (a <= this.k)
								out.println(i + " " + j + " " + k + " " + l);
						}
					}
				}
			}
		}
		return ret;
	}

	int countCheck(int x1, int c1, int x2, int c2) {
		int ret = 0;
		for (int i = x1; i <= x2; ++i)
			for (int j = c1; j <= c2; ++j)
				if (map[i][j] == 0)
					++ret;
		return ret;
	}

	int countEq(int c1, int c2, int r, int let) {
		int ret = 0;
		int b = r / 32;
		for (int i = 0; i < b; ++i) {
			int m = mask[let][c1][i] & mask[let][c2][i];
			ret += Integer.bitCount(m);
		}
		int mm;
		int mod = r % 32;
		if (mod == 31)
			mm = -1;
		else
			mm = (1 << (mod + 1)) - 1;
		int aa = mask[let][c1][b] & mm;
		int bb = mask[let][c2][b] & mm;
		ret += Integer.bitCount(aa & bb);
		return ret;
	}

	int findLast(int x1, int c1, int c2) {
		int l = x1, r = n - 1;
		int precalc = 0;
		if (x1 > 0)
			precalc += sum[x1 - 1][c2];
		if (x1 > 0 && c1 > 0)
			precalc -= sum[x1 - 1][c1 - 1];
		while (l + 1 < r) {
			int mid = (l + r) >> 1;
			int cnt = count(x1, c1, mid, c2) - precalc;
			if (cnt > k)
				r = mid;
			else
				l = mid;
		}
		if (count(x1, c1, r, c2) - precalc <= k)
			return r;
		return l;
	}

	int count(int x1, int c1, int mid, int c2) {
		int cnt = sum[mid][c2];
		if (c1 > 0)
			cnt -= sum[mid][c1 - 1];
		return cnt;
	}

	void precalcSum() {
		sum = new int[n][m];
		sum[0][0] = (map[0][0] == 0) ? 1 : 0;
		for (int i = 1; i < n; ++i) {
			sum[i][0] = sum[i - 1][0] + (map[i][0] == 0 ? 1 : 0);
		}
		for (int j = 1; j < m; ++j) {
			sum[0][j] = sum[0][j - 1] + (map[0][j] == 0 ? 1 : 0);
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < m; ++j) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1]
						+ (map[i][j] == 0 ? 1 : 0) - sum[i - 1][j - 1];
			}
		}
	}

	void createMask() {
		mask = new int[27][m][15];
		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				int pos = i / 32;
				int bit = i % 32;
				mask[map[i][j]][j][pos] |= 1 << bit;
			}
		}
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		// in = new BufferedReader(new FileReader("input.txt"));
		// out = new PrintWriter("output.txt");
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		// generate();
		solve();
		in.close();
		out.close();
	}

	void generate() throws FileNotFoundException {
		Random rnd = new Random();
		int n = rnd.nextInt(101) + 1;
		int m = rnd.nextInt(101) + 1;
		int k = rnd.nextInt(n * m + 1);
		PrintWriter out = new PrintWriter("input.txt");
		out.println(n + " " + m + " " + k);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				char c = (char) (rnd.nextInt(27) + 'a');
				out.print(c);
			}
			out.println();
		}
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
