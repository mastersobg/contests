import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	static class Edge {
		Edge e;
		int u;
		boolean used = false;
	}

	static class Graph {
		ArrayList<Edge>[] g;

		void addEdge(int v, int u) {
			Edge e = new Edge();
			e.u = u;
			g[v].add(e);
		}

		Graph(int n) {
			g = new ArrayList[n];
			for (int i = 0; i < g.length; i++) {
				g[i] = new ArrayList<Edge>();
			}
		}

		Graph inserse() {
			Graph ret = new Graph(g.length);
			for (int i = 0; i < g.length; ++i) {
				for (int j = 0; j < g[i].size(); ++j) {
					int v = i;
					int u = g[i].get(j).u;
					ret.addEdge(u, v);
				}
			}
			return ret;
		}

		void print() {
			System.out.println("vertex count=" + g.length);
			for (int i = 0; i < g.length; ++i) {
				System.out.print((i + 1) + ": ");
				for (int j = 0; j < g[i].size(); ++j)
					System.out.print((g[i].get(j).u + 1) + " ");
				System.out.println();
			}
		}
	}

	void directEdges(int v, boolean[] was, Graph g, Graph ng, int prev) {
		if (was[v])
			return;
		was[v] = true;
		for (int i = 0; i < g.g[v].size(); ++i) {
			Edge e = g.g[v].get(i);
			if (e.used)
				continue;
			e.used = e.e.used = true;
			int u = e.u;
			ng.addEdge(v, u);
			directEdges(u, was, g, ng, v);
		}
	}

	void dfs1(int v, boolean[] was, Graph g, ArrayList<Integer> order) {
		was[v] = true;

		for (int i = 0; i < g.g[v].size(); ++i) {
			int u = g.g[v].get(i).u;
			if (!was[u]) {
				dfs1(u, was, g, order);
			}
		}

		order.add(v);
	}

	void dfs2(int v, boolean[] was, Graph g, int[] colors, int color) {
		was[v] = true;
		colors[v] = color;

		for (int i = 0; i < g.g[v].size(); ++i) {
			int u = g.g[v].get(i).u;
			if (!was[u])
				dfs2(u, was, g, colors, color);
		}
	}

	void bfs(int v, Graph g, int[] d) {
		Arrays.fill(d, 1 << 29);
		d[v] = 0;
		int[] q = new int[g.g.length];
		int b = 0, e = 0;
		for (q[e++] = v; b < e; ++b) {
			v = q[b];
			for (int i = 0; i < g.g[v].size(); ++i) {
				int u = g.g[v].get(i).u;
				if (d[u] > d[v] + 1) {
					d[u] = d[v] + 1;
					q[e++] = u;
				}
			}
		}
	}

	void solve() throws IOException {
		int n = ni();
		int m = ni();
		Graph g = new Graph(n);
		for (int i = 0; i < m; ++i) {
			int v = ni() - 1;
			int u = ni() - 1;
			Edge e1 = new Edge();
			e1.u = u;
			Edge e2 = new Edge();
			e2.u = v;
			e1.e = e2;
			e2.e = e1;
			g.g[v].add(e1);
			g.g[u].add(e2);
		}
//		g.print();
		Graph directed = new Graph(n);
		directEdges(0, new boolean[n], g, directed, -1);
//		directed.print();

		boolean[] was = new boolean[n];
		ArrayList<Integer> order = new ArrayList<Integer>(n);
		for (int i = 0; i < n; ++i)
			if (!was[i]) {
				dfs1(i, was, directed, order);
			}

		// System.out.println(order);

		Graph reversed = directed.inserse();
//		reversed.print();
		Arrays.fill(was, false);
		int[] colors = new int[n];
		int color = 0;
		for (int i = n - 1; i >= 0; --i) {
			int v = order.get(i);
			if (!was[v]) {
				dfs2(v, was, reversed, colors, color++);
			}
		}

		Graph tree = new Graph(color);

		for (int v = 0; v < n; ++v) {
			for (int j = 0; j < g.g[v].size(); ++j) {
				int u = g.g[v].get(j).u;
				if (colors[v] != colors[u]) {
					tree.addEdge(colors[v], colors[u]);
				}
			}
		}
//		tree.print();

		int[] d = new int[tree.g.length];
		bfs(0, tree, d);
		int mx = -1;
		int v = -1;
		for (int i = 0; i < d.length; ++i)
			if (mx < d[i]) {
				mx = d[i];
				v = i;
			}
		bfs(v, tree, d);
		mx = -1;
		int u = -1;
		for (int i = 0; i < d.length; ++i)
			if (mx < d[i]) {
				mx = d[i];
				u = i;
			}
		int a = findAny(colors, v, -1);
		int b = findAny(colors, u, a - 1);
		out.println(a + " " + b);
	}

	int findAny(int[] colors, int color, int diff) {
		for (int i = 0; i < colors.length; ++i)
			if (colors[i] == color && i != diff)
				return i + 1;
		return -1;
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
