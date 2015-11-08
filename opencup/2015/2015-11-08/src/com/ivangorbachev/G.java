package com.ivangorbachev;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.misc.Pair;

import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class G {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        HashMap<String, Integer> map = new HashMap<>();
        final HashMap<String, Integer> order = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = in.readString();
            StringTokenizer st = new StringTokenizer(s, ".");
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            if (map.containsKey(s2)) {
                map.put(s2, map.get(s2) + 1);
            } else {
                map.put(s2, 1);
                order.put(s2, i);
            }
        }
        List<Pair<String, Integer>> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(new Pair<String, Integer>(key, map.get(key)));
        }
        Collections.sort(list, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                int p1 = order.get(o1.getX());
                int p2 = order.get(o2.getX());
                return p1 - p2;
            }
        });

        for (Pair p : list) {
            out.println(p.getX() + ": " + p.getY());
        }
    }
}
