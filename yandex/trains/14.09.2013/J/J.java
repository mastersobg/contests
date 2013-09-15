import java.io.*;
import java.util.*;
import java.math.*;

public class J implements Runnable {
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer st;
	static Random rnd;

	private void solve() throws IOException {
		while (solveOne())
			;
	}

	private boolean solveOne() throws IOException {
		String line = in.readLine();
		if (line.equals("0"))
			return false;
		String[] words = line.split(" ");
		StringBuilder text = new StringBuilder();
		for (String word : words)
			text.append(word);
		solveForText(text.toString());
		return true;
	}

	class StringHash {
		final long p = 43, pRev = BigInteger.valueOf(p)
				.modInverse(BigInteger.valueOf(2).pow(64)).longValue();
		private long[] hashes, revs;

		public StringHash(String s) {
			hashes = new long[s.length() + 1];
			revs = new long[s.length() + 1];
			long mod = 1;
			revs[0] = 1;
			for (int i = 1; i <= s.length(); i++) {
				long code = s.charAt(i - 1) - 'A' + 1;
				hashes[i] = hashes[i - 1] + code * mod;
				mod *= p;
				revs[i] = revs[i - 1] * pRev;
			}
		}

		public long get(int l, int r) {
			return (hashes[r] - hashes[l]) * revs[l];
		}
	}

	private void solveForText(String s) {
		StringHash sh = new StringHash(s);
		for (int len = 1; len <= s.length(); len++) {
			int result = 1;
			Map<Long, Integer> count = new HashMap<Long, Integer>();
			for (int i = 0; i + len <= s.length(); i++) {
				long hash = sh.get(i, i + len);
				Integer before = count.get(hash);
				if (before == null)
					before = 0;
				before += 1;
				count.put(hash, before);
				result = Math.max(result, before);
			}
			out.println(result);
			if (result == 1)
				break;
		}
	}

	public static void main(String[] args) {
		new J().run();
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