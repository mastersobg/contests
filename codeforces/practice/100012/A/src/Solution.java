import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  BufferedReader in;
  StringTokenizer st;
  PrintWriter out;

  ArrayList<Integer> []g;
  int n;
  int []order, level;
  int last = 0;

  void dfs(int v, int p, int l) {
    level[v] = l;
    for(int u : g[v]) {
      if(u != p) {
        dfs(u, v, l + 1);
      }
    }
    order[v] = last++;
  }
  void solve() throws IOException {
    n = ni();
    g = new ArrayList[n];
    for(int i = 0; i < n; ++i) {
      g[i] = new ArrayList<Integer> ();
    }
    for(int i = 1; i < n; ++i) {
      int v = ni() - 1;
      int u = ni() - 1;
      g[v].add(u);
      g[u].add(v);
    }
    level = new int[n];
    order = new int[n];
    dfs(0, -1, 0);
    for(int i = 0; i < n; ++i) {
      out.println(order[i] + " " + level[i]);
    }
  }

  public Solution() throws IOException {
    Locale.setDefault(Locale.US);
    in = new BufferedReader(new FileReader("input.txt"));
    out = new PrintWriter("output.txt");
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
    new Solution();
  }
}
