package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class EllysFigurines {

	int n, m;

	int getRealMask(int mask, int size, int a) {
		int ret = mask;
		for (int i = 0; i < size; ++i) {
			if (is(mask, i)) {
				for (int j = i, k = 0; k < a && j < size; ++k, ++j)
					ret = set(ret, j);
			}
		}
		return ret;
	}

	boolean is(int mask, int bit) {
		return (mask & (1 << bit)) != 0;
	}

	int set(int mask, int bit) {
		return mask | (1 << bit);
	}

	int[] hash;

	public int getMoves(String[] v, int R, int C) {
		n = v.length;
		m = v[0].length();

		boolean[][] map = new boolean[n][m];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = v[i].charAt(j) == 'X';
			}
		}

		int board = 1 << m;
		hash = new int[board];
		// long time = System.currentTimeMillis();
		int[] was = new int[board];
		Arrays.fill(was, 1 << 29);
		Arrays.fill(hash, Integer.MAX_VALUE);
		for (int mask = 0; mask < board; ++mask) {
			int rmask = getRealMask(mask, m, C);
			int value = Integer.bitCount(mask);
			if (was[rmask] > value) {
				for (int s = rmask;; s = (s - 1) & rmask) {
					hash[s] = min(hash[s], value);
					if (s == 0)
						break;
				}
				was[rmask] = value;
			}
		}
		// System.out.println(System.currentTimeMillis() - time);
		board = 1 << n;
		int result = 1 << 29;
		// time = System.currentTimeMillis();
		for (int mask = 0; mask < board; ++mask) {
			int rmask = getRealMask(mask, n, R);
			rmask = getMask(map, rmask);
			result = min(Integer.bitCount(mask) + hash[rmask], result);
		}
		// System.out.println(System.currentTimeMillis() - time);
		return result;
	}

	int getMask(boolean[][] v, int mask) {
		int ret = 0;
		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				if (v[i][j] && !is(mask, i)) {
					ret = set(ret, j);
				}
			}
		}
		return ret;
	}

	// BEGIN CUT HERE
	public static void main(String[] args) {
		try {
			eq(0,
					(new EllysFigurines()).getMoves(new String[] { ".X.X.",
							"XX..X", ".XXX.", "...X.", ".X.XX" }, 1, 2), 3);
			eq(1,
					(new EllysFigurines()).getMoves(new String[] { ".X.X.",
							"XX..X", ".X.X.", "...X.", ".X.XX" }, 2, 2), 2);
			eq(2, (new EllysFigurines()).getMoves(new String[] { "XXXXXXX" },
					2, 3), 1);
			eq(3,
					(new EllysFigurines()).getMoves(new String[] { "XXXXX",
							"X....", "XXX..", "X....", "XXXXX" }, 1, 1), 4);
			eq(4,
					(new EllysFigurines()).getMoves(new String[] {
							"XXX..XXX..XXX.", ".X..X....X...X",
							".X..X....X...X", ".X..X....X...X",
							".X...XXX..XXX.", "..............",
							"...XX...XXX...", "....X......X..",
							"....X....XXX..", "....X......X..",
							"...XXX..XXX..." }, 1, 2), 7);
			eq(5,
					(new EllysFigurines()).getMoves(new String[] {
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX",
							"XXXXXXXXXXXXXXX" }, 15, 15), 1);
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
