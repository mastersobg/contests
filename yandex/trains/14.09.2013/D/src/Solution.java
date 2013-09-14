import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  int b;
	int []v;
  HashMap<Integer, Integer> map;

  void solve(int N) throws IOException {
    n = N;
    v = new int[n];
    map = new HashMap<Integer, Integer> ();
    int total = 0; 
    for (int i = 0; i < n; ++i) {
      v[i] = ni();
      total += v[i];
    }
    b = n >> 1;
    rec1(0, 0, 0);
    int ret = rec2(b, 0, 0);
    out.println(total - ret);
	}

  void rec1(int pos, int diff, int sum) {
    if (pos == b) {
      if (!map.containsKey(diff)) {
        map.put(diff, sum);
      } else {
        if (map.get(diff) < sum) {
          map.put(diff, sum);
        }
      }
      return ;
    }
    rec1(pos + 1, diff + v[pos], sum + v[pos]);
    rec1(pos + 1, diff - v[pos], sum + v[pos]);
    rec1(pos + 1, diff, sum);
  }

  int rec2(int pos, int diff, int sum) {
    if (pos == n) {
      int rdiff = -diff;
      if (map.containsKey(rdiff)) {
        return sum + map.get(rdiff);
      }
      return 0;
    }
    int max = rec2(pos + 1, diff + v[pos], sum + v[pos]);
    max = max(max, rec2(pos + 1, diff - v[pos], sum + v[pos]));
    max = max( max, rec2(pos + 1, diff, sum));
    return max;
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
    while(true) {
      int n = ni();
      if (n == 0) {
        break;
      }
		  solve(n);
    }
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

