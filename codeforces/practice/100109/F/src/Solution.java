import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n, k;
	int[] w, c;
	int[] weight = new int[4001];
	ArrayList<Integer>[] list = new ArrayList[4001];

	void solve() throws IOException {
		n = ni();
		k = ni();
		w = new int[n];
		c = new int[n];
		for (int i = 0; i < n; ++i) {
			w[i] = ni();
			c[i] = ni();
		}
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; ++i) {
			weight[w[i]]++;
			list[w[i]].add(c[i]);
		}
		for (int i = 0; i < list.length; i++) {
			Collections.sort(list[i]);
		}
		int ret = count();
		out.println(ret + " " + calc(ret));
	}

	int calc(int cnt) {
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for (int i = 0; i < weight.length; ++i)
			if (weight[i] >= cnt) {
				int r = 0;
				for (int j = list[i].size() - 1, k = 0; k < cnt; --j, ++k)
					r += list[i].get(j);
				lst.add(r);
			}
		Collections.sort(lst);
		int ret = 0;
		for (int i = lst.size() - 1, j = 0; j < k; ++j, --i)
			ret += lst.get(i);
		return ret;
	}

	int count() {
		int l = 0, r = n;
		while (l + 1 < r) {
			int mid = (l + r) >> 1;
			if (cnt(mid) >= k)
				l = mid;
			else
				r = mid;
		}
		if (cnt(r) >= k)
			return r;
		return l;
	}

	int cnt(int mid) {
		int ret = 0;
		for (int i = 0; i < weight.length; ++i)
			if (weight[i] >= mid)
				++ret;
		return ret;
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
