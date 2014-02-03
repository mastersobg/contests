import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	double[] v;

	void solve() throws IOException {
		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < 4; ++i)
			list.add((double)ni());
		boolean ret = go(list);
		if (ret) {
			out.println("YES");
		} else
			out.println("NO");
	}

	boolean go(List<Double> values) {
		if (values.size() == 1) {
			return abs(values.get(0) - 24) < 1e-9;
		} else {
			for (int i = 0; i < values.size(); ++i) {
				for (int j = 0; j < values.size(); ++j)
					if (i != j) {
						for (int op = 0; op < 4; ++op) {
							List<Double> next = new ArrayList<Double>();
							Solve s = new Solve();
							next.add(s.op(values.get(i), values.get(j), op));
							if (s.error) {
								continue;
							}
							for (int k = 0; k < values.size(); ++k)
								if (i != k && j != k)
									next.add(values.get(k));
							if (go(next))
								return true;
						}
					}
			}
		}
		return false;
	}

	class Solve {
		boolean error;

		double op(double a, double b, int op) {
			if (op == 0)
				return a + b;
			if (op == 1)
				return a - b;
			if (op == 2)
				return a * b;
			if (op == 3) {
				if (b == 0) {
					error = true;
					return 0;
				} else
					return a / b;
			}
			throw new RuntimeException();
		}
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
