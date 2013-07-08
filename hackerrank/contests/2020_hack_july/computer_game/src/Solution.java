import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  int []primes;
  ArrayList<Integer> []arr, pr;
  ArrayList<Integer> []g;

  int []mt;
  boolean []used, was;
  Timer timer = new Timer();

  void solve() throws IOException {
    pr = new ArrayList[500000];
    for (int i = 0; i < pr.length; ++i) 
      pr[i] = new ArrayList<Integer> ();
    timer.start();
    primes = prime();
    n = ni();
    arr = new ArrayList[n];
    for (int i = 0; i < arr.length; ++i)
      arr[i] = new ArrayList<Integer> (0);
    for (int i = 0; i < n; ++i) {
      factor(ni(), i, true); 
    }
    for (int i = 0; i < n; ++i) {
      factor(ni(), i, false);
    }
    timer.print();
    mt = new int[n];
    used = new boolean[n];
    was = new boolean[n];
    g = new ArrayList[n];
    for (int i = 0; i < n; ++i) {
      g[i] = new ArrayList<Integer> ();
    }
    Arrays.fill(mt, -1);
    timer.start();
    greedy();
    timer.print();
    int ret = kuhn();
    out.println(ret);
    System.out.println();
	}

  int kuhn() {
    int ret = 0;
    for (int i = 0; i < n; ++i) {
      if (used[i]) {
        ++ret;
      } else {
        Arrays.fill(was, false);
//        was = new boolean[n];
        ret += dfs(i) ? 1 : 0;
      }
    }
    return ret;
  }


  boolean dfs(int v) {
    if (was[v])
      return false;
    was[v] = true;
    List<Integer> list = arr[v];
/*    for (int prime : list) {
      List<Integer> other = pr[prime];
      for (int o : other) {
        if (mt[o] == -1) {
          mt[o] = v;
          return true;
        }
      }
    }
    for (int prime : list) {
      List<Integer> other = pr[prime];
      for (int o : other) {
        if (dfs(mt[o])) {
          mt[o] = v;
          return true;
        }
      }
    }
    */
    for (int u : g[v]) {
      if (mt[u] == -1) {
        mt[u] = v;
        return true;
      }
    }
    for (int u : g[v]) {
      if (dfs(mt[u])) {
        mt[u] = v;
        return true;
      }
    }
    
    return false;
  }

  void greedy() {
    for (int i = 0; i < n; ++i) {
      List<Integer> list = arr[i];
gl : for (int prime : list) {
        List<Integer> other = pr[prime];//pr.get(prime);
        if (other != null) {
          for (int o : other) {
            if (mt[o] == -1 && !used[i]) {
              mt[o] = i;
              used[i] = true;
            }
          }
        }
      }
    }
  }

  int [] prime() {
    boolean []ret = new boolean[32000];
    ret[0] = ret[1] = true; 
    int cnt = 0;
    for (int i = 2; i < ret.length; ++i) {
      if (ret[i] == false) {
        ++cnt;
        for (int j = i + i; j < ret.length; j += i)
          ret[j] = true;
      }
    }
    int []result = new int[cnt];
    int size = 0;
    for (int i = 0; i < ret.length; ++i)
      if (!ret[i]) {
        result[size++] = i;
      }
    return result;
  }
  void factor(int value, int pos, boolean flag) {
    for (int idx = 0; idx < primes.length; ++idx) {
      int a = primes[idx];
      long other = (long) a * (long) a;
      if (other > value) {
        break;
      }
      if (value % a == 0) {
        while (value % a == 0) {
          value /= a;
        }
        int index = getPrimeIdx(a);
        if (flag) {
          arr[pos].add(index);
        } else {
          pr[index].add(pos);
//          addPr(index, pos);
        }
      }
    }
    if (value != 1) {
      int index = getPrimeIdx(value);
      if (flag) {
        arr[pos].add(index);
      } else {
        pr[index].add(pos);
//        addPr(index, pos);
      }       
    }
  }

  /*void addPr(int idx, int value) {
    if (!pr.containsKey(idx)) {
      pr.put(idx, new ArrayList<Integer>());
    }
    pr.get(idx).add(value);
  }*/

  HashMap<Integer, Integer> prime2idx = new HashMap<Integer, Integer> ();

  int getPrimeIdx(int prime) {
    if (!prime2idx.containsKey(prime)) {
      int idx = prime2idx.size();
      prime2idx.put(prime, idx);
      return idx;
    }
    return prime2idx.get(prime);
  }

  boolean DEBUG = true;
    void dbg(Object ...args) {
      if(!DEBUG)
        return;
      for(Object o : args)
        System.err.print(o + " ");
      System.err.println();
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

  class Timer {
    long time;
    void start() {
      time = System.currentTimeMillis();
    } 
    void print() {
      System.out.println("time=" + (System.currentTimeMillis() - time));
    }
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
