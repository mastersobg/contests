import java.io.*;
import java.util.*;
import java.math.*;

public class L implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		int n = nextInt();
		int[] arr = new int[n], all = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = nextInt();
			all[i] = arr[i];
		}
		Arrays.sort(all);
		for (int i = 0; i < n; i++) {
			arr[i] = Arrays.binarySearch(all, arr[i]) + 1;
		}
		int[] tree = new int[n + 1];
		long result = 0;
		for (int i = 0; i < n; i++) {
			int id = arr[i];
			if (id < 0 || id > n)
				throw new AssertionError();
			id = n - id + 1;
			result += getSum(tree, id);
			add(tree, id, n);
		}
		out.println(result);
	}

	private void add(int[] tree, int id, int n) {
		while (id <= n) {
			tree[id]++;
			id += (id & (-id));
		}
	}

	private int getSum(int[] tree, int id) {
		int result = 0;
		while (id > 0) {
			result += tree[id];
			id -= (id & (-id));
		}
		return result;
	}

	public static void main(String[] args) {
		new L().run();
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