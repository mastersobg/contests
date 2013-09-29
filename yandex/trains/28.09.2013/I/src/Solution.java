import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
  String filename = "suffix";

  int k;
  char []s1, s2;

  int [][]d1;
  int []d2;
  int []next;

  void solve() throws IOException {
    k = ni();
    s1 = in.readLine().toCharArray();
    s2 = in.readLine().toCharArray();
    d1 = new int[s1.length][s2.length];
    for (int i = 0; i < s1.length; ++i) {
      Arrays.fill(d1[i], -1);
    }
    for (int i = 0; i < s1.length; ++i) {
      for (int j = 0; j < s2.length; ++j) {
        rec1(i, j);
      }
    }
    
    d2 = new int[s1.length];
    next = new int[s1.length];
    Arrays.fill(d2, -1);
    int ret = rec2(s1.length - 1);
    if (ret == 0) {
      out.println(0);
    } else {
      int pos = s1.length - 1;
      ArrayList<Integer> res = new ArrayList<Integer> ();
      while (pos != s1.length - s2.length - 1) {
        res.add(pos - next[pos]);
        pos = next[pos];
      }

      out.println(res.size());
      for (int a : res) {
        out.print(a + " ");
      }
    }
	}

  int rec2(int pos) {
    if (pos == s1.length - s2.length - 1) {
      return 1;
    }
    if (pos < 0) {
      return 0;
    }
    int ret = d2[pos];
    if (ret == -1) {
      ret = 0;
      int pos2 = s1.length - pos - 1;
      for (int i = pos - k + 1; i >= 0; --i) {
        int max = d1[i][pos2];
        if (max >= pos - i + 1) {
          int a = rec2(i - 1);
          if (a == 1) {
            ret = a;
            next[pos] = i - 1;
          }
        }
      }
      d2[pos] = ret;
    }
    return ret;
  }


  int rec1(int i, int j) {
    if (i >= s1.length || j >= s2.length) {
      return 0;
    }
    int ret = d1[i][j];
    if (ret == -1) {
      if (s1[i] == s2[j]) {
        ret = rec1(i + 1, j + 1) + 1;
      } else {
        ret = 0;
      }
      d1[i][j] = ret;
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

