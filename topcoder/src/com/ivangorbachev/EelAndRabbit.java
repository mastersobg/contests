package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class EelAndRabbit {

	static class Event implements Comparable<Event> {
		int time;
		boolean open;
		int idx;

		public int compareTo(Event o) {
			if (time != o.time)
				return time - o.time;
			return open == o.open ? 0 : (open == true) ? -1 : 1;
		}

	}

	public int getmax(int[] l, int[] t) {
		ArrayList<Event> events = new ArrayList<Event>();
		for (int i = 0; i < l.length; ++i) {
			Event e = new Event();
			e.time = t[i];
			e.open = true;
			e.idx = i;
			events.add(e);
			e = new Event();
			e.time = t[i] + l[i];
			e.open = false;
			e.idx = i;
			events.add(e);
		}
		Collections.sort(events);

		HashSet[] hs = new HashSet[events.size()];
		HashSet<Integer> ret = new HashSet<Integer>();
		for (int i = 0; i < hs.length; i++) {
			Event e = events.get(i);
			if (e.open) {
				ret.add(e.idx);
			} else {
				ret.remove(e.idx);
			}
			hs[i] = new HashSet<Integer>(ret);
		}
		int mx = 0;
		for (int i = 0; i < events.size(); ++i)
			for (int j = i + 1; j < events.size(); ++j) {
				HashSet<Integer> a = new HashSet<Integer>(hs[i]);
				a.addAll(hs[j]);
				mx = max(mx, a.size());
			}
		return mx;
	}

	// BEGIN CUT HERE
	public static void main(String[] args) {
		try {
			eq(0,
					(new EelAndRabbit()).getmax(new int[] { 2, 4, 3, 2, 2, 1,
							10 }, new int[] { 2, 6, 3, 7, 0, 2, 0 }), 6);
			eq(1, (new EelAndRabbit()).getmax(new int[] { 1, 1, 1 }, new int[] {
					2, 0, 4 }), 2);
			eq(2,
					(new EelAndRabbit()).getmax(new int[] { 1 },
							new int[] { 1 }), 1);
			eq(3,
					(new EelAndRabbit()).getmax(new int[] { 8, 2, 1, 10, 8, 6,
							3, 1, 2, 5 }, new int[] { 17, 27, 26, 11, 1, 27,
							23, 12, 11, 13 }), 7);
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
