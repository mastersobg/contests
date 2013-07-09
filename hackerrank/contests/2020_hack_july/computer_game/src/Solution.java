import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  int [][]n2p, p2n;
  HashMap<Integer, Integer> pId = new HashMap<Integer, Integer> ();
  int []was, mt, pwas;

  void solve() throws IOException {
    Timer t = new Timer();
    t.start();
    n = ni();
    int []primes= primes();
    n2p = new int[n][];
    for (int i = 0; i < n; ++i) {
      ArrayList<Integer> list = factor(ni(), primes);
      n2p[i] = new int[list.size()];
      Collections.shuffle(list);
      for (int j = 0; j < list.size(); ++j) {
        n2p[i][j] = list.get(j);
      }
    }
    int primeIdxs = pId.size();
    p2n = new int[primeIdxs][];
    ArrayList<Integer> []tp2n = new ArrayList[primeIdxs];
    for (int i = 0; i < primeIdxs; ++i) {
      tp2n[i] = new ArrayList<Integer> ();
    }
    for (int i = 0; i < n; ++i) {
      ArrayList<Integer> list = factor(ni(), primes);
      for (int j = 0; j < list.size(); ++j) {
        int a = list.get(j);
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
    t.print();
	}

  int kuhn() {
    was = new int[n];
    pwas = new int[100000];
    mt = new int[n];
    Arrays.fill(mt, -1);
    int ret = 0;
    int step = 1;
    for (int i = n - 1; i >= 0; --i) {
      if (dfs(i, step)) {
        ++step;
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
      if (pdfs(v, prime, step)) {
        return true;
      }
    }
    return false;
  }

  boolean pdfs(int s, int v, int step) {
    if (pwas[v] == step) {
      return false;
    }
    pwas[v] = step;
    for (int other : p2n[v]) {
      if (mt[other] == -1) {
        mt[other] = s;
        return true;
      }
    }
    
    for (int other : p2n[v]) {
      if (dfs(mt[other], step)) {
        mt[other] = s;
        return true;
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
    Integer idx = pId.get(prime);
    if (idx != null) {
      return idx;
    }
    pId.put(prime, pId.size());
    return pId.size() - 1;
  }

	
  void generate() throws IOException {
    PrintWriter out = new PrintWriter("input.txt");
    out.println(100000);
    Random rnd = new Random();
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

  
