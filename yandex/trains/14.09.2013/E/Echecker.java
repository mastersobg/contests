import java.io.*;
import java.util.*;
import java.math.*;

public class Echecker implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	final int maxDiff = 2048;

	private void solve() throws IOException {
		int[][] d = new int[maxDiff + maxDiff + 1][maxDiff + maxDiff + 1];

		final int inf = Integer.MAX_VALUE / 2;
		for (int i = 0; i < maxDiff + maxDiff + 1; i++)
			Arrays.fill(d[i], inf);

		d[maxDiff][maxDiff] = 0;
		Queue<Pair> q = new ArrayDeque<Pair>();
		q.add(new Pair(maxDiff, maxDiff));

		final int[] dx = { 1, -1, 1, -1, 2, -2, 2, -2 };
		final int[] dy = { 2, 2, -2, -2, 1, 1, -1, -1 };

		while (q.size() > 0) {
			Pair cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur.x + dx[i], ny = cur.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx <= maxDiff + maxDiff
						&& ny <= maxDiff + maxDiff && d[nx][ny] == inf) {
					d[nx][ny] = d[cur.x][cur.y] + 1;
					q.add(new Pair(nx, ny));
				}
			}
		}

		while (solveOne(d))
			;
	}

	class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	private boolean solveOne(int[][] d) throws IOException {
		String line = nextToken();
		if (line.equals("END"))
			return false;
		int x0 = maxDiff, y0 = maxDiff, x1 = maxDiff + Integer.parseInt(line), y1 = maxDiff
				+ nextInt();

		long result = d[y1][x1];

		out.println(result);

		return true;
	}

	public static void main(String[] args) {
		new Echecker().run();
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