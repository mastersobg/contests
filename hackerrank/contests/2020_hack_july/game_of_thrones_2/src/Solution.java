import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    final long mod = 1000000007L;
    long []fact, ifact;

	void solve() throws IOException {
        fact = fact();
        ifact = ifact();
        char []s = ns().toCharArray();
        int []letters = new int[26];
        for (int i = 0; i < s.length; ++i) {
            letters[s[i] - 'a']++;
        }
        long ret = 1L;
        int total = 0;
        for (int i = 0; i < letters.length; ++i) {
            int cnt = letters[i] >> 1;
            if (cnt > 0) {
                total += cnt;
                ret = (ret * fact[total]) % mod;
                ret = (ret * ifact[cnt]) % mod;
                ret = (ret * ifact[total - cnt]) % mod;
            }
        }
        out.println(ret);
	}


    long []fact() {
        long []f = new long[50001];
        f[0] = 1;
        for (int i = 1; i < f.length; ++i)
            f[i] = (f[i - 1] * i) % mod;
        return f;
    }

    long []ifact() {
        long []ret = new long[50001];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = pow(fact[i], (int) (mod - 2));
        }
        return ret;
    }

    long pow(long a, int p) {
        long ret = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                ret = (ret * a) % mod;
            }
            a = (a * a) % mod;
            p >>= 1;
        }
        return ret;
    }


	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
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

	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
}
