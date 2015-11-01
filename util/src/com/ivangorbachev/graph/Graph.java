package com.ivangorbachev.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivan Gorbachev
 */
public class Graph {
    private ArrayList<Edge>[]edges;

    public Graph(int vertices) {
        edges = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            edges[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(new Edge(from, to));
    }

    public List<Edge> getEdges(int vertex) {
        assertVertexIdx(vertex);
        return edges[vertex];
    }

    public Iterable<Integer> getAdjacentVertices(final int vertex) {
        assertVertexIdx(vertex);
        return new Iterable<Integer>() {
            @Override
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