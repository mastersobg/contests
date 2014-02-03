import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		int n = ni();
		int k = ni();
		int d = ni();
		HashSet<String> words = new HashSet<String>();
		for (int i = 0; i < n; ++i)
			words.add(ns());
		HashSet<String> bad = new HashSet<String>();
		for (int i = 0; i < k; ++i) {
			String line = in.readLine();
			parse(words, bad, line);
		}
		if (bad.size() >= d)
			out.println("Danger");
		else
			out.println("No");
	}

	void parse(HashSet<String> dict, HashSet<String> words, String line) {
		Parser p = new Parser(line);
		String s = null;
		while ((s = p.next()) != null) {
			if (dict.contains(s)) {
				words.add(s);
			}
		}
	}

	class Parser {
		String text;
		int pos = 0;

		Parser(String text) {
			this.text = text;
		}

		String next() {
			skip();
			if (pos >= text.length()) {
				return null;
			}
			StringBuilder result = new StringBuilder();
			while (pos < text.length()
					&& ('a' <= text.charAt(pos) && text.charAt(pos) <= 'z')) {
				result.append(text.charAt(pos));
				++pos;
			}
			return result.toString();
		}

		void skip() {
			while (pos < text.length()
					&& !('a' <= text.charAt(pos) && text.charAt(pos) <= 'z'))
				++pos;
		}

	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int p = ni();
		for (; p > 0; --p) {
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
