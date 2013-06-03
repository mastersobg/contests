import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    static class Pair implements Comparable<Pair> {
        int idx, value;

        public int compareTo(Pair p) {
            if(value != p.value)
                return value - p.value;
            return idx - p.idx;
        }

        @Override
        public String toString() {
            return "idx = " + idx + " value = " + value;
        }
    }
    int ret;
	void solve() throws IOException {
        int n = ni();
        int []v = new int[n];
        for(int i = 0; i < n; ++i) {
            v[i] = ni();
        }
        if (n == 0) {
            out.println(0);
            return ;
        }
        Arrays.sort(v);
        int first = v[0];
        int best = 1 << 29;
        for(int it = first - 5 ; it <= first; ++it) {
            ret = 0;
            for(int i = 0; i < n; ++i) {
                sub(it, v[i]);
            }
            best = min(ret, best);
        }
        out.println(best);
	}
    int sub(int a, int b) {
        int dx = b - a;
        ret += dx / 5;
        dx %= 5;
        ret += dx / 2;
        dx %= 2;
        ret += dx;
        return a;
    }

    boolean DEBUG = true;
    void dbg(Object ...args) {
      if(!DEBUG)
        return;
      for(Object o : args)
        System.err.print(o + " ");
      System.err.println();
    }

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for(int t = ni(); t > 0; --t)
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
