package com.ivangorbachev;

import com.ivangorbachev.graph.Graph;

import java.util.Arrays;

public class Treestrat {

    public int roundcnt(int[] tree, int[] A, int[] B) {
        int n = tree.length + 1;
        Graph g = new Graph(n);
        for (int i = 0; i < tree.length; ++i) {
            g.addEdge(i + 1, tree[i]);
            g.addEdge(tree[i], i + 1);
        }
        int []d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        for (int b : B) {
            dfs(g, b, 0, d);
        }
        int []d2 = new int[n];
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

    void go(Graph g, int v, int step, int []d) {
        if (d[v] <= step) {
            return ;
        }
        d[v] = step;
        for (int to : g.getAdjacentVertices(v)) {
            go(g, to, step + 1, d);
        }
    }

    void dfs(Graph g, int vertex, int step, int []d) {
        if (d[vertex] <= step) {
            return;
        }
        d[vertex] = step;
        for (int to : g.getAdjacentVertices(vertex)) {
            dfs(g, to, step + 1, d);
        }
    }
}
