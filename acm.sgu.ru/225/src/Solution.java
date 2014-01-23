import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	int[][] m2id;
	int[][] id2m;

	int n, k;
	long[][][] dp;
	int[][] move;
	int[] tmp = new int[100000];

	int[] precalc(int id) {
		int size = 0;
		int m1 = id2m[0][id];
		int m2 = id2m[1][id];
		int mask = m1 << 1 | m1 >> 1 | m2 << 2 | m2 >> 2;
		for (int i = 0; i < (1 << n); ++i) {
			if ((i & mask) == 0) {
				tmp[size++] = i;
			}
		}
		int[] ret = new int[size];
		for (int i = 0; i < size; ++i) {
			ret[i] = tmp[i];
		}
		return ret;
	}

	long[][] precalc = {
			{ 1L, 1L, },
			{ 1L, 4L, 6L, 4L, 1L, },
			{ 1L, 9L, 28L, 36L, 18L, 2L, 0L, 0L, 0L, 0L, },
			{ 1L, 16L, 96L, 276L, 412L, 340L, 170L, 48L, 6L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, },
			{ 1L, 25L, 252L, 1360L, 4436L, 9386L, 13384L, 12996L, 8526L, 3679L,
					994L, 158L, 15L, 1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, },
			{ 1L, 36L, 550L, 4752L, 26133L, 97580L, 257318L, 491140L, 688946L,
					716804L, 556274L, 323476L, 141969L, 47684L, 12488L, 2560L,
					393L, 40L, 2L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, },
			{ 1L, 49L, 1056L, 13340L, 111066L, 649476L, 2774728L, 8891854L,
					21773953L, 41310504L, 61412928L, 72304650L, 68233368L,
					52400729L, 33376132L, 18001362L, 8363763L, 3374053L,
					1178064L, 351592L, 87913L, 17910L, 2858L, 336L, 26L, 1L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, },
			{ 1L, 64L, 1848L, 32084L, 376560L, 3184708L, 20202298L, 98796304L,
					379978716L, 1167053680L, 2897726604L, 5876860140L,
					9825415557L, 13660238780L, 15932672964L, 15737653004L,
					13304668385L, 9742722088L, 6260518246L, 3574590840L,
					1830733371L, 844203844L, 349524138L, 128874944L, 41833846L,
					11792736L, 2840224L, 572432L, 93840L, 12004L, 1122L, 68L,
					2L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, },
			{ 1L, 81L, 3016L, 68796L, 1080942L, 12472084L, 110018552L,
					762775440L, 4241252429L, 19206532478L, 71707869632L,
					222946143752L, 582155146204L, 1286247689414L,
					2421159140764L, 3908273840366L, 5446391581062L,
					6599640204257L, 7010436668992L, 6589213734278L,
					5537849837497L, 4207779106033L, 2920161348852L,
					1865346129716L, 1101125592067L, 600730512987L,
					302041066250L, 139345014744L, 58692638521L, 22451454400L,
					7755194754L, 2403337080L, 663103709L, 161373907L,
					34237130L, 6238414L, 957145L, 120334L, 11914L, 872L, 42L,
					1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, },
			{ 1L, 100L, 4662L, 135040L, 2732909L, 41199404L, 481719518L,
					4491423916L, 34075586550L, 213628255072L, 1120204619108L,
					4961681221524L, 18715619717199L, 60541371615660L,
					168976761361446L, 409191804533576L, 864172675710439L,
					1599730843649564L, 2609262108838924L, 3770687313420780L,
					4857550050070531L, 5616928666465104L, 5874943705896600L,
					5604501518609804L, 4917655076255841L, 3999855946779732L,
					3034690618677388L, 2156485957257040L, 1437827591264317L,
					899278231344296L, 526753407546620L, 288274613750624L,
					146990556682887L, 69626509814580L, 30542906352994L,
					12366448408056L, 4604442057431L, 1569983914256L,
					487876545370L, 137395261280L, 34831261750L, 7884855000L,
					1578162590L, 275861904L, 41455966L, 5246412L, 543534L,
					44244L, 2652L, 104L, 2L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
					0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, }, };

	void solve() throws IOException {
		n = ni();
		k = ni();
		out.println(precalc[n - 1][k]);
	}

	void calc() {
		for (n = 1; n <= 10; ++n) {
			out.print("{");
			for (k = 0; k <= n * n; ++k) {
				if (k > 50) {
					out.print("0L, ");
					continue;
				}
				Timer timer = new Timer();
				timer.start();
				m2id = new int[1 << n][1 << n];
				id2m = new int[2][30000];
				int id = 0;
				move = new int[30000][];
				for (int m1 = 0; m1 < (1 << n); ++m1) {
					for (int m2 = 0; m2 < (1 << n); ++m2) {
						if (check(m1, m2)) {
							m2id[m1][m2] = id;
							id2m[0][id] = m1;
							id2m[1][id] = m2;
							move[id] = precalc(id);
							++id;
						}
					}
				}
				int[] bits = new int[1 << n];
				for (int i = 0; i < (1 << n); ++i)
					bits[i] = Integer.bitCount(i);

				dp = new long[2][k + 1][id];
				dp[0][0][0] = 1L;
				int cur = 0;
				for (int i = 0; i < n; ++i) {
					int next = cur ^ 1;
					for (int j = 0; j <= k; ++j)
						Arrays.fill(dp[next][j], 0);
					for (int j = 0; j <= k; ++j) {
						for (int q = 0; q < id; ++q) {
							if (dp[cur][j][q] > 0) {
								for (int mv = 0; mv < move[q].length; ++mv) {
									int m3 = move[q][mv];
									int m2 = id2m[1][q];
									int id1 = m2id[m2][m3];
									if (j + bits[m3] <= k) {
										dp[next][j + bits[m3]][id1] += dp[cur][j][q];
									}
								}

							}
						}
					}
					cur = next;
				}
				long ret = 0;
				for (int i = 0; i < id; ++i) {
					ret += dp[cur][k][i];
				}
				out.print(ret + "L, ");
			}
			out.println("},");
			out.flush();
		}

		// timer.print();
	}

	void go(int cur, int next, int set, int id, int col, int add, int mask) {
		if (col == n) {
			int m2 = id2m[1][id];
			int id1 = m2id[m2][mask];
			dp[next][set + add][id1] += dp[cur][set][id];
			return;
		}
		go(cur, next, set, id, col + 1, add, mask);
		int m1 = id2m[0][id];
		int m2 = id2m[1][id];
		if (set + add < k && !checkBit(m1, col - 1) && !checkBit(m1, col + 1)
				&& !checkBit(m2, col - 2) && !checkBit(m2, col + 2)) {
			go(cur, next, set, id, col + 1, add + 1, mask | (1 << col));
		}
	}

	boolean check(int m1, int m2) {
		for (int i = 0; i < n; ++i) {
			if (checkBit(m1, i)) {
				if (checkBit(m2, i - 2) || checkBit(m2, i + 2)) {
					return false;
				}
			}
		}
		return true;
	}

	boolean checkBit(int m, int bit) {
		if (bit >= n || bit < 0)
			return false;
		return (m & (1 << bit)) != 0;
	}

	public void run() throws IOException {
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
		new Solution().run();
	}

	class Timer {

		long time;

		void start() {
			time = System.currentTimeMillis();
		}

		long time() {
			return System.currentTimeMillis() - time;
		}

		void print() {
			print("Time spent = ");
		}

		void print(String message) {
			dbg(message, time());
		}

	}

	static boolean DEBUG = true;

	void dbg(Object... objs) {
		if (!DEBUG) {
			return;
		}
		for (Object o : objs) {
			String printLine;
			if (o.getClass().isArray()) {
				printLine = arrayToString(o);
			} else {
				printLine = o.toString();
			}
			System.err.print(printLine + " ");
		}
		System.err.println();
	}

	String arrayToString(Object o) {
		if (o instanceof long[])
			return Arrays.toString((long[]) o);
		if (o instanceof int[])
			return Arrays.toString((int[]) o);
		if (o instanceof short[])
			return Arrays.toString((short[]) o);
		if (o instanceof char[])
			return Arrays.toString((char[]) o);
		if (o instanceof byte[])
			return Arrays.toString((byte[]) o);
		if (o instanceof double[])
			return Arrays.toString((double[]) o);
		if (o instanceof float[])
			return Arrays.toString((float[]) o);
		if (o instanceof boolean[])
			return Arrays.toString((boolean[]) o);
		if (o instanceof Object[])
			return Arrays.deepToString((Object[]) o);
		throw new IllegalStateException();
	}

}
