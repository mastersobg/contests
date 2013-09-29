import java.io.*;
import java.util.*;
import java.math.*;

public class Prime implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	final int border = 512;

	private void solve() throws IOException {
		List<Long> primes = new ArrayList<Long>();
		for (long i = 2; primes.size() <= border; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		int tests = nextInt();
		for (int test = 0; test < tests; test++) {
			solveOne(primes);
		}
	}

	private void solveOne(List<Long> primes) throws IOException {
		long n = nextLong();
		for (long prime : primes) {
			long i = n - prime;
			if (i >= 2 && isPrime(i)) {
				out.println(prime + " " + i);
				return;
			}
		}
		out.println("0 0");
	}

	// private List<Long> factorize(long n) {
	// List<Long> result = new ArrayList<Long>();
	// for (long i = 2; i * i <= n; i++) {
	// while (n % i == 0) {
	// result.add(i);
	// n /= i;
	// }
	// }
	// if (n > 1) {
	// result.add(n);
	// }
	// Collections.sort(result);
	// return result;
	// }

	private boolean isPrime(long n) {
		return BigInteger.valueOf(n).isProbablePrime(12);
	}

	public static void main(String[] args) {
		// final boolean oldChecker = false;
		//
		// if (oldChecker)
		// new Thread(null, new Template(), "yarrr", 1 << 24).start();
		// else
		new Prime().run();
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