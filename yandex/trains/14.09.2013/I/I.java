import java.io.*;
import java.util.*;
import java.math.*;

public class I implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		while (solveOne())
			;
	}

	class Edge {
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

	}

	private boolean solveOne() throws IOException {
		int n = nextInt(), m = nextInt();
		if (n == 0 && m == 0)
			return false;
		Edge[] edges = new Edge[m];
		for (int i = 0; i < m; i++) {
			edges[i] = new Edge(nextInt(), nextInt(), nextInt());
		}
		Arrays.sort(edges, new Comparator<Edge>() {
			public int compare(Edge a, Edge b) {
				return Integer.compare(a.cost, b.cost);
			}
		});
		int[] dsu = new int[n];
		for (int i = 0; i < n; i++)
			dsu[i] = i;
		long result = (n == 1 ? 0 : Long.MAX_VALUE);
		int remain = n - 1;
		for (int i = 0; i < m && remain > 0; i++) {
			int u = dsuFind(dsu, edges[i].u), v = dsuFind(dsu, edges[i].v);
			if (u != v) {
				--remain;
				dsuMerge(dsu, u, v);
				if (remain == 0) {
					result = edges[i].cost;
				}
			}
		}
		out.println(result == Long.MAX_VALUE ? "IMPOSSIBLE" : result);
		return true;
	}

	private void dsuMerge(int[] dsu, int u, int v) {
		dsu[u] = v;
	}

	private int dsuFind(int[] dsu, int u) {
		if (dsu[u] == u)
			return u;
		return dsu[u] = dsuFind(dsu, dsu[u]);
	}

	public static void main(String[] args) {
		new I().run();
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