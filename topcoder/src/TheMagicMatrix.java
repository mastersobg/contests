import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class TheMagicMatrix {

	boolean check(int[][] arr) {
		int[] rows = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				rows[i] += arr[i][j];
			}
		}
		int[] cols = new int[arr.length];
		for (int j = 0; j < arr.length; ++j) {
			for (int i = 0; i < arr.length; ++i) {
				cols[j] += arr[i][j];
			}
		}
		int a = rows[0];
		for (int i = 0; i < rows.length; ++i)
			if (rows[i] != a)
				return false;
		for (int i = 0; i < cols.length; ++i)
			if (cols[i] != a)
				return false;
		return true;
	}

	void rec(int[][] arr, int x, int y) {
		if (x == arr.length) {
			if (check(arr)) {
				check(arr);
				print(arr);
			}
			return;
		}
		if (y == arr.length) {
			rec(arr, x + 1, 0);
			return;
		}

		for (int i = 0; i < 10; ++i) {
			arr[x][y] = i;
			rec(arr, x, y + 1);
		}
	}

	void print(int[][] arr) {
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public int find(int n, int[] rows, int[] columns, int[] values) {
		rec(new int[3][3], 0, 0);
		return 0;
	}

	// BEGIN CUT HERE
	public static void main(String[] args) {
		try {
			eq(0, (new TheMagicMatrix()).find(2, new int[] { 0, 1 }, new int[] {
					1, 0 }, new int[] { 4, 4 }), 10);
			eq(1, (new TheMagicMatrix()).find(2, new int[] { 0, 1 }, new int[] {
					1, 0 }, new int[] { 4, 7 }), 0);
			eq(2, (new TheMagicMatrix()).find(4, new int[] { 0, 0, 0, 1, 2, 2,
					2, 3, 3, 3 }, new int[] { 0, 1, 2, 3, 0, 1, 2, 0, 1, 2 },
					new int[] { 3, 5, 1, 9, 5, 1, 8, 6, 7, 1 }), 2);
			eq(3, (new TheMagicMatrix()).find(474, new int[] { 44, 77 },
					new int[] { 47, 74 }, new int[] { 4, 7 }), 83494518);
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
