package com.ivangorbachev;

import com.ivangorbachev.graph.Graph;
import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.Pair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Task1837 {

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
