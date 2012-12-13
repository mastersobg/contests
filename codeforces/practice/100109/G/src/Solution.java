import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	HashMap<String, Integer>[] maps = new HashMap[5];

	void solve() throws IOException {
		for (int i = 0; i < maps.length; i++) {
			maps[i] = new HashMap<String, Integer>();
		}
		n = ni();
		for (int it = 0; it < n; ++it) {
			int a = ni();
			String[] v = new String[a];
			for (int j = 0; j < a; ++j) {
				v[j] = ns();
			}
			Arrays.sort(v);

			for (int mask = 1; mask < (1 << a); ++mask) {
				StringBuilder sb = new StringBuilder();
				for (int bit = 0; bit < a; ++bit)
					if ((mask & (1 << bit)) != 0) {
						sb.append(v[bit] + ',');
					}
				String s = sb.toString();
				HashMap<String, Integer> map = maps[Integer.bitCount(mask)];
				if (!map.containsKey(s)) {
					map.put(s, 1);
				} else {
					map.put(s, map.get(s) + 1);
				}
			}
		}

		int m = ni();
		for (int it = 0; it < m; ++it) {
			int a = ni();
			String[] v = new String[a];
			for (int j = 0; j < a; ++j)
				v[j] = ns();
			Arrays.sort(v);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < a; ++j)
				sb.append(v[j] + ',');
			Integer ret = maps[a].get(sb.toString());
			if (ret == null)
				ret = 0;
			out.println(ret);
		}
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
