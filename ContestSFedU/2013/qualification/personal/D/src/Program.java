import java.io.*;
import java.util.*;

import javax.management.RuntimeErrorException;

import static java.lang.Math.*;

public class Program {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	Timer timer = new Timer();
	int n, m;
	int[][] v;
	int k;
	int[] x, y;
	int[][][] dp;
	int[][] prec;

	int rec(int xPos, int yPos, int step) {
		if (step == k)
			return 0;
		int ret = dp[step][xPos][yPos];
		if (ret == -1) {
			int n = x[step];
			int m = y[step];
			int nNext = x[step + 1];
			int mNext = y[step + 1];
			ret = Integer.MIN_VALUE;
			int xBoard = xPos + n - nNext;
			int yBoard = yPos + m - mNext;
			for (int i = xPos; i <= xBoard; ++i) {
				for (int j = yPos; j <= yBoard; ++j) {
					int sum = sum(i, j, i + nNext, j + mNext);
					if ((step & 1) == 1) {
						sum = -sum;
					}
					ret = max(ret, rec(i, j, step + 1) + sum);
				}
			}
			dp[step][xPos][yPos] = ret;
		}
		return ret;
	}

	int dp() {
		dp = new int[k + 1][n][m];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				Arrays.fill(dp[i][j], Integer.MIN_VALUE);
			}
		}
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[0][i], 0);
		}
		for (int step = 0; step < k; ++step) {
			int n = x[step];
			int m = y[step];
			int nNext = x[step + 1];
			int mNext = y[step + 1];
			for (int xPos = 0; xPos <= this.n - n; ++xPos) {
				for (int yPos = 0; yPos <= this.m - m; ++yPos) {
					if (dp[step][xPos][yPos] == Integer.MIN_VALUE)
						continue;
					int xBoard = xPos + n - nNext;
					int yBoard = yPos + m - mNext;
					for (int i = xPos; i <= xBoard; ++i) {
						for (int j = yPos; j <= yBoard; ++j) {
							int sum = sum(i, j, i + nNext, j + mNext);
							if ((step & 1) == 1) {
								sum = -sum;
							}
							dp[step + 1][i][j] = max(dp[step + 1][i][j],
									dp[step][xPos][yPos] + sum);
						}
					}
				}
			}
		}
		int ret = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				ret = max(ret, dp[k][i][j]);
		return ret;
	}

	int sum(int x, int y, int n, int m) {
		return prec[n][m] - prec[x][m] - prec[n][y] + prec[x][y];
	}

	void solve() throws IOException {
		timer.ts();
		read();
		dp = new int[k][n][m];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		prec = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				prec[i][j] = prec[i - 1][j] + prec[i][j - 1]
						- prec[i - 1][j - 1] + v[i - 1][j - 1];
			}
		}
		 out.println(rec(0, 0, 0));
		// if (rec(0, 0, 0) != dp())
		// throw new RuntimeException();
//		out.println(dp());
		timer.print();
	}

	void read() throws IOException {
		n = ni();
		m = ni();
		v = new int[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				v[i][j] = ni();
			}
		}
		k = ni();
		x = new int[k + 1];
		y = new int[k + 1];
		x[0] = n;
		y[0] = m;
		for (int i = 1; i <= k; ++i) {
			x[i] = ni();
			y[i] = ni();
		}
	}

	void gen() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test.txt");
		int n = 1000;
		int m = 1000;
		out.println(n + " " + m);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				out.print("100 ");
			}
			out.println();
		}
		out.println(10);
		for (int i = 0; i < 10; ++i) {
			out.print(n - i * 100 - 1);
			out.print(" ");
			out.println(n - i * 100 - 1);
		}
		out.close();
	}

	public Program() throws IOException {
		Locale.setDefault(Locale.US);
		// in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new FileReader("test.txt"));
		out = new PrintWriter(System.out);
		// gen();
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
		new Program();
	}

	static class Timer {
		long start;

		void ts() {
			start = System.currentTimeMillis();
		}

		long te() {
			return System.currentTimeMillis() - start;
		}

		void print() {
			System.out.println("time=" + te());
		}
	}
}
