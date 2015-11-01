import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	void solve() throws IOException {
		int t = ni();

		while (t-- > 0) {
			char[] s = in.readLine().toCharArray();
			Set<Integer> exist = new HashSet<Integer>();
			for (int i = 0; i < s.length; i++) {
				exist.add((int) s[i] - 48);
			}
			StringBuilder res = new StringBuilder();
			boolean was = false;
			for (int i = 0; i <= 9; i++) {
				if (!exist.contains(i)) {
					res.append(i);
					was = true;
				}
			}
			if (was) {
				out.println(res);
			} else {
				out.println("allo");
			}
		}
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.parseInt(ns());
	}

	double nd() throws IOException {
		return Double.parseDouble(ns());
	}

	long nl() throws IOException {
		return Long.parseLong(ns());
	}

	Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		solve();
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new Main();
	}

}
