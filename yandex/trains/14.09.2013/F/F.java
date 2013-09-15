import java.io.*;
import java.util.*;
import java.math.*;

public class F implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	final double eps = 1e-8;

	class Pair implements Comparable<Pair> {
		int index;
		double value;

		public Pair(int index, double value) {
			this.index = index;
			this.value = value;
		}

		public int compareTo(Pair p) {
			return Double.compare(value, p.value);
		}
	}

	private void solve() throws IOException {
		int n = nextInt();
		double[] xs = new double[n], ys = new double[n], rs = new double[n];
		for (int i = 0; i < n; i++) {
			xs[i] = nextDouble();
			ys[i] = nextDouble();
			rs[i] = nextDouble();
		}
		int vertexes = n + 2, top = n, bottom = n + 1;
		boolean[][] g = new boolean[vertexes][vertexes];
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				g[i][j] = g[j][i] = intersect(xs[i], ys[i], rs[i], xs[j],
						ys[j], rs[j]);
			}
		}
		for (int i = 0; i < n; i++) {
			g[i][top] = g[top][i] = intersect(xs[i], ys[i], rs[i], xs[i],
					1000.0, 0);
			g[i][bottom] = g[bottom][i] = intersect(xs[i], ys[i], rs[i], xs[i],
					0.0, 0);
		}
		boolean[] used = new boolean[vertexes];
		dfs(top, vertexes, g, used);

		//out.println(Arrays.toString(used));
		if (used[bottom]) {
			out.println("-1");
		} else {
			List<Pair> leftIntersections = new ArrayList<Pair>();
			List<Pair> rightIntersections = new ArrayList<Pair>();

			for (int i = 0; i < n; i++) {
				if (intersect(xs[i], ys[i], rs[i], 0, ys[i], 0)) {
					double l = 0, r = ys[i];

					for (int it = 0; it < 64; it++) {
						double m = (l + r) * 0.5;
						if (distance(xs[i], ys[i], 0, m) < rs[i])
							r = m;
						else
							l = m;
					}
					leftIntersections.add(new Pair(i, (l + r) * 0.5));
				}

				if (intersect(xs[i], ys[i], rs[i], 1000.0, ys[i], 0)) {
					double l = 0, r = ys[i];

					for (int it = 0; it < 64; it++) {
						double m = (l + r) * 0.5;
						if (distance(xs[i], ys[i], 1000.0, m) < rs[i])
							r = m;
						else
							l = m;
					}
					rightIntersections.add(new Pair(i, (l + r) * 0.5));
				}
			}

			Collections.sort(leftIntersections);
			Collections.sort(rightIntersections);

			double x0 = 0.0, y0 = 1000.0, x1 = 1000.0, y1 = 1000.0;

			for (Pair p : leftIntersections) {
				if (used[p.index]) {
					y0 = p.value;
					break;
				}
			}
			for (Pair p : rightIntersections) {
				if (used[p.index]) {
					y1 = p.value;
					break;
				}
			}

			out.printf(Locale.US, "%.2f %.2f %.2f %.2f\n", x0, y0, x1, y1);
		}
	}

	private void dfs(int u, int vertexes, boolean[][] g, boolean[] used) {
		used[u] = true;
		for (int v = 0; v < vertexes; v++) {
			if (g[u][v] && !used[v]) {
				dfs(v, vertexes, g, used);
			}
		}
	}

	private boolean intersect(double x0, double y0, double r0, double x1,
			double y1, double r1) {
		return distance(x0, y0, x1, y1) < r0 + r1 + eps;
	}

	private double distance(double x0, double y0, double x1, double y1) {
		double dx = x0 - x1, dy = y0 - y1;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static void main(String[] args) {
		new F().run();
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