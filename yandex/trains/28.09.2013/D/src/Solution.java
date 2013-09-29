import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
  String filename = "informatics";

  int k, n;

	void solve() throws IOException {
    k = ni();
    n = ni();
    ArrayList<BitSet> v = new ArrayList<BitSet>();
    for (int i = 0; i < n; ++i) {
      BitSet set = new BitSet(n);
      set.set(i);
      v.add(set);
    }
    for (int i = 0; i < k; ++i) {
      int q = ni();
      if (q == 1) {
        BitSet set = new BitSet(n);
        int cnt = ni();
        for (int j = 0; j < cnt; ++j) {
          int value = ni() - 1; 
          set.or(v.get(value));
        }
        v.add(set);
      } else {
        int a = ni() - 1;
        int cnt = ni();
        for (int j = 0; j < cnt; ++j) {
          v.add((BitSet)v.get(a).clone());
        }
      }
    }

    int q = ni();
    for (int i = 0; i < q; ++i) {
      int idx = ni() - 1;
      int bit = ni() - 1;
      if (v.get(idx).get(bit)) {
        out.println("YES");
      } else {
        out.println("NO");
      }
    }
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

