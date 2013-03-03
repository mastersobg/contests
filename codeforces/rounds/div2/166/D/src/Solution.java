import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	char[] s;
	boolean[] ok = new boolean[26];
	int k;

	static class Node {

		Node[] next = new Node[26];

		boolean hasNext(char c) {
			return next[c - 'a'] != null;
		}

		Node getNext(char c) {
			return next[c - 'a'];
		}

		void addNext(char c, Node node) {
			next[c - 'a'] = node;
		}
	}

	void solve() throws IOException {
		s = ns().toCharArray();
		String letters = ns();
		for (int i = 0; i < letters.length(); ++i)
			if (letters.charAt(i) == '1')
				ok[i] = true;
		k = ni();
		Node root = new Node();
		int ret = 0;
		for (int i = 0; i < s.length; ++i) {
			Node cur = root;
			int cnt = 0;
			for (int j = i; j < s.length; ++j) {
				char c = s[j];
				if (ok[c - 'a'] == false)
					++cnt;
				if (cnt > k)
					break;
				if (cur.hasNext(c) == false) {
					++ret;
					cur.addNext(c, new Node());
				}
				cur = cur.getNext(c);
			}
		}
		out.println(ret);
	}

	void gen() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test.txt");
		Random rnd = new Random();
		for (int i = 0; i < 1500; ++i) {
			char c = (char) (rnd.nextInt(26) + 'a');
			out.print(c);
		}
		out.close();
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		gen();
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
