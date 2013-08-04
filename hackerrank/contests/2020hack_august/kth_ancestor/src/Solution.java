import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = false;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n;

  static class Node {
    Node p;
    int idx;
    HashSet<Node> children = new HashSet<Node>();
    Node []parents;

    Node(int idx) {
      parents = new Node[20];
      Arrays.fill(parents, null);
      this.idx = idx;
    }
  }
 
  Node []idx2node;
  Node root;

  void dfs(Node v, Node p) {
    v.p = p;
    v.parents[0] = p;
    for (int i = 1; i < v.parents.length; ++i) {
      if (v.parents[i - 1] == null) {
        break;
      }
      v.parents[i] = v.parents[i - 1].parents[i - 1];
    }
    for (Node u : v.children) {
      if (u != p) {
        dfs(u, v);
      }
    }
  }
	void solve() throws IOException {
    idx2node = new Node[100001];
    n = ni();
    for (int i = 0; i < n; ++i) {
      int a = ni();
      int b = ni();
      if (b == 0) {
        root = getV(a);    
      } else {
        Node v = getV(a);
        Node u = getV(b);
        v.children.add(u);
        u.children.add(v);
      }
    }
    dfs(root, null);
    int q = ni();
    for (int it = 0; it < q; ++it) {
      int c = ni();
      if (c == 0) {
        Node p = getV(ni());
        Node v = getV(ni());
        v.p = p;
        p.children.add(v);
        
        v.parents[0] = p;
        for (int i = 1; i < v.parents.length; ++i) {
          if (v.parents[i - 1] == null) {
            break;
          }
          v.parents[i] = v.parents[i - 1].parents[i - 1];
        }
      } else if (c == 1) {
        int a = ni();
        Node v = getV(a);
        idx2node[a] = null;
        v.p.children.remove(v);
      } else {
        int a = ni();
        int k = ni();
        if (idx2node[a] == null) {
          out.println(0);
        } else {
          Node v = getV(a);
          Node u = getKth(v, k);
          if (u == null) {
            out.println(0);
          } else {
            out.println(u.idx);
          }
        }
      }
    }
	}

  Node getKth(Node v, int k) {
    for (int i = v.parents.length - 1; i >= 0; --i) {
      int a = 1 << i;
      if (a < k) {
        k -= a;
        v = v.parents[i];
        if (v == null) {
          return null;
        }
      }
    }
    return v.parents[0];
  }

  Node getV(int a) {
    if (idx2node[a] != null)
      return idx2node[a];
    idx2node[a] = new Node(a);
    return idx2node[a];
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
    for (int t = ni(); t > 0; --t) {
		  solve();
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

