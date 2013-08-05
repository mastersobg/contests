import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n, k, m;
  int []v;

  byte [][][]dp;
  int [][]f;
  final int mod = 1000000007;

  byte rec(int pos, int took, int s) {
    if (dp[pos][took][s] == -1) {
      byte ret = 0;
      if (took == k) {
        ret = (byte) (s == 0 ? 1 : 0);
      } else if (pos == n) {
        ret = 0;
      } else {
        if (s - v[pos] >= 0) {
          ret = rec(pos + 1, took + 1, s - v[pos]);
        }
        ret = (byte) max(ret, rec(pos + 1, took, s));
      }
      dp[pos][took][s] = ret;
    }
    return dp[pos][took][s];
  }

  int f(int n, int last) {
    if (n == 0) {
      return 1;
    }
    if (last > n) {
      return 0;
    }
    if (f[n][last] == -1) {
      int ret = 0;
      if (n - last >= 0) {
        ret = (ret + f(n - last, last)) % mod;
      }
      ret = (ret + f(n, last + 1)) % mod;
      f[n][last] = ret;
    }
    return f[n][last];
  }
	void solve() throws IOException {
    Timer timer = new Timer();
    timer.start();
    n = ni();
    k = ni();
    m = ni();
    v = new int[n];
    for (int i = 0; i < n; ++i) {
      v[i] = ni();
    }
    dp = new byte[n + 1][k + 1][m];
    int ret = -1;
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp[i].length; ++j) {
        Arrays.fill(dp[i][j], (byte) -1);
      }
    }
    for (int s = m - 1; s >= 0; --s) {
      ret = rec(0, 0, s);
      if (ret == 1) {
        ret = s;
        break;
      }
    }
    timer.print();
    out.println(ret);
    f = new int[ret + 1][ret + 1];
    for (int i = 0; i < f.length; ++i) {
      Arrays.fill(f[i], -1);
    }
    out.println(f(ret, 1));
    timer.print();
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
//    generate();
		in = new BufferedReader(new InputStreamReader(System.in));
//    in = new BufferedReader(new FileReader("test.txt"));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}
  void generate() throws IOException {
    PrintWriter out = new PrintWriter("test.txt");
    out.println("100 100 3000");
    Random rnd = new Random();
    for (int i = 0; i < 100; ++i) {
      out.println(25);
    }
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

