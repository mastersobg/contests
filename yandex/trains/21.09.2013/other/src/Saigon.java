import java.io.*;
import java.util.*;
import java.math.*;

public class Saigon implements Runnable {
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
		int m = nextInt(), n = nextInt();
		//out.println("second");
		//for(int m = 4; m <= 10; m++) {
			//for(int n = 3; n <= 100; n++) {
				BigInteger[][] cache = new BigInteger[n + 1][m + 1];
				out.println(f(n, m, cache));
		//	}
		//}
	}

	
	BigInteger f(int n, int m, BigInteger[][]cache) {
		if (cache[n][m] != null) {
			return cache[n][m];
		} else {
			if (m < 2) {
				return BigInteger.valueOf(Integer.MAX_VALUE);
			}
			if (n == 1) {
				return BigInteger.ONE;
			}
			BigInteger ret = null;
			for (int k = 1; k < n; ++k) {
				BigInteger a = f(k, m, cache).multiply(BigInteger.valueOf(2)).add(f(n - k, m - 1, cache));
				if (ret == null || a.compareTo(ret) < 0) {
					ret = a;
				}
			}
			cache[n][m] = ret;
			return ret;
		}
	}
	private BigInteger rec(int n, int m, int used, BigInteger[][][] cache) {
		if(cache[n][m][used] != null) {
			return cache[n][m][used];
		} else if(n < m - used) {
			return cache[n][m][used] = BigInteger.valueOf(2 * n - 1);
		} else {
			BigInteger result = null;
			for(int take = 1; take <= m - 2; take++) {
				int lol = (used == 1 ? 1 : 0);
				BigInteger current =rec(n - take, m, 1, cache).add(rec(n - take, m, lol, cache)).add(BigInteger.valueOf((2 * take - 1)));
				if(result == null || current.compareTo(result) < 0)
					result = current;
			}
			return cache[n][m][used] = result;
		}
	}

	public static void main(String[] args) {
		new Saigon().run();
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
