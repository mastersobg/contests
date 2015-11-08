import java.io.InputStream;
import java.io.*;
import java.util.Locale;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.List;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        L solver = new L();
        solver.solve(1, in, out);
        out.close();
    }

    static class L {
        int n;
        int k;
        int m;
        Graph g;
        boolean[] bad;
        int badTotal;
        int[] q;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            n = in.readInt();
            k = in.readInt();
            m = in.readInt();
//        g = new ArrayList[n];
//        for (int i = 0; i < n; i++) {
//            g[i] = new ArrayList<>();
//        }
            q = new int[n];
            g = new Graph(n);
            bad = new boolean[n];
            badTotal = k;
            for (int i = 0; i < k; ++i) {
                bad[in.readInt() - 1] = true;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; ++i) {
                int v = in.readInt() - 1;
                int u = in.readInt() - 1;
//            g[u].add(v);
                g.addEdge(u, v);
                if (bad[u]) {
                    bfs(v);
                }
                sb.append(badTotal);
                sb.append("\n");
            }
            out.print(sb.toString());
        }

        private void bfs(int v) {
            int b = 0, e = 0;
            q[e++] = v;
            for (; b < e; ++b) {
                v = q[b];
                if (bad[v])
                    continue;
                bad[v] = true;
                ++badTotal;
                for (int u : g.getAdjacentVertices(v))
                    if (!bad[u]) {
                        q[e++] = u;
                    }
            }
        }

    }

    static class Graph {
        private ArrayList<Edge>[] edges;

        public Graph(int vertices) {
            edges = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                edges[i] = new ArrayList<Edge>();
            }
        }

        public void addEdge(int from, int to) {
            edges[from].add(new Edge(from, to));
        }

        public Iterable<Integer> getAdjacentVertices(final int vertex) {
            assertVertexIdx(vertex);
            return new Iterable<Integer>() {

                public Iterator<Integer> iterator() {
                    return new AdjVerticesIterator(edges[vertex]);
                }
            };
        }

        private void assertVertexIdx(int v) {
            if (v < 0 || v >= edges.length) {
                throw new IllegalArgumentException(
                        "vertex = " + v + " edges.length = " + edges.length);
            }
        }

    }

    static class Edge {
        private final int from;
        private final int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getTo() {
            return to;
        }

    }

    static class AdjVerticesIterator implements Iterator<Integer> {
        private List<Edge> edges;
        private int idx = 0;

        public AdjVerticesIterator(List<Edge> edges) {
            if (edges == null) {
                throw new IllegalArgumentException("edges is null");
            }
            this.edges = edges;
        }


        public boolean hasNext() {
            return idx < edges.size();
        }


        public Integer next() {
            if (!hasNext()) {
                throw new IllegalStateException("no next element");
            }
            return edges.get(idx++).getTo();
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    static class Reader {
        private BufferedReader in;
        private StringTokenizer st;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

        public String readString() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            return st.nextToken();
        }

        public int readInt() {
            return Integer.parseInt(readString());
        }

    }
}

