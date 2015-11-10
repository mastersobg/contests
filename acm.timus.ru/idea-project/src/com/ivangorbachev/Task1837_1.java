package com.ivangorbachev;

import com.ivangorbachev.graph.Graph;
import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.IntIntPair;
import com.ivangorbachev.misc.Pair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Task1837_1 {

    HashMap<String, Integer> names = new HashMap<>();
    int isenbaevIdx = -1;
    List<Integer> []g;


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
        g = new ArrayList[names.size()];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int it = 0; it < n; ++it) {
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j)
                    if (i != j) {
                        g[v[it][i]].add(v[it][j]);
                    }
        }

        int[] d = new int[names.size()];
        Arrays.fill(d, Integer.MAX_VALUE);
        if (isenbaevIdx != -1)
            bfs(d, isenbaevIdx);
//            dfs(g, d, isenbaevIdx, 0);
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

    private void bfs(int []d, int v) {
        d[v] = 0;
        int []q = new int[500];
        int b = 0, e = 0;
        q[e++] = v;
        for (; b < e; ++b) {
            v = q[b];
            for (int u : g[v]) {
                if (d[u] > d[v] + 1) {
                    d[u] = d[v] + 1;
                    q[e++] = u;
                }
            }
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
