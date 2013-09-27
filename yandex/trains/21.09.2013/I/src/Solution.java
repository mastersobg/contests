import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  String filename = "ticket";

  int n;
  char []s1, s2;

  long [][][]dp;

  long prefix(char []s) {
    dp = new long[2][n + 1][2 * 9 * n + 2];
    dp[1][0][(n / 2) * 9] = 1;
//    dbg(dp.length, dp[0].length, dp[0][0].length);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < 2; ++j) {
        for (int k = 0; k < dp[j][i].length; ++k) {
          for (char next = '0'; next <= '9'; ++next) {
            if (!(j == 0 || next <= s[i])) {
              continue; 
            }
            int ni = i + 1;
            int nj;
            if (j == 1 && next == s[i]) {
              nj = 1;
            } else {
              nj = 0;
            }
            int nk;
            if (i < n / 2) {
              nk = k + (next - '0');
            } else {
              nk = k - (next - '0');
            }
            if (nk >= dp[j][i].length || nk < 0) {
              continue;
            }
            dp[nj][ni][nk] += dp[j][i][k];
          }
        }
      }
    }
    return dp[0][n][(n / 2) * 9] + dp[1][n][(n / 2) * 9];
  }

	void solve() throws IOException {
    n = ni();
    s1 = ns().toCharArray();
    s2 = ns().toCharArray();
    long q = Long.valueOf(new String(s1));
    long a = prefix(s2);
    long b;
    if (q == 0) b = 0;
    else b = prefix(toString(q - 1, n).toCharArray());
    out.println(a - b);
	}

  String toString(long a, int len) {
    String ret = Long.toString(a);
    while (ret.length() < len) {
      ret = "0" + ret;
    }
    return ret;
  }

  void dbg(Object ... objs) {
    if (!DEBUG) {
      return ;
    }
    for (Object o : objs) {
      String printLine;
      if (o.getClass().isArray()) {
        printLine = arrayToString(o);
      } else {
        printLine = o.toString();
      }
      System.err.print(printLine + " ");
    }
    System.err.println();
  }

  String arrayToString(Object o) {
    if (o instanceof long[]) 
      return Arrays.toString((long[]) o);
    if (o instanceof int[])
      return Arrays.toString((int[]) o);
    if (o instanceof short[])
      return Arrays.toString((short[]) o);
    if (o instanceof char[])
      return Arrays.toString((char[]) o);
    if (o instanceof byte[])
      return Arrays.toString((byte[]) o);
    if (o instanceof double[])
      return Arrays.toString((double[]) o);
    if (o instanceof float[])
      return Arrays.toString((float[]) o);
    if (o instanceof boolean[])
      return Arrays.toString((boolean[]) o);
    if (o instanceof Object[])
      return Arrays.deepToString((Object[]) o);
    throw new IllegalStateException();
  }
  

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
    in = new BufferedReader(new FileReader(filename + ".in"));
    out = new PrintWriter(filename + ".out");
    int t = ni();
    for (int i = 0; i < t; ++i) {
		  solve();
    }
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

  class Timer {
    
    long time;
    
    void start() {
      time = System.currentTimeMillis();
    }
    long time() {
      return System.currentTimeMillis() - time;
    }
    void print() {
      print("Time spent = ");
    } 
    
    void print(String message) {
      dbg(message, time());
    }

  }
}

