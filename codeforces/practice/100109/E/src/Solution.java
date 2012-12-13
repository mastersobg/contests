import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int n;
	boolean[] who;
	int[] value;
	int dragons = 0;

	Dragon[] dv;

	void solve() throws IOException {
		long start = System.currentTimeMillis();
		n = ni() - 1;
		who = new boolean[n];
		value = new int[n];
		dv = new Dragon[n];
		for (int i = 0; i < n; ++i) {
			if (ns().charAt(0) == 'd') {
				who[i] = true;
				++dragons;
				dv[i] = new Dragon(i, ni());
			} else {
				value[i] = ni();
			}
		}
		int l = value[n - 1];
		int r = dragons;
		killed = new int[dragons];
		if (l > r) {
			out.println(-1);
			return;
		}
		while (l + 1 < r) {
			int mid = (l + r) >> 1;
			int cnt = count(mid, false);
			if (cnt == -1)
				r = mid;
			else
				l = mid;
		}
		if (count(r, true) != -1) {
			out.println(gold);
			out.println(killedSize);
			Arrays.sort(killed, 0, killedSize);
			for (int i = 0; i < killedSize; ++i)
				out.print((killed[i] + 2) + " ");
		} else if (count(l, true) != -1) {
			out.println(gold);
			out.println(killedSize);
			Arrays.sort(killed, 0, killedSize);
			for (int i = 0; i < killedSize; ++i)
				out.print((killed[i] + 2) + " ");
		} else {
			out.println(-1);
		}
		// System.err.println(System.currentTimeMillis() - start);
	}

	static class Dragon implements Comparable<Dragon> {
		int idx, value;

		public Dragon(int idx, int value) {
			super();
			this.idx = idx;
			this.value = value;
		}

		@Override
		public int compareTo(Dragon o) {
			return o.value - value;
		}
	}

	int gold;
	int[] killed;
	int killedSize = 0;
	PriorityQueue<Dragon> q = new PriorityQueue<Solution.Dragon>();

	int count(int kill, boolean ret) {
		gold = 0;
		killedSize = 0;
		q.clear();
		for (int i = n - 2; i >= 0; --i) {
			if (!who[i]) {
				while (kill >= value[i] && q.size() > 0) {
					if (ret) {
						Dragon d = q.poll();
						gold += d.value;
						killed[killedSize++] = d.idx;
					} else
						q.poll();
					--kill;
				}
				if (kill >= value[i])
					return -1;
			} else {
				q.add(dv[i]);
			}
		}
		if (ret) {
			while (kill > 0 && q.size() > 0) {
				--kill;
				Dragon d = q.poll();
				gold += d.value;
				killed[killedSize++] = d.idx;
			}
		}
		return 0;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		// generate();
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

	void generate() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test.txt");
		int n = 200000;
		out.println(n);
		for (int i = 1; i < n - 1; ++i) {
			out.println("d 10000");
		}
		out.println("p 1");
		out.close();
	}
}
