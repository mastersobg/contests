package main;

import static java.lang.Math.*;

public class TheLargestString {

	static class Ret implements Comparable<Ret> {
		final String s1, s2, c;

		Ret(String s1, String s2) {
			this.s1 = s1;
			this.s2 = s2;
			c = s1 + s2;
		}

		public int compareTo(Ret o) {
			return c.compareTo(o.c);
		}
	}

	Ret[][] dp;

	final Ret empty = new Ret("", "");

	Ret rec(int pos, int length) {
		if (length == 0) {
			return empty;
		}
		Ret ret = dp[pos][length];
		if (ret == null) {
			ret = empty;
			if (can(pos + 1, length - 1)) {
				Ret a = rec(pos + 1, length - 1);
				a = new Ret(s.charAt(pos) + a.s1, t.charAt(pos) + a.s2);
				if (ret.compareTo(a) < 0) {
					ret = a;
				}
			}
			if (can(pos + 1, length)) {
				Ret a = rec(pos + 1, length);
				if (ret.compareTo(a) < 0) {
					ret = a;
				}
			}
			dp[pos][length] = ret;
		}
		return ret;
	}

	String s, t;

	boolean can(int pos, int len) {
		return s.length() - pos >= len;
	}

	public String find(String s, String t) {
		this.s = s;
		this.t = t;
		dp = new Ret[s.length() + 1][s.length() + 1];
		Ret ret = empty;
		for (int i = 0; i <= s.length(); ++i) {
			Ret a = rec(0, i);
			if (ret.compareTo(a) < 0) {
				ret = a;
			}
		}
		return ret.c;
	}

	// BEGIN CUT HERE
	public static void main(String[] args) {
		try {
			eq(3,
					(new TheLargestString()).find(
							"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
							"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			eq(2, (new TheLargestString()).find("x", "x"), "xx");
			eq(0, (new TheLargestString()).find("ab", "zy"), "by");
			eq(1, (new TheLargestString()).find("abacaba", "zzzaaaa"), "cbaaaa");
			eq(3, (new TheLargestString()).find("abbabbabbababaaaabbababab",
					"bababbaabbbababbbbababaab"), "bbbbbbbbbbbbbbbbbbaaab");
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
