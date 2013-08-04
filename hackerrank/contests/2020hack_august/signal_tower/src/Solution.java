import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
    int n = ni();
    double []x = new double[n];
    double []y = new double[n];
    double lx = 1e+9, rx = -1e+9, ly = 1e+9, ry = -1e+9;
    for (int i = 0; i < n; ++i) {
      x[i] = nd();
      y[i] = nd();
      lx = min(lx, x[i]);
      rx = max(rx, x[i]);
      ly = min(ly, y[i]);
      ry = max(ry, y[i]);
    }
    double eps = 1e-3;
    double min = 1e+9;
    double xret = 0.0, yret = 0.0;
    for (double dx = lx; dx <= rx; dx += eps) {
      for (double dy = ly; dy <= ry; dy += eps) {
        double a = calc(x, y, dx, dy);
        if (a < min) {
          min = a;
          xret = dx;
          yret = dy;
        }
      }
    }
    dbg("x=", xret, "y=", yret, "min=", min);
	}

  double calc(double []x, double []y, double dx, double dy) {
    double ret = 0;
    for (int i = 0; i < x.length; ++i) {
      ret += max(abs(x[i] - dx), abs(y[i] - dy));
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

