import java.io.*;
import java.util.*;
import java.math.*;

public class Training implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		int tests = nextInt();
		for(int test = 0; test < tests; test++)
			solveOne();
	}

	private void solveOne() throws IOException {
		int n = nextInt();
		out.println(rec(0, n, new int[n], new boolean[n]) / 2);
	}

	private int rec(int step, int n, int[] h, boolean[] u) {
		if(step == n) {
			return good(n, h) ? 1 : 0;
		} else {
			int result = 0;
			for(int i = 0; i < n; i++) {
				if(!u[i]) {
					u[i] = true;
					h[step] = i;
					result += rec(step + 1, n, h, u);
					u[i] = false;
				}
			}
			return result;
		}
	}

	private boolean good(int n, int[] h) {
		for(int i = 1; i < n - 1; i++) {
			if(h[i] > h[i - 1] && h[i] > h[i + 1])
				;
			else if(h[i] < h[i - 1] && h[i] < h[i + 1])
				;
			else
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new Training().run();
	}

	public void run() {
		try {
			final String className = this.getClass().getName().toLowerCase();
			try {
				in = new BufferedReader(new FileReader(className + ".in"));
				out = new PrintWriter(new FileWriter(className + ".out"));
			} catch(FileNotFoundException e) {
				in = new BufferedReader(new InputStreamReader(System.in));
				out = new PrintWriter(System.out);
			}

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
