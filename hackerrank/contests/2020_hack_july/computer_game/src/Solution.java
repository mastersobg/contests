import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;
  int []primes;
  ArrayList<Integer> []arr;
  HashMap<Integer, List<Integer>> pr = new HashMap<Integer, List<Integer>>();

	void solve() throws IOException {
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
          addPr(index, pos);
        }
      }
    }
    if (value != 1) {
      int index = getPrimeIdx(value);
      if (flag) {
        arr[pos].add(index);
      } else {
        addPr(index, pos);
      }       
    }
  }

  void addPr(int idx, int value) {
    if (!pr.containsKey(idx)) {
      pr.put(idx, new ArrayList<Integer>());
    }
    pr.get(idx).add(value);
  }

  HashMap<Integer, Integer> prime2idx = new HashMap<Integer, Integer> ();

  int getPrimeIdx(int prime) {
    if (!prime2idx.containsKey(prime)) {
      int idx = prime2idx.size();
      prime2idx.put(prime, idx);
      return idx;
    }
    return prime2idx.get(prime);
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
}
