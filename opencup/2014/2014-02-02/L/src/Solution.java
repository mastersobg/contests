import java.io.*;
import java.util.*;

import javax.management.RuntimeErrorException;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	class Event implements Comparable<Event> {
		int y1, y2, x;
		boolean open;

		Event(int y1, int y2, int x, boolean open) {
			this.y1 = y1;
			this.y2 = y2;
			this.x = x;
			this.open = open;
		}

		@Override
		public int compareTo(Event e) {
			if (x != e.x)
				return x - e.x;
			return open == e.open ? 0 : (open ? -1 : 1);
		}
	}

	class SegmentTree {

		int[] min;
		int[] count;
		int[] add;
		int size;

		SegmentTree(int[] arr) {
			int size = arr.length;
			min = new int[size * 4];
			count = new int[size * 4];
			add = new int[size * 4];
			this.size = size;
			init(0, size - 1, 0, arr);
		}

		void init(int l, int r, int idx, int[] cnt) {
			// assert(0 <= l && l <= r && r < size);
			// assert(0 <= idx && idx < min.length);
			if (l == r) {
				min[idx] = 0;
				count[idx] = cnt[l];
			} else {
				int m = (l + r) >> 1;
				init(l, m, idx * 2 + 1, cnt);
				init(m + 1, r, idx * 2 + 2, cnt);
				count[idx] = count[idx * 2 + 1] + count[idx * 2 + 2];
			}
		}

		void add(int l, int r, int cnt) {
			add(l, r, 0, size - 1, 0, cnt);
		}

		int getMinimumCount() {
			push(0);
			return count[0];
		}

		void push(int idx) {
			if (add[idx] != 0) {
				min[idx] += add[idx];
				if (idx * 2 + 1 < min.length)
					add[idx * 2 + 1] += add[idx];
				if (idx * 2 + 2 < min.length)
					add[idx * 2 + 2] += add[idx];
				add[idx] = 0;
			}
		}

		void ass(boolean cond) {
			if (!cond) {
				throw new RuntimeException();
			}
		}

		void add(int lf, int rg, int l, int r, int idx, int cnt) {
			ass(0 <= lf && lf <= rg && rg <= r && r < size);
			ass(0 <= idx && idx < min.length);
			push(idx);
			if (lf == l && rg == r) {
				add[idx] += cnt;
			} else {
				int m = (l + r) >> 1;
				if (rg <= m) {
					add(lf, rg, l, m, idx * 2 + 1, cnt);
				} else if (lf > m) {
					add(lf, rg, m + 1, r, idx * 2 + 2, cnt);
				} else {
					add(lf, m, l, m, idx * 2 + 1, cnt);
					add(m + 1, rg, m + 1, r, idx * 2 + 2, cnt);
				}
				push(idx * 2 + 1);
				push(idx * 2 + 2);
				if (min[idx * 2 + 1] < min[idx * 2 + 2]) {
					min[idx] = min[idx * 2 + 1];
					count[idx] = count[idx * 2 + 1];
				} else if (min[idx * 2 + 1] > min[idx * 2 + 2]) {
					min[idx] = min[idx * 2 + 2];
					count[idx] = count[idx * 2 + 2];
				} else {
					min[idx] = min[idx * 2 + 1];
					count[idx] = count[idx * 2 + 1] + count[idx * 2 + 2];
				}
			}
		}
	}

	int n;
	List<Event> events;

	long solve() throws IOException {
		Timer timer = new Timer();
		n = ni();
		events = new ArrayList<Event>(n * 2);
		int maxY = -1;
		for (int i = 0; i < n; ++i) {
			int x1 = ni(), y1 = ni(), x2 = ni(), y2 = ni();
			events.add(new Event(y1, y2, x1, true));
			events.add(new Event(y1, y2, x2, false));
			maxY = max(y1, maxY);
			maxY = max(y2, maxY);
		}
		int[] compressed = compressY();
		Collections.sort(events);
		timer.start();
		SegmentTree tree = new SegmentTree(compressed);
		int all = tree.getMinimumCount();
		long result = 0;
		for (int i = 0; i < events.size(); ++i) {
			Event e = events.get(i);
			if (i > 0) {
				int minimum = tree.getMinimumCount();
				int count = all - minimum;
				result += (long) count * (e.x - events.get(i - 1).x);
			}
			if (e.open) {
				tree.add(e.y1, e.y2 - 1, 1);
			} else {
				tree.add(e.y1, e.y2 - 1, -1);
			}
		}
		out.println(result);
		out.flush();
		return timer.time();
	}

	int[] compressY() {
		Set<Integer> set = new TreeSet<Integer>();
		for (Event e : events) {
			set.add(e.y1);
			set.add(e.y2);
		}
		HashMap<Integer, Integer> rev = new HashMap<Integer, Integer>();
		int[] setArr = new int[set.size()];
		int idx = 0;
		for (int a : set) {
			setArr[idx++] = a;
			rev.put(a, idx - 1);
		}
		int[] result = new int[set.size() + 1];
		for (int i = 0; i < idx; ++i) {
			if (i == idx - 1)
				result[i] = result[i + 1] = 1;
			else
				result[i] = setArr[i + 1] - setArr[i];
		}
		for (Event e : events) {
			e.y1 = rev.get(e.y1);
			e.y2 = rev.get(e.y2);
		}
		return result;
	}

	void test() throws IOException {
		PrintWriter out = new PrintWriter("test");
		Random rnd = new Random();
        int mx = 5;
        int n = 3;
        int tests = 1;
		out.println(tests);
		for (int t = 0; t < tests; ++t) {
			out.println(n);
			for (int i = 0; i < n; ++i) {
				int x1 = rnd.nextInt(mx);
				int y1 = rnd.nextInt(mx);
				int addX = rnd.nextInt(mx) + 1;
				int addY = rnd.nextInt(mx) + 1;
				int x2 = min(x1 + addX, mx);
				int y2 = min(y1 + addY, mx);
				out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			}
		}
		out.close();
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		// test();
		Timer timer = new Timer();
		timer.start();
		long time = 0;
		for (int t = ni(); t > 0; --t) {
			time += solve();
		}
		// dbg("timeadd = ", time);
		// timer.print();
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
//        if (args.length > 0)
//            new Solution().test();
//        else
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
