import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	static class Pair implements Comparable<Pair> {
		int t, w;

		public Pair(int t, int w) {
			super();
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(Pair o) {
			if (w != o.w)
				return o.w - w;
			return t - o.t;
		}
	}

	static class Cmp implements Comparator<Pair> {

		@Override
		public int compare(Pair o1, Pair o2) {
			return o1.t - o2.t;
		}

	}

	void solve() throws IOException {
		for (int t = ni(); t > 0; --t) {
			int n = ni();
			Pair[] v = new Pair[n];
			int mx = 0;
			for (int i = 0; i < n; ++i) {
				v[i] = new Pair(ni(), ni());
				mx = max(v[i].t, mx);
			}
			Arrays.sort(v, new Cmp());
			int idx = 0;
			PriorityQueue<Pair> q = new PriorityQueue<Solution.Pair>();
			long ret = 0;
			for (int curT = 0;; ++curT) {
				if (q.isEmpty() && idx == n)
					break;
				while (idx < n && v[idx].t == curT) {
					q.add(v[idx]);
					++idx;
				}
				if (q.isEmpty())
					continue;
				Pair p = q.poll();
				ret += (long) (curT - p.t) * (long) (p.w);
			}
			out.println(ret);
		}
	}

	void gen() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test.txt");
		out.println(3);
		for (int t = 0; t < 3; ++t) {
			int n = 100000 / 3;
			out.println(n);
			for (int i = 0; i < n; ++i) {
				out.println("1 1000000");
			}
		}
		out.close();
	}

	public Solution() throws IOException {
		// gen();
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		// in = new BufferedReader(new FileReader("test.txt"));
		out = new PrintWriter(System.out);
		// long ts = System.currentTimeMillis();
		solve();
		// System.err.println(System.currentTimeMillis() - ts);
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
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
