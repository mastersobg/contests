import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	class Rect {
		int x1, y1, x2, y2;

		public Rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

	int n;
	Rect[] rects;
	int[][] tree;

	void add(int x, int y, int add) {
		for (; x < tree.length; x += x & -x) {
			for (int j = y; j < tree[x].length; j += j & -j)
				tree[x][j] += add;
		}
	}

	int count(int x, int y) {
		if (x == 0 || y == 0)
			return 0;
		int ret = 0;
		for (; x > 0; x -= x & -x) {
			for (int j = y; j > 0; j -= j & -j)
				ret += tree[x][j];
		}
		return ret;
	}

	void solve() throws IOException {
		n = ni();
		rects = new Rect[n];
		for (int i = 0; i < n; ++i) {
			rects[i] = new Rect(ni(), ni(), ni(), ni());
		}
		int maxX = compactX();
		int maxY = compactY();
		tree = new int[maxX + 5][maxY + 5];
		for (Rect r : rects) {
			add(r.x2, r.y2, 1);
			add(r.x2, r.y1, -1);
			add(r.x1, r.y2, -1);
			add(r.x1, r.y1, 1);
		}
		long ret = 0;
		for (int i = 1; i < maxX - 1; ++i)
			for (int j = 1; j < maxY - 1; ++j) {
				int count = count(i, j);
				count -= count(i - 1, j);
				count -= count(i, j - 1);
				count += count(i - 1, j - 1);
				if (count != 0) {
					long dx = X.get(i + 1) - X.get(i);
					long dy = Y.get(j + 1) - Y.get(j);
					ret += dx * dy;
				}
			}	
		out.println(ret);
	}

	HashMap<Integer, Integer> X = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> Y = new HashMap<Integer, Integer>();

	int compactX() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (Rect r : rects) {
			set.add(r.x1);
			set.add(r.x2);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int idx = 1;
		for (Integer a : set) {
			X.put(idx, a);
			map.put(a, idx++);
		}
		for (Rect r : rects) {
			r.x1 = map.get(r.x1);
			r.x2 = map.get(r.x2);
		}
		return idx;
	}

	int compactY() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (Rect r : rects) {
			set.add(r.y1);
			set.add(r.y2);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int idx = 1;
		for (Integer a : set) {
			Y.put(idx, a);
			map.put(a, idx++);
		}
		for (Rect r : rects) {
			r.y1 = map.get(r.y1);
			r.y2 = map.get(r.y2);
		}
		return idx;
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int t = ni(); t > 0; --t) {
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
		new Solution().run();
	}

	class Timer {

		long time;

		void start() {
			time = System.currentTimeMillis();
		}

		long time() {
			return System.currentTimeMillis() - time;
		}

		void print() {
			print("Time spent = ");
		}

		void print(String message) {
			dbg(message, time());
		}

	}

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

}
