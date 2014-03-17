import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Program implements Runnable {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	char[] s;
	int[] dp;

	int mod = 1000000000 + 7;
	int[] sum;
	int[] sub;

	int rec(int pos) {
		if (pos >= s.length)
			return 0;
		int ret = dp[pos];
		if (ret == -1) {
			rec(pos + 1);
			ret = sum[pos + 2];
			ret = (ret + 1) % mod;
			ret = ret - sub[s[pos] - 'a'];
			if (ret < 0)
				ret += mod;
			sum[pos] = (sum[pos + 1] + ret) % mod;
			sub[s[pos] - 'a'] = (sub[s[pos] - 'a'] + ret) % mod;
			dp[pos] = ret;
		}
		return ret;
	}

	String read() throws IOException {
		StringBuilder ret = new StringBuilder(in.readLine());
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < ret.length(); ++i)
			if (Character.isLetter(ret.charAt(i)))
				result.append(Character.toLowerCase(ret.charAt(i)));
		return result.toString();
	}

	void solve() throws IOException {
		s = read().toCharArray();
		dp = new int[s.length + 1];
		Arrays.fill(dp, -1);
		sum = new int[s.length + 10];
		sub = new int[26];
		rec(0);
		int ret = 0;
		for (int i = 0; i < s.length; ++i)
			ret = (ret + dp[i]) % mod;
		out.println(ret);
	}

	void gen() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test.txt");
		Random rnd = new Random();
		for (int i = 0; i < 100000; ++i) {
			int a = rnd.nextInt(26);
			out.print((char) (a + 'a'));
		}
		out.close();
	}

	public Program() throws IOException {

	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		Thread th = new Thread(null, new Program(), "", 1 << 26);
		th.start();
		th.join();
	}

	@Override
	public void run() {
		try {
			Locale.setDefault(Locale.US);
			in = new BufferedReader(new InputStreamReader(System.in));
			// gen();
			// in = new BufferedReader(new FileReader("test.txt"));

			out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (IOException e) {
		}
	}
}
