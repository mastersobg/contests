import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
  String problem = "g";

  int n;
  int [][]v;
  int [][][]dp;

  void solve() throws IOException {
    n = ni();
    v = new int[3][n];
    for (int i = 0; i < n; ++i) {
      v[0][i] = ni();
      v[1][i] = ni();
      v[2][i] = ni();
    }
    dp = new int[2][2][n + 1];
    for (int i = 0; i < 2; ++i)
      for (int j = 0; j < 2; ++j)
        Arrays.fill(dp[i][j], -2000000000);
    int ret = sol();//rec(0, 0, 0);
//    int brute = brute(n);
//    assert ret == brute : ret + " " + brute;
    out.println(ret);
	}

/*  int rec(int idx, int p1, int p2) {
    if (idx == n) return 0;
    int ret = dp[p1][p2][idx];
    if (ret < 0) {
      ret = rec(idx + 1, p2, 0);
      int q = rec(idx + 1, p2, 1);
      if (p2 == 1) {
        q += b;
        if (p1 == 1) {
          q -= b;
          q += c;
        } else {
            q += b;
            q -= a;
        }
      } else q += this.a;

      ret = max(ret, q);
      dp[p1][p2][idx] = ret;
    }
    return ret;
  }
*/
  int sol() {
    dp[0][0][0] = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < 2; ++j) {
        for (int k = 0; k < 2; ++k) {
          if (dp[j][k][i] < 0) continue;
          dp[k][0][i + 1] = max(dp[k][0][i + 1], dp[j][k][i]);
          int q = 0;
          if (k == 1) {
            q += v[1][i];
            if (j == 1) {
              q -= v[1][i - 1];
              q += v[2][i - 1];
            } else {
              q += v[1][i-1];
              q -= v[0][i-1];
            }
          } else q += v[0][i];
          dp[k][1][i + 1] = max(dp[k][1][i + 1], q + dp[j][k][i]);
        }
      }
    }
    int ret = 0;
    for (int i = 0; i < 2; ++i) for (int j = 0; j < 2; ++j)
      ret = max(dp[i][j][n], ret);
    return ret;
  }
  int brute(int n) {
    int board = 1 << n;
    int ret = 0;
    for (int mask = 0; mask < board; ++mask) {
      int cur = 0;
      for (int i = 0; i < n; ++i) {
        if (in(mask, n, i)) {
          int prev = in(mask, n, i - 1) ? 1 : 0;
          int next = in(mask, n, i + 1) ? 1 : 0;
          if (prev + next == 0) cur += v[0][i];
          else if (prev + next == 1) cur += v[1][i];
          else cur += v[2][i];
        }
      }
      ret = max(ret, cur);
    }
    return ret;
  }

  boolean in(int mask, int len, int bit) {
    if (bit < 0) return false;
    if (bit >= len) return false;
    return (mask & (1 << bit)) != 0;
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
    in = new BufferedReader(new FileReader(problem + ".in"));
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

