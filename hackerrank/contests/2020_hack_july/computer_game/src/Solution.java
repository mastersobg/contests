import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  int [][]n2p, p2n;
  HashMap<Integer, Integer> pId = new HashMap<Integer, Integer> ();
  int []was, mt;

  void solve() throws IOException {
    Timer t = new Timer();
    t.start();
    n = ni();
    int []primes= primes();
    n2p = new int[n][];
    for (int i = 0; i < n; ++i) {
      ArrayList<Integer> f = factor(ni(), primes);
      n2p[i] = new int[f.size()];
      int sz = 0;
      for (int a : f) {
        n2p[i][sz++] = a;
      }
    }
    int primeIdxs = pId.size();
    p2n = new int[primeIdxs][];
    ArrayList<Integer> []tp2n = new ArrayList[primeIdxs];
    for (int i = 0; i < primeIdxs; ++i) {
      tp2n[i] = new ArrayList<Integer> ();
    }
    for (int i = 0; i < n; ++i) {
      ArrayList<Integer> f = factor(ni(), primes);
      for (int a : f) {
        if (a < primeIdxs) {
          tp2n[a].add(i);
        }
      }
    }
    
    for (int i = 0; i < primeIdxs; ++i) {
      p2n[i] = new int[tp2n[i].size()];
      int sz = 0;
      for (int a : tp2n[i]) {
        p2n[i][sz++] = a;
      }
    }
    t.print();
    out.println(kuhn());
	}

  int kuhn() {
    was = new int[n];
    mt = new int[n];
    Arrays.fill(mt, -1);
    int ret = 0;
    for (int i = 0; i < n; ++i) {
//      dbg(i);
      if (dfs(i, i + 1)) {
        ++ret;
      }
    }
    return ret;
  }

  boolean dfs(int v, int step) {
    if (was[v] == step)
      return false;
    was[v] = step;
    for (int prime : n2p[v]) {
      for (int other : p2n[prime]) {
        if (mt[other] == -1) {
          mt[other] = v;
          return true;
        }
      }
    }
    for (int prime : n2p[v]) {
      for (int other : p2n[prime]) {
        if (dfs(mt[other], step)) {
          mt[other] = v;
          return true;
        }
      }
    }
    return false;
  }

  int []primes() {
    boolean []p = new boolean[32000];
    p[0] = p[1] = true;
    int cnt = 0;
    for (int i = 2; i < p.length; ++i) {
      if (!p[i]) {
        ++cnt;
        for (int j = i * i; j < p.length; j += i) 
          p[j] = true;
      }
    }
    int []ret = new int[cnt];
    int size = 0;
    for (int i = 0; i < p.length; ++i)
      if (!p[i])
        ret[size++] = i;
    return ret;
  }

  ArrayList<Integer> factor(int n, int []primes) {
    ArrayList<Integer> ret = new ArrayList<Integer> ();
    for (int i = 0; i < primes.length; ++i) {
      int p = primes[i];
      if (p * p > n) {
        break;
      }
      if (n % p == 0) {
        while (n % p == 0) {
          n /= p;
        }
        ret.add(getPrimeId(p));
      }
    }
    if (n != 1) {
      ret.add(getPrimeId(n));
    }
    return ret;
  }

  int getPrimeId(int prime) {
    if (pId.containsKey(prime))
      return pId.get(prime);
    pId.put(prime, pId.size());
    return pId.size() - 1;
  }

	
  void generate() throws IOException {
    PrintWriter out = new PrintWriter("input.txt");
    out.println(100000);
    Random rnd = new Random(12345L);
    for (int i = 0; i < 100000; ++i) { 
      out.print((rnd.nextInt(999999999) + 2) + " ");
    }
    out.println();
    for (int i = 0; i < 100000; ++i) { 
      out.print((rnd.nextInt(999999999) + 2) + " ");
    }
    out.close();
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
//    generate();
//		in = new BufferedReader(new FileReader("input.txt"));    
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

  
