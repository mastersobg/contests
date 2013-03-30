import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	private static class Box {
		int idx;
		int h, w, l;

		public Box(int idx, int w, int l, int h) {
			this.idx = idx;
			this.h = h;
			this.w = min(w, l);
			this.l = max(w, l);
		}
	}

	int n;
	Box[] boxes;
	int[][] dp;

	int rec(int mask, int prev) {
		int ret = dp[prev][mask];
		if (ret == -1) {
			ret = 0;
			for (int i = 0; i < boxes.length; ++i) {
				if (!checkBit(mask, boxes[i].idx)) {
					if (canPut(boxes[prev], boxes[i])) {
						int nextMask = setBit(mask, boxes[i].idx);
						ret = max(ret, rec(nextMask, i) + boxes[i].h);
					}
				}
			}
			dp[prev][mask] = ret;
		}
		return ret;
	}

	private boolean canPut(Box prev, Box cur) {
		return prev.w >= cur.w && prev.l >= cur.l;
	}

	private int setBit(int mask, int bit) {
		return mask | (1 << bit);
	}

	private boolean checkBit(int mask, int bit) {
		return (mask & (1 << bit)) != 0;
	}

	void solve() throws IOException {
		n = ni();
		boxes = new Box[n * 3];
		int idx = 0;
		for (int i = 0; i < n; ++i) {
			int w = ni();
			int h = ni();
			int l = ni();
			boxes[idx++] = new Box(i, w, h, l);
			boxes[idx++] = new Box(i, w, l, h);
			boxes[idx++] = new Box(i, l, h, w);
		}
		dp = new int[boxes.length][1 << n];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		int ret = 0;
		for (int i = 0; i < boxes.length; ++i)
			ret = max(ret, rec(1 << boxes[i].idx, i) + boxes[i].h);
		out.println(ret);
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
}
