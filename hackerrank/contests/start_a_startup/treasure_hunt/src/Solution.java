import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
    int [][][]v = new int[2][5][5];
    for (int i = 0; i < 5; ++i) {
      for (int j = 0; j < 5; ++j) {
        int a = ni();
        v[0][i][j] = a / 10 - 1;
        v[1][i][j] = a % 10 - 1;
      }
    }
    boolean [][]was = new boolean[5][5];
    int x = 0, y = 0;
    boolean found = false;
    List<Integer> X = new ArrayList<Integer>(), Y = new ArrayList<Integer> ();
    X.add(1);
    Y.add(1);
    while (true) {
      int nx = v[0][x][y];
      int ny = v[1][x][y];
      X.add(nx + 1);
      Y.add(ny + 1);
      if (nx == x && ny == y) {
        found = true;
        break;
      }
      was[x][y] = true;
      if (was[nx][ny]) {
        break;
      }
      x = nx;
      y = ny;
    }
    if (!found) {
      out.println("NO TREASURE");
      return ;
    }
    for (int i = 0; i < X.size() - 1; ++i) {
      out.println(X.get(i) + " " + Y.get(i));
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

