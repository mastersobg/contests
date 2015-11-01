package com.ivangorbachev;

import static java.lang.Math.*;

public class CoinsGameEasy {

	boolean[][] map;
	int n, m;
	final int INF = 1 << 29;
	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	boolean checkInside(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	boolean canMove(int x, int y) {
		if (!checkInside(x, y))
			return true;
		return !map[x][y];
	}

	int rec(int x1, int y1, int x2, int y2, int depth) {
		boolean ci1 = checkInside(x1, y1);
		boolean ci2 = checkInside(x2, y2);
		if (ci1 != ci2)
			return depth;
		if (ci1 == false)
			return INF;
		if (depth == 10)
			return INF;
		int ret = INF;
		for (int i = 0; i < 4; ++i) {
			int nx1 = x1 + dx[i];
			int ny1 = y1 + dy[i];
			int nx2 = x2 + dx[i];
			int ny2 = y2 + dy[i];
			int mx1 = x1;
			int my1 = y1;
			int mx2 = x2;
			int my2 = y2;

			if (canMove(nx1, ny1)) {
				mx1 = nx1;
				my1 = ny1;
			}
			if (canMove(nx2, ny2)) {
				mx2 = nx2;
				my2 = ny2;
			}
			ret = min(ret, rec(mx1, my1, mx2, my2, depth + 1));
		}
		return ret;
	}

	public int minimalSteps(String[] board) {
		map = new boolean[board.length][board[0].length()];
		int x1 = -1, y1 = -1, x2 = -1, y2 = 1;
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {
				map[i][j] = board[i].charAt(j) == '#';
				if (board[i].charAt(j) == 'o') {
					if (x1 == -1) {
						x1 = i;
						y1 = j;
					} else {
						x2 = i;
						y2 = j;
					}
				}
			}
		}
		n = map.length;
		m = map[0].length;
		int ret = rec(x1, y1, x2, y2, 0);
		return ret == INF ? -1 : ret;
	}

	// BEGIN CUT HERE
	public static void main(String[] args) {
		try {
			eq(3,
					(new CoinsGameEasy()).minimalSteps(new String[] { "###",
							".o.", "###", ".o.", "###" }), -1);

			eq(0, (new CoinsGameEasy()).minimalSteps(new String[] { "oo" }), 1);
			eq(1,
					(new CoinsGameEasy()).minimalSteps(new String[] { ".#",
							".#", ".#", "o#", "o#", "##" }), 4);
			eq(2,
					(new CoinsGameEasy()).minimalSteps(new String[] {"",
							"", "..", "o#", "o#", "##" }), 3);

			eq(4,
					(new CoinsGameEasy()).minimalSteps(new String[] { "###",
							".o.", "#.#", ".o.", "###" }), 3);
			eq(5,
					(new CoinsGameEasy()).minimalSteps(new String[] {
							"###########", "........#o#", "###########",
							".........o#", "###########" }), 10);
			eq(6,
					(new CoinsGameEasy()).minimalSteps(new String[] {
							"############", ".........#o#", "############",
							"..........o#", "############" }), -1);
		} catch (Exception exx) {
			System.err.println(exx);
			exx.printStackTrace(System.err);
		}
	}

	private static void eq(int n, int a, int b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected " + b
					+ ", received " + a + ".");
	}

	private static void eq(int n, char a, char b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected '" + b
					+ "', received '" + a + "'.");
	}

	private static void eq(int n, long a, long b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected \"" + b
					+ "L, received " + a + "L.");
	}

	private static void eq(int n, boolean a, boolean b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected " + b
					+ ", received " + a + ".");
	}

	private static void eq(int n, double a, double b) {
		if (eq(a, b))
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected " + b
					+ ", received " + a + ".");
	}

	private static void eq(int n, String a, String b) {
		if (a != null && a.equals(b))
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected \"" + b
					+ "\", received \"" + a + "\".");
	}

	private static void eq(int n, int[] a, int[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i]) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void eq(int n, long[] a, long[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i]) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void eq(int n, double[] a, double[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (!eq(a[i], b[i])) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void eq(int n, String[] a, String[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (!a[i].equals(b[i])) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void print(int a) {
		System.err.print(a + " ");
	}

	private static void print(long a) {
		System.err.print(a + "L ");
	}

	private static void print(String s) {
		System.err.print("\"" + s + "\" ");
	}

	private static void print(int[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print(rs[i]);
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void print(long[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print(rs[i]);
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void print(double[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print(rs[i]);
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void print(String[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print("\"" + rs[i] + "\"");
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void nl() {
		System.err.println();
	}

	private static double eps = 1e-9;

	private static boolean eq(double a, double b) {
		return abs(a - b) <= eps;
	}
	// END CUT HERE
}
