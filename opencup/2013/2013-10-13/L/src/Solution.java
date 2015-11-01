import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
  String problem = "l";

	void solve() throws IOException {
    int []arr = new int[4];
    while (true) {
      arr[0] = ni();
      arr[1] = ni();
      arr[2] = ni();
      arr[3] = ni();
      if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0 && arr[3] == 0) break;
      if (arr[3] == 0) {
        out.println(arr[0] + " " + arr[1] + " " + arr[2] + " " + (arr[0] * arr[1] * arr[2]));
      } else {
        calc(arr);
        for (int i = 0; i < 4; ++i) 
          out.print(arr[i] + " ");
        out.println();
      }
    }
	}

  void calc(int []arr) {
    for (int i = 0; i < 3; ++i) 
      if (arr[i] == 0) {
        int mn = 1;
        for (int j = 0; j < 3; ++j) if (j != i)
          mn *= arr[j];
        arr[i] = arr[3] / mn;
        break;
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

