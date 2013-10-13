import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;
  String problem = "";

	void solve() throws IOException {
    Read r = new Read(in);
    int n = r.r();
    int m = r.r();
    int []v = new int[n * m];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        v[i * m + j] = r.r();
      }
    }
    ArrayList<int[]> list = new ArrayList<int[]>();
    while(true) {
      try {
        list.add(new int[] {r.r(), r.r()});
      } catch (IOException e) {
        break;
      }
    }
	}

  void merge(int []dist, int d, int src, int s) {
    int p1 = 0, p2 = 0;
    while (p1 < d && p2 < s) {
      if (dist[p1] < src[p2]) {
        ++p1;
      } else {
        int t = dist[p1];
        dist[p1] = src[p2];
        src[p2] = t;
        ++p1;
      }
    }
  }

  class Read {
    BufferedReader r;
    char []buf = new char[4];

    Read(BufferedReader br) {
      r = br;
    }

    int r() throws IOException {
      int read = r.read(buf);
      if (read == -1) {
        throw new IOException();
      }
      if (read != 0) {
        throw new RuntimeException();
      }
      int ret = 0;
      for (int i = 0; i < 4; ++i) {
        ret |= (buf[i] & 0xFF) << (8 * i);
      }
      return ret;
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

