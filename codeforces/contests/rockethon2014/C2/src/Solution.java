import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;

	class Player implements Comparable<Player> {

		int idx;
		int p, e;
		int place;

		Player(int idx, int a, int b) {
			this.idx = idx;
			p = a;
			e = b;
		}

		@Override
		public int compareTo(Player o) {
			return e - o.e;
		}
	}

	class Cmp implements Comparator<Player> {

		@Override
		public int compare(Player o1, Player o2) {
			return o2.p - o1.p;
		}
	}

	Player[] p;

	void solve() throws IOException {
		n = ni();
		k = ni();
		p = new Player[n];
		Player[] order = new Player[n + 1];
		for (int i = 0; i < n; ++i) {
			p[i] = new Player(i, ni(), ni());
			order[i] = p[i];
		}
		Player our = new Player(n, 0, 0);
		order[n] = our;
		Arrays.sort(p);
		Arrays.sort(order, new Cmp());
		for (int i = 0; i <= n; ++i)
			order[i].place = i;
		int result = 0;
		int win = 0;
		if (n + 1 <= k) {
			out.println(0);
			return;
		}
		boolean[] was = new boolean[n + 1];
		for (Player player : p) {
			result += player.e;
			was[player.idx] = true;
			++win;

			int pl = player.place;
			while (true) {
				if (pl == n)
					break;
				int pts1 = player.p;
				int pts2 = order[pl + 1].p + (was[order[pl + 1].idx] ? 0 : 1);
				if (pts2 >= pts1) {
					Player tmp = order[pl];
					order[pl] = order[pl + 1];
					order[pl + 1] = tmp;
					order[pl].place--;
					order[pl + 1].place++;
				} else
					break;
				++pl;
			}

			for (int idx = our.place - 1; idx >= 0; --idx) {
				int pts = order[idx].p + (was[order[idx].idx] ? 0 : 1);
				if (pts < win || (pts == win && was[order[idx].idx])) {
					Player tmp = order[idx];
					order[idx] = order[idx + 1];
					order[idx + 1] = tmp;
					order[idx + 1].place++;
					our.place --;
				} else break;
			}
			if (our.place + 1 <= k)
				break;
		}
		if (our.place + 1 <= k)
			out.println(result);
		else
			out.println(-1);
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
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
