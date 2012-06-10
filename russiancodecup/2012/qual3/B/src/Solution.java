import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	void solve() throws IOException {
		for (int t = ni(); t > 0; --t) {
			int m = ni();
			int xs = ni();
			int ys = ni();
			int xf = ni();
			int yf = ni();
			int a = abs(xf - xs) + abs(yf - ys);
			if (a <= m)
				out.println("First 1");
			else
				out.println("Infinity");
		}
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
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
		new Solution();
	}
}
