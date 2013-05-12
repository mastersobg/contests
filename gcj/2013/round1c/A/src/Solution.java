import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		char[] s = ns().toCharArray();
		int n = ni();
		long ret = 0;
		int prev = 0;
		int last = -1;
		for (int i = 0; i < s.length; ++i) {
			char c = s[i];
			if (isVowel(c)) {
				prev = 0;
				if (last != -1)
					ret = (ret + last - n + 2);
			} else {
				++prev;
				if (prev >= n) {
					last = i;
					ret = (ret + (long) (i - n + 2));
				} else {
					if (last != -1)
						ret = (ret + last - n + 2);
				}
			}
		}
		out.println(ret);
	}

	boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	void generate() throws FileNotFoundException {
		Random rnd = new Random();
		PrintWriter out = new PrintWriter("test.txt");
		out.println(7);
		for (int k = 0; k < 7; ++k) {
			for (int i = 0; i < 1000000; ++i) {
				int a = rnd.nextInt(26);
				out.print((char) (a + 'a'));
			}
			out.println(" 1");
		}
		out.close();
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		// generate();
		in = new BufferedReader(new FileReader("input_large.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int test = 1; test <= t; ++test) {
			out.print("Case #" + test + ": ");
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
		new Solution();
	}
}
