import java.io.*;
import java.util.*;
import java.math.BigInteger;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;


	void solve() throws IOException {
    int a = 30; //ni();
    int b = 1000000;//ni();
    int deflen = getlen(b);
    long qwe = 0;
    for (int i = 1; i <= 30; ++i) {
      for (int j = 1; j <= 100000000; ++j) {
        int q = i ^ j;
        if (getlen(q) != deflen) {
          ++qwe;
          dbg(getlen(q));
        }
      }
    }
    dbg("qwe=", qwe);
    if (true) {
      return ;
    }
    long s = 0;
    long cnt = 0;
    for (int i = 1; i <= a; ++i) {
      for (int j = 1; j <= b; ++j) {
        long q = (long) i * (long) j;
        if (!was(q, i, b, true)) {
          s += q;
          ++cnt;
        }
      }
    }
    long ret = 0;
    int dlen = getlen(b);
    ArrayList<Long> list = new ArrayList<Long> ();
    int LEN = 0;
    long xor = 0;
    for (int i = 1; i <= a; ++i) {
      for (int j = 1; j <= b; ++j) {
        int q = i ^ j;
        if (!was(q, i, b, false)) {
          int len = dlen;
          if (q > b) {
            ++LEN;
          }
        }
          xor += q;
/*          long value = (s * (long)pow(10, len)) + (long) q * (long) cnt;
          if (ret + value < ret) {
            list.add(ret);
            ret = 0;
          }
          ret += value;
          if (ret < 0) {
            dbg("step=", i, j);
            dbg("q=", q);
            dbg("len=", len);
            dbg("cnt=", cnt);
            dbg("q*cnt=", q * cnt);
            dbg("pow=", pow(10, len));
            dbg("s=", s);
            dbg("s*pow=", s * (long) pow(10, len));
            dbg("s*pow + q * cnt=", s * (long) pow(10, len) + (long)q * (long)cnt);
            dbg("ret=", ret);
            throw new RuntimeException();            
          }
        }
      }*/
  }
    }
/*   dbg("list=", list.size());
    BigInteger result = BigInteger.ZERO;
    for (long v : list) {
      result = result.add(BigInteger.valueOf(v));
    }*/
    out.println(ret);
    
    //    long stress = stress(a, b);
//    assert stress == ret : "stress = " + stress + " ret = " + ret;
	}

  boolean was(long n, long a, long b, boolean op) {
    for (int i = 1; i < a; ++i) {
      long o;
      if (op) {
        if (n % i != 0)
          continue;
        o = n / i;
      } else {
        o = n ^ i;
      }
      if (o <= b && o > 0) {
        return true;
      }
    }
    return false;
  }

  int getlen(int a) {
    int ret = 0;
    while (a > 0) {
      a /= 10;
      ++ret;
    }
    return ret;
  }

  long stress(long a, long b) {
    HashSet<Long> set1 = new HashSet<Long> (), set2 = new HashSet<Long> ();
    for (int i = 1; i <= a; ++i) {
      for (int j = 1; j <= b; ++j) {
        set1.add((long) i * (long) j);
        set2.add((long) i ^ j);
      }
    }
    long ret = 0;
    int dlen = getlen((int)b);
    for (Long n1 : set1) {
      for (Long n2 : set2) {
        String s1 = n1.toString();
        String s2 = n2.toString();
        while (s2.length() < dlen)
          s2 = "0" + s2;
        ret += Long.valueOf(s1 + s2);
      }
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

