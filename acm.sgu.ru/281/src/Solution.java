import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int n = ni();
		String[] v1 = new String[n];
		String[] v2 = new String[n];
		for (int i = 0; i < n; ++i)
			v1[i] = ns();
		for (int i = 0; i < n; ++i)
			v2[i] = ns();
		HashSet<String> s1 = new HashSet<String>(), s2 = new HashSet<String>();
		TreeSet<String> s = new TreeSet<String>();
		String[] ret = new String[n];
		int size = 0;
		for (int i = 0; i < n; ++i) {
			if (s1.isEmpty() && s2.isEmpty() && v1[i].equals(v2[i])) {
				ret[size++] = v1[i];
			} else {
				if (s2.contains(v1[i])) {
					s2.remove(v1[i]);
					s.add(v1[i]);
				} else
					s1.add(v1[i]);
				if (s1.contains(v2[i])) {
					s1.remove(v2[i]);
					s.add(v2[i]);
				} else
					s2.add(v2[i]);
				if (s1.isEmpty() && s2.isEmpty()) {
					size = addRet(s, ret, size);
				}
			}
		}
		addRet(s, ret, size);
		for (String str : ret)
			out.println(str);
	}

	int addRet(TreeSet<String> s, String[] ret, int size) {
		for (String str : s) {
			ret[size++] = str;
		}
		s.clear();
		return size;
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
