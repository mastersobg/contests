import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    Read file, console;
    PrintWriter out;

    final double eps = 1e-9;

    boolean eq(double a, double b) {
        return abs(a - b) < eps;
    }

	void solve() throws IOException {
        double value = console.nd();
        double ret = min(8.0, 2 * value);
        out.println(ret);
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
        file = new Read(new FileReader("trainingdata.txt"));
        console = new Read(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
        file.close();
        console.close();
		out.close();
	}


    class Read {
    
        BufferedReader in;
        StringTokenizer st;

        Read(Reader in) {
            this.in = new BufferedReader(in);
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

        void close() throws IOException {
            in.close();
        }
    }
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
}
