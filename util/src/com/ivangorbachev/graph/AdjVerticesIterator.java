package com.ivangorbachev.graph;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ivan Gorbachev
 */
public class AdjVerticesIterator implements Iterator<Integer> {

    private List<Edge> edges;
    private int idx = 0;

    public AdjVerticesIterator(List<Edge> edges) {
        if (edges == null) {
            throw new IllegalArgumentException("edges is null");
        }
        this.edges = edges;
    }

    @Override
    public boolean hasNext() {
        return idx < edges.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new IllegalStateException("no next element");
        }
        return edges.get(idx++).getTo();
    }
}
