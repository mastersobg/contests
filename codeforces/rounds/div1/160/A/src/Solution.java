import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
		// ssd
		Integer[] q = new Integer[ni()];
		for (int i = 0; i < q.length; ++i)
			q[i] = ni();
		Arrays.sort(q);
		Integer[] v = new Integer[ni()];
		for (int i = 0; i < v.length; ++i)
			v[i] = ni();
		int a = q[0];
		int ret = 0;
		v = sort(v);
		int cnt = 0;
		for (int i = 0; i < v.length; ++i) {
			if (cnt == a) {
				i += 1;
				cnt = 0;
			} else {
				++cnt;
				ret += v[i];
			}
		}
		out.println(ret);
	}

	Integer[] sort(Integer[] v) {
		Arrays.sort(v);
		Integer[] ret = new Integer[v.length];
		for (int i = 0, j = v.length - 1; i < v.length; ++i, --j)
			ret[i] = v[j];
		return ret;
	}

	public Solution() throws IOException {
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
		new Solution();
	}
}
