package main;

import java.util.*;

import static java.lang.Math.*;

public class SpaceWarDiv1 {

	Person[] girls, enemies;

	static class Person implements Comparable<Person> {
		int s;
		long cnt;
		long beat;
		int idx = 0;

		Person(int s, long cnt, int idx) {
			this.s = s;
			this.cnt = cnt;
			this.idx = idx;
		}

		public int compareTo(Person o) {
			if (o.s == s)
				return idx - o.idx;
			return o.s - s;
		}
	}

	public long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength,
			long[] enemyCount) {
		girls = new Person[magicalGirlStrength.length];
		for (int i = 0; i < magicalGirlStrength.length; ++i)
			girls[i] = new Person(magicalGirlStrength[i], 1, i);
		enemies = new Person[enemyCount.length];
		for (int i = 0; i < enemyCount.length; ++i)
			enemies[i] = new Person(enemyStrength[i], enemyCount[i], i);
		Arrays.sort(girls);
		Arrays.sort(enemies);
		long l = 0, r = 0;
		for (int i = 0; i < enemyCount.length; ++i)
			r += enemyCount[i];
		while (l + 1 < r) {
			long mid = (l + r) / 2l;
			if (model(mid)) {
				r = mid;
			} else {
				l = mid;
			}
		}
		if (model(l)) {
			return l;
		} else if (model(r)) {
			return r;
		} else
			return -1;
	}

	boolean model(long F) {
		for (Person p : girls)
			p.beat = 0;
		TreeSet<Person> g = new TreeSet<Person>();
		int idx = 0;
		for (Person enemy : enemies) {
			while (idx < girls.length) {
				if (girls[idx].s < enemy.s) {
					break;
				}
				g.add(girls[idx]);
				idx++;
			}
			long cnt = enemy.cnt;
			while (cnt > 0 && !g.isEmpty()) {
				Person p = g.first();
				long can = F - p.beat;
				if (can > cnt) {
					p.beat += cnt;
					cnt = 0;
				} else {
					cnt -= can;
					g.remove(p);
				}
			}
			if (cnt != 0) {
				return false;
			}
		}
		return true;
	}

	static boolean DEBUG = false;

	void dbg(Object... args) {
		if (!DEBUG)
			return;
		for (Object o : args)
			System.err.print(o + " ");
		System.err.println();
	}

	// BEGIN CUT HERE
	public static void main(String[] args) {
		try {
			DEBUG = true;
			eq(1, (new SpaceWarDiv1()).minimalFatigue(new int[] { 2, 3, 5 },
					new int[] { 1, 1, 2 }, new long[] { 2L, 9L, 4L }), 5L);
			eq(0, (new SpaceWarDiv1()).minimalFatigue(new int[] { 2, 3, 5 },
					new int[] { 1, 3, 4 }, new long[] { 2L, 9L, 4L }), 7L);
			eq(2, (new SpaceWarDiv1()).minimalFatigue(new int[] { 14, 6, 22 },
					new int[] { 8, 33 }, new long[] { 9L, 1L }), -1L);
			eq(3, (new SpaceWarDiv1()).minimalFatigue(new int[] { 869, 249,
					599, 144, 929, 748, 665, 37, 313, 99, 33, 437, 308, 137,
					665, 834, 955, 958, 613, 417 }, new int[] { 789, 57, 684,
					741, 128, 794, 542, 367, 937, 739, 568, 872, 127, 261, 103,
					763, 864, 360, 618, 307 }, new long[] { 20626770196420L,
					45538527263992L, 52807114957507L, 17931716090785L,
					65032910980630L, 88711853198687L, 26353250637092L,
					61272534748707L, 89294362230771L, 52058590967576L,
					60568594469453L, 23772707032338L, 43019142889727L,
					39566072849912L, 78870845257173L, 68135668032761L,
					36844201017584L, 10133804676521L, 6275847412927L,
					37492167783296L }), 75030497287405L);
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
