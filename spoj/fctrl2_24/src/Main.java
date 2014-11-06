import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import java.math.BigInteger;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        for (int t = ni(); t > 0; --t) {
            int n = ni();
            BigInteger ret = BigInteger.ONE;
            for (int i = 1; i <= n; ++i) {
                ret = ret.multiply(BigInteger.valueOf(i));
            }
            out.println(ret);
        }
	}
	
	public Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
        out.flush();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}
	public static void main(String[] args) throws IOException {
		new Main();
	}
}
