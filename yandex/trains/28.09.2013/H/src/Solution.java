import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
	String filename = "seating";

	static class Dim implements Comparable<Dim> {
		int cnt;
		int dim;

		Dim(int cnt, int dim) {
			this.cnt = cnt;
			this.dim = dim;
		}

		public int compareTo(Dim dim) {
			return dim.cnt - cnt;
		}

		public String toString() {
			return "[" + "cnt=" + cnt + "," + "dim=" + dim + "]";
		}
	}

	int k, r, m;
	Dim[] v;
	long start = System.currentTimeMillis();

	void solve() throws IOException {
		k = ni();
		r = ni();
		m = ni();
		v = new Dim[k];
		int ss = 0;
		for (int i = 0; i < k; ++i) {
			v[i] = new Dim(ni(), i);
			ss += v[i].cnt;
		}
//		Arrays.sort(v);
		//dbg(v);
		ret = -1;
		seat = new int[ss];
		try {
			rec(0, 0, new int[m], new int[ss], 0);
		} catch (RuntimeException e) {

		}
		//dbg(seat);
		out.println(ret);
		int[][] ans = new int[m][r];
		int dim = 0;
		int cur = 0;
		for (int i = 0; i < seat.length; ++i) {
			int x = seat[i] / r;
			int y = seat[i] % r;
			ans[x][y] = v[dim].dim + 1;
			++cur;
			if (cur == v[dim].cnt) {
				dim++;
				cur = 0;
			}
		}
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < r; ++j) {
				out.print(ans[i][j] + " ");
			}
			out.println();
		}
	}

	int[] seat;
	int ret;

	void rec(int i, int men, int[] cnt, int[] s, int sum) {
//		if (System.currentTimeMillis() - start > 1950) {
//		  throw new RuntimeException();
//		}
		if (i == k) {
			if (sum > ret) {
				ret = sum;
				System.arraycopy(s, 0, seat, 0, s.length);
				// throw new RuntimeException();
			}
			return;
		}

		for (int it = cnt.length - 1; it > 0; --it) {
			int mask = can(cnt, v[i].cnt, s, it, (byte) 1, men);
			if (mask != -1) {
				for (int j = 0; j < cnt.length; ++j) {
					if ((mask & (1 << j)) != 0) {
						cnt[j]++;
					}
				}
				rec(i + 1, men + v[i].cnt, cnt, s, sum + it);
				for (int j = 0; j < cnt.length; ++j) {
					if ((mask & (1 << j)) != 0) {
						cnt[j]--;
					}
				}
			}
		}

		int[] ncnt = cnt.clone();
		int first = 0;
		for (int it = 0; it < v[i].cnt; ++it) {
			while (first < ncnt.length && ncnt[first] == r) {
				++first;
			}
			if (first >= ncnt.length) {
				throw new RuntimeException();
			}

			s[men + it] = first * r + ncnt[first];
			ncnt[first]++;
			++first;
			if (first == ncnt.length) {
				first = 0;
			}
		}
//		dbg(ncnt) ;
//		dbg(s);
		rec(i + 1, men + v[i].cnt, ncnt, s, sum);

	}

	int can(int[] cnt, int count, int[] s, int diff, byte set, int men) {
		int first = 0;
		int mask = 0;
		for (int i = 0; i < count; ++i) {
			while (first < cnt.length && cnt[first] == r) {
				++first;
			}
			if (first >= cnt.length) {
				return -1;
			}

			if (set != 0) {
//				dbg("place=", men + i);
				s[men + i] = first * r + cnt[first];
				mask |= (1 << first);
			}

			first += diff;
		}

		return mask;
	}

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

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader(filename + ".in"));
		out = new PrintWriter(filename + ".out");
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
}
