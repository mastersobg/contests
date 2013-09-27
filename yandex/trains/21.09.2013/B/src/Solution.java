import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  String filename = "instance";

  int n, x, y;
  int [][]v;
  int [][]dp;

	void solve() throws IOException {
    n = ni();
    x = ni();
    y = ni();
    v = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        v[i][j] = ni();
      }
    }
    dp = new int[n][1 << n];
    for (int i = 0; i < n; ++i) {
      Arrays.fill(dp[i], -1);
    }
    int ret = rec(1 << x, x);
    if (ret == 1) {
      out.println("Yes");
    } else {
      out.println("No");
    }
	}

  int rec(int mask, int last) {
    if (Integer.bitCount(mask) == n) {
      return last == y ? 1 : 0;
    }
    int ret = dp[last][mask];
    if (ret < 0) {
      ret = 0;
      for (int i = 0; i < n; ++i) {
        if ((mask & (1 << i)) == 0 && v[last][i] == 1) {
          ret = max(ret, rec(mask | (1 << i), i));
        }
      }
      dp[last][mask] = ret;
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

