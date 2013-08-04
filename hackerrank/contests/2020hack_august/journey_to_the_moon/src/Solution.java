import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  ArrayList<Integer> []g;

  void dfs(int v, int color, int []was) {
    if (was[v] != 0) {
      return ;
    }
    was[v] = color;
    for (int u : g[v]) {
      dfs(u, color, was);
    }
  }

	void solve() throws IOException {
    n = ni();
    int k = ni();
    g = new ArrayList[n];
    for (int i = 0; i < n; ++i) {
      g[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < k; ++i) {
      int a = ni();
      int b = ni();
      g[a].add(b);
      g[b].add(a);
    }
    
    int []was = new int[n];
    int color = 0;
    for (int i = 0; i < n; ++i) {
      if (was[i] == 0) {
        dfs(i, ++color, was);
      }
    }
    int []cnt = new int[color + 1];
    for (int i = 0; i < n; ++i) {
      cnt[was[i]]++;
    }
    long ret = 0;
    for (int i = 1; i < cnt.length; ++i) {
      ret += (long) cnt[i] * (long) (n - cnt[i]);
    }
    out.println(ret / 2l);
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
		in = new BufferedReader(new InputStreamReader(System.in));
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

