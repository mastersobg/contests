import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	char[] s1, s2;
	int[] l1, l2;

	int[] calcLet(char[] s) {
		int[] ret = new int[26];
		for (char c : s) {
			int l = c - 'A';
			ret[l]++;
		}
		return ret;
	}

	void solve() throws IOException {
		s1 = ns().toCharArray();
		s2 = ns().toCharArray();
		l1 = calcLet(s1);
		l2 = calcLet(s2);
		int ret = 0;
		int nextChar = 0;
		int[] left = new int[26];
		System.arraycopy(l1, 0, left, 0, 26);
		for (int i = 0; i < s1.length; ++i) {
			int ch = s1[i] - 'A';
			nextChar = next(nextChar);
			if (l1[ch] > l2[ch]) {
				if (nextChar < ch) {
					++ret;
					l1[nextChar]++;
					l1[ch]--;
					s1[i] = (char) (nextChar + 'A');
				} else {
					int needToReplace = l1[ch] - l2[ch];
					if (left[ch] <= needToReplace) {
						++ret;
						l1[nextChar]++;
						l1[ch]--;
						s1[i] = (char) (nextChar + 'A');
					}
				}
			}
			left[ch]--;
		}
		out.println(ret);
		out.println(new String(s1));
	}

	int next(int cur) {
		while (cur < 26 && l2[cur] <= l1[cur])
			++cur;
		return cur;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
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
		new Solution();
	}
}
