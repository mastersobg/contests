import java.io.*;
import java.util.*;
import java.math.*;

public class G implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	final char free = '.';
	final int inf = Integer.MAX_VALUE / 2;

	class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	private void solve() throws IOException {
		int n = nextInt(), m = nextInt();
		char[][] field = new char[n][m];
		for (int i = 0; i < n; i++) {
			String line = nextToken();
			for (int j = 0; j < m; j++) {
				field[i][j] = line.charAt(j);
			}
		}
		int[][] earliestFire = new int[n][m];
		for (int i = 0; i < n; i++)
			Arrays.fill(earliestFire[i], inf);
		Queue<Pair> queue = new ArrayDeque<G.Pair>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (field[i][j] == 'F') {
					queue.add(new Pair(i, j));
					earliestFire[i][j] = 0;
				}
			}
		}
		final int[] dx = { 1, -1, 0, 0 };
		final int[] dy = { 0, 0, 1, -1 };
		while (queue.size() > 0) {
			Pair cur = queue.poll();
			for (int k = 0; k < dx.length; k++) {
				int nx = cur.x + dx[k], ny = cur.y + dy[k];
				if (nx >= 0 && ny >= 0 && nx < n && ny < m
						&& field[nx][ny] == free && earliestFire[nx][ny] == inf) {
					earliestFire[nx][ny] = earliestFire[cur.x][cur.y] + 1;
					queue.add(new Pair(nx, ny));
				}
			}
		}
		int[][] exitTime = new int[n][m];
		for (int i = 0; i < n; i++)
			Arrays.fill(exitTime[i], inf);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (field[i][j] == 'J') {
					exitTime[i][j] = 0;
					queue.add(new Pair(i, j));
				}
			}
		}
		while (queue.size() > 0) {
			Pair cur = queue.poll();
			for (int k = 0; k < dx.length; k++) {
				int nx = cur.x + dx[k], ny = cur.y + dy[k];
				int newTime = exitTime[cur.x][cur.y] + 1;
				if (nx >= 0 && ny >= 0 && nx < n && ny < m
						&& field[nx][ny] == free
						&& earliestFire[nx][ny] > newTime
						&& exitTime[nx][ny] == inf) {
					exitTime[nx][ny] = newTime;
					queue.add(new Pair(nx, ny));
				}
			}
		}
		int result = inf;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || j == 0 || i == n - 1 || j == m - 1)
					result = Math.min(result, exitTime[i][j] + 1);
			}
		}
		out.println(result == inf ? "IMPOSSIBLE" : result);

	}

	public static void main(String[] args) {
		new G().run();
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