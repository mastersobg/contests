import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	int[][] keypad = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 },
			{ -1, 0, -1 } };

	boolean[] mays = new boolean[1000];

	boolean may(char[] x) {
		int prev = -1, prev_i = -1, prev_j = -1;
		for (int k = 0; k < x.length; k++) {
			int cur = x[k] - 48;
			if (prev == -1) {
				prev = cur;
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 3; j++) {
						if (keypad[i][j] == cur) {
							prev_i = i;
							prev_j = j;
						}
					}
				}
				continue;
			}

			int cur_i = 0, cur_j = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (keypad[i][j] == cur) {
						cur_i = i;
						cur_j = j;
					}
				}
			}

			if (cur_j < prev_j) {
				return false;
			}
			if (cur_i < prev_i) {
				return false;
			}

			prev_i = cur_i;
			prev_j = cur_j;
			prev = cur;
		}

		return true;
	}
	/*
	 3
	 180
	 83
	 132
	 */

	void solve() throws IOException {
		for (int i = 0; i < 1000; i++) {
			mays[i] = may(String.valueOf(i).toCharArray());
		}

		int t = ni();
		out: while (t-- > 0) {
			String k = ns();
			int ki = Integer.parseInt(k);

			int left = 0;
			for (int i = ki; i >= 0; i--) {
				if (may(String.valueOf(i).toCharArray())) {
					left = i;
					break;
				}
			}
			int right = 0;
			for (int i = ki; i < 1000; i++) {
				if (may(String.valueOf(i).toCharArray())) {
					right = i;
					break;
				}
			}
			
			if (Math.abs(ki - left) <= Math.abs(ki - right)) {
				out.println(left);
			} else {
				out.println(right);
			}
		}
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.parseInt(ns());
	}

	double nd() throws IOException {
		return Double.parseDouble(ns());
	}

	long nl() throws IOException {
		return Long.parseLong(ns());
	}

	Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		solve();
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new Main();
	}

}
