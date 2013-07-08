import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  int []b;
  ArrayList<Integer> []g;

  void solve() throws IOException {
    n = ni();
    int []primes= primes();
    ArrayList<Integer> []next = new ArrayList[n];
    for (int i = 0; i < n; ++i)
      next[i] = new ArrayList<Integer> ();
    for (int i = 0; i < n; ++i) {
      next[i] = factor(ni(), primes);
    }
    b = new int[n];
    for (int i = 0; i < n; ++i) 
      b[i] = ni();
    System.out.println("OK");
    g = new ArrayList[n];
    boolean []was = new boolean[n];
    for (int i = 0; i < n; ++i) {
      g[i] = new ArrayList<Integer> ();
      Arrays.fill(was, false);
      for (int o : next[i]) {
        List<Integer> others = other(o);
        for (int a : others) 
        if (was[a] == false) {
          g[i].add(a);
          was[a] = true;
        }
      }
    }
    System.out.println(map.size());
    System.out.println(Arrays.deepToString(g));
	}

  HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

  List<Integer> other(int prime) {
    if (map.containsKey(prime))
      return map.get(prime);
    ArrayList<Integer> ret = new ArrayList<Integer> ();
    for (int i = 0; i < n; ++i) {
      if (b[i] % prime == 0) {
        ret.add(i);
      }
    }
    map.put(prime, ret);
    return ret;
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
        ret.add(p);
      }
    }
    if (n != 1) {
      ret.add(n);
    }
    return ret;
  }
	
  public void run() throws IOException {
		Locale.setDefault(Locale.US);
//		in = new BufferedReader(new InputStreamReader(System.in));
    generate();
    in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
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
}
