import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int w, vx, q;
  int []x, y;
  int s;
  int []vy;

	void solve() throws IOException {
    w = ni();
    vx = ni();
    q = ni();
    x = new int[q];
    y = new int[q];
    for (int i = 0; i < q; ++i) {
      x[i] = ni();
      y[i] = ni();
    }
    s = ni();
    vy = new int[s];
    for (int i = 0; i < s; ++i) {
      vy[i] = ni();
    }
    Arrays.sort(vy);

    int l = 0, r = s - 1;
    while (l + 1 < r) {
      int m = (l + r) >> 1;
      if (can(m)) {
        l = m;
      } else {
        r = m;
      }
    }
    if (can(r)) {
      out.println(vy[r]);
    } else if (can(l)) {
      out.println(vy[l]);
    } else {
      out.println("IMPOSSIBLE");
    }
	}

  boolean can(int idx) {
    double speed = vy[idx];
    double xl = x[0], xr = x[0] + w;
    for (int i = 1; i < q; ++i) {
      int dh = abs(y[i] - y[i - 1]);
      double time = dh / speed;
      double nxl = xl - time * vx;
      double nxr = xr + time * vx;
      xl = max(nxl, x[i]);
      xr = min(nxr, x[i] + w);
      if (more(xl, xr)) {
        return false;
      }
    }
    return true;
  }

  double eps = 1e-9;

  boolean more(double a, double b) {
    return a > b && !eq(a, b);
  }

  boolean eq(double a, double b) {
    return abs(a-b) <= eps;
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

