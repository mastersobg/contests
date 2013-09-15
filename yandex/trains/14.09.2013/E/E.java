import java.io.*;
import java.util.*;
import java.math.*;

public class E implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	final int maxDiff = 4;
	final long inf = Long.MAX_VALUE / 8;

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
		int x0 = 0, y0 = 0, x1 = Integer.parseInt(line), y1 = nextInt();

		long result = getAdd(x0, y0, x1, y1);

		for (int dx0 = -maxDiff; dx0 <= maxDiff; dx0++) {
			for (int dy0 = -maxDiff; dy0 <= maxDiff; dy0++) {
				for (int dx1 = -maxDiff; dx1 <= maxDiff; dx1++) {
					for (int dy1 = -maxDiff; dy1 <= maxDiff; dy1++) {

						long nx0 = x0 + dx0, ny0 = y0 + dy0, nx1 = x1 + dx1, ny1 = y1
								+ dy1;
						long curResult = d[dx0 + maxDiff][dy0 + maxDiff]
								+ d[dx1 + maxDiff][dy1 + maxDiff]
								+ getAdd(nx0, ny0, nx1, ny1);

						result = Math.min(result, curResult);

					}
				}
			}
		}

		out.println(result);

		return true;
	}

	private long getAdd(long x0, long y0, long x1, long y1) {
		x1 -= x0;
		y1 -= y0;
		x1 = Math.abs(x1);
		y1 = Math.abs(y1);
		x0 = 0;
		y0 = 0;

		if (y1 > x1) {
			long t = x1;
			x1 = y1;
			y1 = t;
		}

		long result = 0;

		{
			long diff = x1 - y1;
			long can = Math.min(diff, y1);
			x1 -= 2 * can;
			y1 -= can;
			result += can;
		}

		if (x1 == y1) {
			result += solveSquare(x0, y0, x1, y1);
		} else {
			if (x1 != 0 && y1 != 0)
				throw new AssertionError();
			long diff = Math.abs(x1 - y1);
			if (diff % 4 != 0) {
				return inf;
			} else {
				result += diff / 2;
			}
		}

		return result;
	}

	private long solveSquare(long x0, long y0, long x1, long y1) {
		long dx = x1 - x0, dy = y1 - y0, result = inf;

		if (Math.abs(2 * dy - dx) % 3 == 0) {
			long b = (2 * dy - dx) / 3;
			long a = dy - 2 * b;
			if (2 * a + 1 * b != dx)
				throw new AssertionError();
			if (1 * a + 2 * b != dy)
				throw new AssertionError();
			result = Math.min(result, Math.abs(a) + Math.abs(b));
		}

		if (Math.abs(2 * dx - dy) % 3 == 0) {
			long a = (2 * dx - dy) / 3;
			long b = dx - 2 * a;
			if (2 * a + 1 * b != dx)
				throw new AssertionError();
			if (1 * a + 2 * b != dy)
				throw new AssertionError();
			result = Math.min(result, Math.abs(a) + Math.abs(b));
		}

		return result;
	}

	public static void main(String[] args) {
		new E().run();
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