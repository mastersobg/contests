package com.ivangorbachev;

import com.ivangorbachev.graph.Graph;
import com.ivangorbachev.io.Reader;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class L {

    int n;
    int k;
    int m;
    Graph g;
    boolean[] bad;
    int badTotal;
    int[] q;

    //    List<Integer> []g;
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

    private void go(int u) {
        if (bad[u])
            return;
        ++badTotal;
        bad[u] = true;
        for (int v : g.getAdjacentVertices(u)) {
            go(v);
        }
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
