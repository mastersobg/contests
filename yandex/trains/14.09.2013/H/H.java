import java.io.*;
import java.util.*;
import java.math.*;

public class H implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		while (solveOne())
			;
	}

	private boolean solveOne() throws IOException {
		int n = nextInt();
		if (n == 0)
			return false;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = nextInt();
		Arrays.sort(arr);
		boolean ok = (arr[0] <= 200);

		if (Math.abs(1422 - arr[n - 1]) * 2 > 200)
			ok = false;

		for (int i = 1; i < n; i++) {
			if (arr[i] - arr[i - 1] > 200)
				ok = false;
		}

		out.println(ok ? "POSSIBLE" : "IMPOSSIBLE");

		return true;
	}

	public static void main(String[] args) {
		new H().run();
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