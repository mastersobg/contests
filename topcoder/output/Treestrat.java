import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class Treestrat {
    public int roundcnt(int[] tree, int[] A, int[] B) {
        int n = tree.length + 1;
        Graph g = new Graph(n);
        for (int i = 0; i < tree.length; ++i) {
            g.addEdge(i + 1, tree[i]);
            g.addEdge(tree[i], i + 1);
        }
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        for (int b : B) {
            dfs(g, b, 0, d);
        }
        int[] d2 = new int[n];
        int result = Integer.MAX_VALUE;
        for (int a : A) {
            Arrays.fill(d2, Integer.MAX_VALUE);
            go(g, a, 0, d2);
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if (d2[i] < d[i]) {
                    max = Math.max(max, d[i]);
                }
            }
            result = Math.min(result, max);
        }
        return result;
    }

    void go(Graph g, int v, int step, int[] d) {
        if (d[v] <= step) {
            return;
        }
        d[v] = step;
        for (int to : g.getAdjacentVertices(v)) {
            go(g, to, step + 1, d);
        }
    }

    void dfs(Graph g, int vertex, int step, int[] d) {
        if (d[vertex] <= step) {
            return;
        }
        d[vertex] = step;
        for (int to : g.getAdjacentVertices(vertex)) {
            dfs(g, to, step + 1, d);
        }
    }

}

class Edge {
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

class AdjVerticesIterator implements Iterator<Integer> {
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

}

class Graph {
    private ArrayList<Edge>[] edges;

    public Graph(int vertices) {
        edges = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(new Edge(from, to));
    }

    public Iterable<Integer> getAdjacentVertices(int vertex) {
        assertVertexIdx(vertex);
        return () -> new AdjVerticesIterator(edges[vertex]);
    }

    private void assertVertexIdx(int v) {
        if (v < 0 || v >= edges.length) {
            throw new IllegalArgumentException(
                    "vertex = " + v + " edges.length = " + edges.length);
        }
    }

}
