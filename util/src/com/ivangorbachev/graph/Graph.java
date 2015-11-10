package com.ivangorbachev.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivan Gorbachev
 */
public class Graph {

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
    }

    public int from(int edgeId) {
        return from[edgeId];
    }

    public int to(int edgeId) {
        return to[edgeId];
    }

    public long weight(int edgeId) {
        return weight[edgeId];
    }

    public int nextEdge(int edgeId) {
        return nextEdge[edgeId];
    }

    public int firstEdge(int v) {
        return firstOutgoing[v];
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

    public void addEdge(int from, int to) {
        addEdge(from, to, 0L);
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
            @Override
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

        @Override
        public boolean hasNext() {
            return currentEdgeIdx != -1;
        }

        @Override
        public Integer next() {
            int vertex = to[currentEdgeIdx];
            this.currentEdgeIdx = nextEdge[currentEdgeIdx];
            return vertex;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}