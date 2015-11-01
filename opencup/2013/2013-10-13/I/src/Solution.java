import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
  String problem = "i";

  int n, k;
  int []v;
  ArrayList<Integer> []g;
  BitSet[]dp;

	void solve() throws IOException {
    n = ni();
    k = ni();
    v = new int[k];
    for (int i = 0; i < k; ++i) {
      v[i] = ni() - 1;
    }
    g = new ArrayList[n];
    for (int i = 0; i < n; ++i) {
      g[i] = new ArrayList<Integer> ();
    }
    for (int i = 0; i < n; ++i) {
      StringTokenizer st = new StringTokenizer(in.readLine());
      int id = Integer.valueOf(st.nextToken()) - 1;
      while (st.hasMoreTokens()) {
        int next = Integer.valueOf(st.nextToken()) - 1;
        g[id].add(next);
      }
    }
    dp = new BitSet[n];
    int ret = -1;
    int ans = 1 << 30;
    for (int i = 0; i < k; ++i) {
      int a = rec(v[i]).cardinality();
      if (a >= ret) {
        if (a > ret) ans = v[i];
        else ans = min(ans, v[i]);
        ret = a;
      }
    }
    out.println(ans + 1);
	}

  BitSet rec(int v) {
    BitSet ret = dp[v];
    if (ret != null) return ret;
    ret = new BitSet(n);
    for (int u : g[v]) {
      ret.or(rec(u));
    }
    ret.set(v);
    dp[v] = ret;
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

