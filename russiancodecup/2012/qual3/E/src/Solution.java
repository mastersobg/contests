import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	final int t = 239;
	final int mod = (int) (1e+9 + 7);

	int hash(long mask, int n) {
		int ret = 0;
		for (int i = n - 1; i >= 0; --i) {
			ret = (int) (((long) ret * (long) t) % (long) mod);
			int bit = (mask & (1L << (long) i)) != 0 ? 1 : 0;
			ret = (ret + bit) % mod;
		}
		return ret;
	}

	int n;
	long m;
	int hash;

	LinkedList<Integer> diff(long a, long b, int n) {
		LinkedList<Integer> ret = new LinkedList<Integer>();
		for (int i = 0; i < n; ++i) {
			int bit1 = (a & (1L << (long) i)) != 0 ? 1 : 0;
			int bit2 = (b & (1L << (long) i)) != 0 ? 1 : 0;
			if (bit1 != bit2)
				ret.add(i);
		}
		return ret;
	}

	int pow(int power) {
		int ret = 1;
		for (int i = 0; i < power; ++i) {
			ret = (int) (((long) ret * (long) t) % (long) mod);
		}
		return ret;
	}

	void solve() throws IOException {
		for (int q = ni(); q > 0; --q) {
			n = ni();
			m = nl();
			hash = ni();
			if (n == 1) {
				boolean solved = false;
				for (int i = 0; i < 2; ++i) {
					int h = hash(i, n);
					if (h == hash) {
						if (m == i) {
							out.println(0);
						} else {
							out.println("1 0");
						}
						solved = true;
					}
				}
				if (!solved)
					out.println(-1);
				continue;
			}

			int mid = n >> 1;
			int board = 1 << mid;
			HashMap<Integer, LinkedList<Integer>> map = new HashMap<Integer, LinkedList<Integer>>();
			for (int i = 0; i < board; ++i) {
				int h = hash(i, mid);
				LinkedList<Integer> a = map.get(h);
				LinkedList<Integer> b = diff(m, i, mid);
				if (a == null || b.size() < a.size())
					map.put(h, b);
			}
			final int power = pow(mid);
			int oldMid = mid;
			mid += (n & 1) == 1 ? 1 : 0;
			board = 1 << mid;
			LinkedList<Integer> ret1 = null, ret2 = null;
			for (int i = 0; i < board; ++i) {
				int h = hash(i, mid);
				h = (int) (((long) h * (long) power) % (long) mod);
				h = (int) (((long) hash - (long) h + (long) mod) % (long) mod);
				LinkedList<Integer> list = map.get(h);
				if (list != null) {
					LinkedList<Integer> l2 = diff(m >> oldMid, i, mid);
					if (ret1 == null || ret2 == null) {
						ret1 = list;
						ret2 = l2;
					} else {
						int s1 = ret1.size() + ret2.size();
						int s2 = list.size() + l2.size();
						if (s2 < s1) {
							ret1 = list;
							ret2 = l2;
						}
					}
				}
			}
			if (ret1 == null || ret2 == null)
				out.println(-1);
			else {
				out.print(ret1.size() + ret2.size());
				for (int value : ret1)
					out.print(" " + value);
				for (int value : ret2)
					out.print(" " + (value + oldMid));
				out.println();
			}
		}
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
		try {
			new Solution();
		} catch (NullPointerException e) {
			// TODO: handle exception
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		} catch (ArithmeticException e) {
			// TODO: handle exception
		} catch (RuntimeException e) {
			// TODO: handle exception
		}
	}
}
