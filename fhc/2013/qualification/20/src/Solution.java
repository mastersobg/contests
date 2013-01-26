import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		String s = in.readLine();
		int[] cnt = new int[26];
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (Character.isLetter(c)) {
				c = Character.toLowerCase(c);
				cnt[c - 'a']++;
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < cnt.length; ++i)
			if (cnt[i] > 0) {
				list.add(cnt[i]);
			}
		Collections.sort(list);
		int letter = 26;
		int ret = 0;
		for (int i = list.size() - 1; i >= 0; --i, --letter) {
			ret += letter * list.get(i);
		}
		out.println(ret);
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		int t = ni();
		for (int it = 0; it < t; ++it) {
			out.print("Case #" + (it + 1) + ": ");
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
