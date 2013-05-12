import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	class Attack implements Comparable<Attack> {
		int day;
		int l, r;
		int s;

		@Override
		public int compareTo(Attack o) {
			return day - o.day;
		}

		@Override
		public String toString() {
			return "Attack [day=" + day + ", l=" + l + ", r=" + r + ", s=" + s
					+ "]";
		}

	}

	void solve() throws IOException {
		int tribes = ni();
		ArrayList<Attack> attacks = new ArrayList<Solution.Attack>();
		for (int it = 0; it < tribes; ++it) {
			int d = ni();
			int n = ni();
			int l = ni(), r = ni();
			int s = ni();
			int dd = ni();
			int dp = ni();
			int ds = ni();
			for (int i = 0; i < n; ++i) {
				Attack attack = new Attack();
				attack.day = d;
				attack.l = l;
				attack.r = r;
				attack.s = s;

				d += dd;
				l += dp;
				r += dp;
				s += ds;
				attacks.add(attack);
			}
		}
		Collections.sort(attacks);
		int add = abs(_min(attacks));
		for (Attack a : attacks) {
			a.l += add;
			a.r += add;
		}
		// System.out.println(attacks);
		if (_max(attacks) < 0) {
			int k = 1;
			++k;
		}
		int[] arr = new int[_max(attacks) + 1];

		int previous = -1;
		int wall = 0;
		ArrayList<int[]> attacked = new ArrayList<int[]>();
		int ret = 0;
		int step = 0;
		for (Attack attack : attacks) {
			if (attack.day != previous) {
				for (int[] el : attacked) {
					for (int i = el[0]; i < el[1]; ++i)
						arr[i] = max(el[2], arr[i]);
				}
				wall = 0;
				attacked.clear();
			}
			previous = attack.day;
			wall = max(wall, attack.s);
			attacked.add(new int[] { attack.l, attack.r, attack.s });
			boolean succed = false;
			for (int i = attack.l; i < attack.r; ++i)
				if (arr[i] < attack.s) {
					succed = true;
				}
			if (succed) {
				ret++;
			}
			// System.out.println(step++ + " " + succed);
		}
		out.println(ret);
	}

	int _min(List<Attack> attacks) {
		int ret = Integer.MAX_VALUE;
		for (Attack a : attacks)
			ret = min(ret, a.l);
		return ret;
	}

	int _max(List<Attack> attacks) {
		int ret = Integer.MIN_VALUE;
		for (Attack a : attacks)
			ret = max(ret, a.r);
		return ret;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input-small-2.txt"));
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
