import java.io.*;
import java.util.*;
import java.math.*;

public class Dhopper implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		int maxDistance = nextInt();
		String s0 = nextToken(), s1 = nextToken();
		int n = s0.length();
		List<Integer>[] edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<Integer>();
			for (int j = 0; j < n; j++) {
				if (s0.charAt(i) == s1.charAt(j)) {
					int distance = getDistance(n, i, j);
					if (distance <= maxDistance) {
						edges[i].add(j);
					}
				}
			}
			//out.println(edges[i]);
			Collections.shuffle(edges[i]);
		}
		boolean ok = true;
		boolean[] used = new boolean[n];
		int[] mt = new int[n];
		Arrays.fill(mt, -1);
		for (int i = 0; i < n && ok; i++) {
			Arrays.fill(used, false);
			if (!dfs(i, edges, used, mt)) {
				ok = false;
			}
		}
		out.println(ok ? "YES" : "NO");

	}

	private boolean dfs(int u, List<Integer>[] edges, boolean[] used, int[] mt) {
		if (used[u]) {
			return false;
		}

		used[u] = true;

		for (int v : edges[u]) {
			if (mt[v] == -1) {
				mt[v] = u;
				return true;
			}
		}

		for (int v : edges[u]) {
			if (dfs(mt[v], edges, used, mt)) {
				mt[v] = u;
				return true;
			}
		}

		return false;
	}

	private int getDistance(int n, int i, int j) {
		return Math.min(Math.abs(i - j),
				Math.min(Math.abs(n - j + i), Math.abs(n - i + j)));
	}

	public static void main(String[] args) {
		// final boolean oldChecker = false;
		//
		// if (oldChecker)
		// new Thread(null, new Template(), "yarrr", 1 << 24).start();
		// else
		new Dhopper().run();
	}

	public void run() {
		try {
			final String className = this.getClass().getName().toLowerCase();

			try {
				in = new BufferedReader(new FileReader(className + ".in"));
				out = new PrintWriter(new FileWriter(className + ".out"));
				// in = new BufferedReader(new FileReader("input.txt"));
				// out = new PrintWriter(new FileWriter("output.txt"));
			} catch (FileNotFoundException e) {
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