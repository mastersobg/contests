import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  void solve() throws IOException {
    int k = ni();
    char []s = ns().toCharArray();
    int ones = 0, zeroes = 0, first = -1;
    for (int i = 0; i < s.length; ++i) {
      if (s[i] == '0') {
        ++zeroes;
      } else {
        if (first == -1) {
          first = i;
        }
        ++ones;
      }
    }
    dbg("ret=", sol(fullString(new String(s), k), k));
    if (ones == 0) {
      out.println(0);
      return ;
    }
    if (2 * zeroes <= 3 * ones) {
      out.println(s.length * k);
      return ;
    }
    zeroes = ones = 0;
    first = findOnes(fullString(new String(s), 2));
    for (int i = first;; ++i) {
      int idx = i % s.length;
      if (s[idx] == '0') {
        ++zeroes;
      } else {
        ++ones;
      }
      if (2 * zeroes > 3 * ones) {
        dbg("i=", i);
        out.println(i - first);
        return ;
      }
    }
  }

  int findOnes(String s) {
    dbg(s);
    int pos = -1;
    int maxLen = 0;
    int cnt = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '1') {
        ++cnt;
      } else {
        if (cnt > maxLen) {
          maxLen = cnt;
          pos = i - cnt;
        }
        cnt = 0;
      }
    }
    dbg("pos=", pos);
    return pos;
  }

	void solve(int q) throws IOException {
    int len = ni(), cnt = ni();
    for (int i = 0; i < (1 << len); ++i) {
      String s = add(Integer.toString(i, 2), len);
      String full = fullString(s, cnt);
      int []arr = sol(full, cnt);
      int ones = 0, zeroes = 0;
      for (int it = 0; it < s.length(); ++it) {
        if (s.charAt(it) == '0') {
          ++zeroes;
        } else {
          ++ones;
        }
      }      
      if (!(arr[1] == 0 && arr[2] == len * cnt - 1)) {
        for (int it = 0; it < s.length(); ++it) {
          if (s.charAt(it) == '1') {
            if (arr[1] != it) {
              dbg("s=", s, "arr=", arr);
              throw new RuntimeException();
            }
            break;
          }
        }
        //dbg("s=", s, "arr=", arr);
//        dbg("zeroes=", zeroes * 2, "ones=", ones * 3);
//        if (zeroes * 2 > ones * 3) {
//          throw new RuntimeException();
//        }
      }
    }
	}

  String add(String s, int len) {
    while (s.length() < len) {
      s = "0" + s;
    }
    return s;
  }

  String fullString(String s, int k) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < k; ++i) {
      sb.append(s);
    }
    return sb.toString();
  }

  int []sol(String s, int k) {
    int maxLen = 0, f = -1, l = -1;
    for (int i = 0; i < s.length(); ++i) {
      int z = 0, o = 0;
      for (int j = i; j < s.length(); ++j) {
        if (s.charAt(j) == '0') {
          ++z;
        } else {
          ++o;
        }
        if (2 * z <= 3 * o) {
          int len = j - i + 1;
          if (len > maxLen) {
            maxLen = len;
            f = i;
            l = j;
          }
        }
      }
    }
    return new int[] {maxLen, f, l};
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

