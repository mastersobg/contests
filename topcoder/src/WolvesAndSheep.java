import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class WolvesAndSheep {

	char[][] data;
	int n, m;

	public int getmin(String[] field) {
		data = new char[field.length][];
		n = field.length;
		m = field[0].length();
		for (int i = 0; i < n; ++i) {
			data[i] = field[i].toCharArray();
		}

		int ret = 1 << 30;
		int board = 1 << (m - 1);
		for (int mask = 0; mask < board; ++mask) {
			if (!checkMask(mask)) {
				continue;
			}
			ret = min(ret, count(mask) + Integer.bitCount(mask));
			if (count(mask) + Integer.bitCount(mask) == 6) {
				dbg(Integer.toString(mask, 2));
				for (int i = 1; i <= pts[0]; ++i) {
					dbg(pts[i]);
				}
				dbg("---------");
			}
		}

		return ret;
	}

	static class Pair {
		int l, r;

		public Pair(int l, int r) {
			super();
			this.l = l;
			this.r = r;
		}
	}

	static class Event implements Comparable<Event> {
		int segm;
		int x;
		boolean left;

		public Event(int segm, int x, boolean left) {
			super();
			this.segm = segm;
			this.x = x;
			this.left = left;
		}

		@Override
		public int compareTo(Event o) {
			if (x != o.x)
				return x - o.x;
			return Boolean.valueOf(left).compareTo(o.left);
		}

	}

	int count(int mask) {
		List<Pair> s = new ArrayList<Pair>();
		for (int j = 0; j < m; ++j) {
			char prev = '.';
			int row = 0;
			for (int i = 0; i < n; ++i) {
				if (data[i][j] != '.') {
					if (data[i][j] != prev && prev != '.') {
						s.add(new Pair(row, i - 1));
					}
					prev = data[i][j];
					row = i;
				}
			}
		}
		List<Event> events = new ArrayList<Event>();
		int idx = 0;
		for (Pair p : s) {
			events.add(new Event(idx, p.l, false));
			events.add(new Event(idx, p.r, true));
			++idx;
		}
		Collections.sort(events);
		int ret = 0;
		int top = 0;
		boolean[] covered = new boolean[s.size()];
		pts[0] = 0;
		for (Event e : events) {
			if (!e.left) {
				stack[top++] = e.segm;
			} else {
				if (!covered[e.segm]) {
					++ret;
					pts[++pts[0]] = e.x;
					while (top > 0) {
						covered[stack[--top]] = true;
					}
				}
			}
		}
		return ret;
	}
	
	int []pts = new int[11000];

	int[] stack = new int[11000];

	boolean checkMask(int mask) {
		for (int i = 0; i < n; ++i) {
			char prev = '.';
			for (int j = 0; j < m; ++j) {
				if (data[i][j] != '.') {
					if (data[i][j] != prev && prev != '.') {
						return false;
					}
					prev = data[i][j];
				}
				if (checkBit(mask, j))
					prev = '.';
			}
		}
		return true;
	}

	boolean checkBit(int mask, int bit) {
		return (mask & (1 << bit)) != 0;
	}

	int resetBit(int mask, int bit) {
		return mask & ~(1 << bit);
	}

	int setBit(int mask, int bit) {
		return mask | (1 << bit);
	}

	// BEGIN CUT HERE
	static boolean DEBUG = false;

	void dbg(Object... objs) {
		if (!DEBUG) {
			return;
		}
		for (Object o : objs) {
			String printLine;
			if (o.getClass().isArray()) {
				printLine = arrayToString(o);
			} else {
				printLine = o.toString();
			}
			System.err.print(printLine + " ");
		}
		System.err.println();
	}

	String arrayToString(Object o) {
		if (o instanceof long[])
			return Arrays.toString((long[]) o);
		if (o instanceof int[])
			return Arrays.toString((int[]) o);
		if (o instanceof short[])
			return Arrays.toString((short[]) o);
		if (o instanceof char[])
			return Arrays.toString((char[]) o);
		if (o instanceof byte[])
			return Arrays.toString((byte[]) o);
		if (o instanceof double[])
			return Arrays.toString((double[]) o);
		if (o instanceof float[])
			return Arrays.toString((float[]) o);
		if (o instanceof boolean[])
			return Arrays.toString((boolean[]) o);
		if (o instanceof Object[])
			return Arrays.deepToString((Object[]) o);
		throw new IllegalStateException();
	}

	public static void main(String[] args) {
		try {
			DEBUG = true;
//			eq(0, (new WolvesAndSheep()).getmin(new String[] { "WS", "SW" }), 2);
//			eq(0,
//					(new WolvesAndSheep()).getmin(new String[] { "W.WSS",
//							"WW.S.", ".SSS.", "SSS.S", ".SS.S" }), 2);
//			eq(1,
//					(new WolvesAndSheep()).getmin(new String[] { ".....",
//							".....", "....." }), 0);
//			eq(2,
//					(new WolvesAndSheep()).getmin(new String[] { ".SS", "...",
//							"S..", "W.W" }), 1);
//			eq(3,
//					(new WolvesAndSheep()).getmin(new String[] { "WWWSWWSSWWW",
//							"WWSWW.SSWWW", "WS.WSWWWWS." }), 10);
			eq(4,
					(new WolvesAndSheep()).getmin(new String[] { ".W.S.W.W",
							"W.W.S.W.", ".S.S.W.W", "S.S.S.W.", ".S.W.W.S",
							"S.S.W.W.", ".W.W.W.S", "W.W.S.S." }), 7);
//			eq(5,
//					(new WolvesAndSheep()).getmin(new String[] {
//							"W.SSWWSSSW.SS", ".SSSSW.SSWWWW", ".WWWWS.WSSWWS",
//							"SS.WSS..W.WWS", "WSSS.SSWS.W.S", "WSS.WS...WWWS",
//							"S.WW.S.SWWWSW", "WSSSS.SSW...S", "S.WWSW.WWSWSW",
//							".WSSS.WWSWWWS", "..SSSS.WWWSSS", "SSWSWWS.W.SSW",
//							"S.WSWS..WSSS.", "WS....W..WSS." }), 24);
//			eq(6,
//					(new WolvesAndSheep()).getmin(new String[] { "WW..SS",
//							"WW..SS", "......", "......", "SS..WW", "SS..WW" }),
//					2);
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
