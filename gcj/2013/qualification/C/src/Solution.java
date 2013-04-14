import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[] arr;

	void precalc() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 10000000; ++i) {
			if (check(get(i))) {
				long n = (long) i * (long) i;
				if (check(get(n))) {
					list.add(i);
				}
			}
		}
		arr = new int[list.size()];
		int idx = 0;
		for (Integer el : list)
			arr[idx++] = el;
	}

	String get(long n) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			sb.append(n % 10l);
			n /= 10l;
		}
		return sb.toString();
	}

	boolean check(String s) {
		for (int i = 0, j = s.length() - 1; i < j; ++i, --j)
			if (s.charAt(i) != s.charAt(j))
				return false;
		return true;
	}

	void solve() throws IOException {
		long a = nl(), b = nl();
		int L = (int) sqrt(a + 0.0);
		while ((long)L * (long)L < a)
			++L;
		int r = (int) sqrt(b + 0.0);
		int l = L;
		int ret = 0;
		for (int i = 0; i < arr.length; ++i)
			if (arr[i] >= l && arr[i] <= r)
				++ret;
		// int pl = search(l, true);
		// int pr = search(r, false);
		// int ret = max(0, pr - pl + 1);
		out.println(ret);
		out.flush();
	}

	int search(int el, boolean right) {
		int l = 0, r = arr.length - 1;
		while (l + 1 < r) {
			int mid = (l + r) >> 1;
			if (arr[mid] < el)
				l = mid;
			else if (arr[mid] > el)
				r = mid;
			else
				return mid;
		}
		return right ? r : l;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		precalc();
		int test = ni();
		for (int t = 1; t <= test; ++t) {
			out.print("Case #" + t + ": ");
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
