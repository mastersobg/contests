import java.io.*;
import java.util.*;
import java.math.*;

public class K implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		while (solveOne())
			;
	}

	class Pair implements Comparable<Pair> {
		double value;
		int index;

		public Pair(double value, int index) {
			this.value = value;
			this.index = index;
		}

		public int compareTo(Pair p) {
			return Double.compare(value, p.value);
		}

	}

	private boolean solveOne() throws IOException {
		int n = nextInt();
		if (n == 0)
			return false;

		Pair[] arr = new Pair[n];
		for (int i = 0; i < n; i++)
			arr[i] = new Pair(nextDouble(), i);

		boolean solved = false;
		int iteration = 0;
		boolean[] taked = new boolean[n];

		while (!solved) {
			mixArray(n, arr, iteration++);
			double left = 0, right = 0;

			Arrays.fill(taked, false);

			for (int i = 0; i < n; i++) {
				double value = arr[i].value;
				if (left < right) {
					left += value;
					taked[arr[i].index] = true;
				} else {
					right += value;
				}
			}

			double diff = Math.abs(left - right);
			double howMuch = diff / Math.min(left, right);
			double eps = 1e-8;

			if (howMuch < 0.02 + eps) {
				solved = true;
			}
		}

		for (int i = 0; i < n; i++) {
			if (taked[i]) {
				out.print((i + 1) + " ");
			}
		}
		out.println();

		return true;
	}

	private void mixArray(int n, Pair[] arr, int step) {
		if (step == 0) {
			Arrays.sort(arr);
			reverseArray(n, arr);
		} else {
			shuffleArray(n, arr);
		}
	}

	private void shuffleArray(int n, Pair[] arr) {
		for (int i = 1; i < n; i++) {
			int index = rnd.nextInt(i);
			Pair t = arr[i];
			arr[i] = arr[index];
			arr[index] = t;
		}
	}

	private void reverseArray(int n, Pair[] arr) {
		int l = 0, r = n - 1;
		while (r - l > 1) {
			Pair t = arr[l];
			arr[l] = arr[r];
			arr[r] = t;
			++l;
			--r;
		}
	}

	public static void main(String[] args) {
		new K().run();
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);

			rnd = new Random();

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = in.readLine();

			if (line == null)
				return null;

			st = new StringTokenizer(line);
		}

		return st.nextToken();
	}

	private int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	private long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	private double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}