import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Map;
import java.io.BufferedReader;
import java.util.List;
import java.util.Collections;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.IOException;
import java.util.Iterator;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Map.Entry;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task1837 solver = new Task1837();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1837 {
        HashMap<String, Integer> names = new HashMap<>();
        int isenbaevIdx = -1;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            int[][] v = new int[n][3];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < 3; ++j) {
                    String s = in.readString();
                    int idx = getIdx(s);
                    isenbaev(s, idx);
                    v[i][j] = idx;
                }
            }
            Graph g = new Graph(names.size(), n * 3 * 2);
            for (int i = 0; i < n; ++i) {
                g.addBidirectionalEdge(v[i][0], v[i][1]);
                g.addBidirectionalEdge(v[i][0], v[i][2]);
                g.addBidirectionalEdge(v[i][1], v[i][2]);
            }

            int[] d = new int[names.size()];
            Arrays.fill(d, Integer.MAX_VALUE);
            if (isenbaevIdx != -1)
                dfs(g, d, isenbaevIdx, 0);
            List<Pair<String, Integer>> ret = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : names.entrySet()) {
                ret.add(new Pair<>(entry.getKey(), d[entry.getValue()]));
            }
            Collections.sort(ret, (a, b) -> a.getX().compareTo(b.getX()));
            for (Pair<String, Integer> p : ret) {
                if (p.getY() == Integer.MAX_VALUE) {
                    out.println(p.getX() + " undefined");
                } else
                    out.println(p.getX() + " " + p.getY());
            }
        }

        private void dfs(Graph g, int[] d, int v, int step) {
            if (d[v] <= step) {
                return;
            }
            d[v] = step;
            for (int u : g.getAdjacentVertices(v)) {
//        for (int id = g.firstEdge(v); id != -1; id = g.nextEdge(id)) {
//            int to = g.to(id);
                dfs(g, d, u, step + 1);
            }
        }

        void isenbaev(String s, int idx) {
            if ("Isenbaev".equals(s)) {
                isenbaevIdx = idx;
            }
        }

        int getIdx(String s) {
            Integer i = names.get(s);
            if (i == null) {
                names.put(s, names.size());
                return names.size() - 1;
            }
            return i;
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

    static class Graph {
        private int lastEdgeIdx;
        private int[] firstOutgoing;
        private int[] from;
        private int[] to;
        private int[] nextEdge;
        private long[] weight;

        public Graph(int vertices) {
            this(vertices, vertices);
        }

        public Graph(int vertices, int edges) {
            edges = Math.max(2, edges);
            this.lastEdgeIdx = 0;

            firstOutgoing = new int[vertices];
            Arrays.fill(firstOutgoing, -1);

            from = new int[edges];
            to = new int[edges];
            nextEdge = new int[edges];
            weight = new long[edges];
        }

        private void ensureCapacity() {
            if (lastEdgeIdx == from.length) {
                int newLength = from.length + (from.length >> 1);
                from = Arrays.copyOf(from, newLength);
                to = Arrays.copyOf(to, newLength);
                nextEdge = Arrays.copyOf(nextEdge, newLength);
                if (weight != null) {
                    weight = Arrays.copyOf(weight, newLength);
                }
            }
        }

        public void addBidirectionalEdge(int from, int to) {
            addEdge(from, to, 0L);
            addEdge(to, from, 0L);
        }

        public void addEdge(int from, int to, long weight) {
            ensureCapacity();
            int edgeId = lastEdgeIdx++;
            if (firstOutgoing[from] == -1) {
                nextEdge[edgeId] = -1;
            } else {
                nextEdge[edgeId] = firstOutgoing[from];
            }
            firstOutgoing[from] = edgeId;

            this.from[edgeId] = from;
            this.to[edgeId] = to;
            if (weight != 0) {
                if (this.weight == null) {
                    this.weight = new long[this.from.length];
                }
                this.weight[edgeId] = weight;
            }
        }

        public Iterable<Integer> getAdjacentVertices(final int vertex) {
            return new Iterable<Integer>() {

                public Iterator<Integer> iterator() {
                    return new AdjVerticesIterator(vertex);
                }
            };
        }

        public class AdjVerticesIterator implements Iterator<Integer> {
            int currentEdgeIdx;

            public AdjVerticesIterator(int vertex) {
                this.currentEdgeIdx = firstOutgoing[vertex];
            }


            public boolean hasNext() {
                return currentEdgeIdx != -1;
            }


            public Integer next() {
                int vertex = to[currentEdgeIdx];
                this.currentEdgeIdx = nextEdge[currentEdgeIdx];
                return vertex;
            }


            public void remove() {
                throw new UnsupportedOperationException();
            }

        }

    }

    static class Pair<K, V> {
        public K x;
        public V y;

        public Pair() {
        }

        public Pair(K x, V y) {
            this.x = x;
            this.y = y;
        }

        public K getX() {
            return x;
        }

        public V getY() {
            return y;
        }


        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (x != null ? !x.equals(pair.x) : pair.x != null) return false;
            return !(y != null ? !y.equals(pair.y) : pair.y != null);

        }


        public int hashCode() {
            int result = x != null ? x.hashCode() : 0;
            result = 31 * result + (y != null ? y.hashCode() : 0);
            return result;
        }


        public String toString() {
            return "[x = " + (x == null ? "null" : x.toString()) +
                    " y = " + (y == null ? "null" : y.toString())
                    + "]";
        }

    }
}

