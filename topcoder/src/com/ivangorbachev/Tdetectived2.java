package com.ivangorbachev;

import com.ivangorbachev.util.BitsUtil;

import java.util.Arrays;
import java.util.HashSet;

public class Tdetectived2 {
    public int reveal(String[] s) {
        //lol ar
        int [][]g = parse(s);
        int []ans = new int[g.length];
        Arrays.fill(ans, Integer.MAX_VALUE);
        rec(g, ans, 1, 0, 0, new int[ans.length]);
        int ret = 0;
        for (int i = 1; i < ans.length; i++) {
            ret += i * ans[i];
        }
        return ret;
    }

    HashSet<Integer> set = new HashSet<Integer>();

    private void rec(int[][] g, int[] ans, int mask, int cur, int step, int []susp) {
        if (set.contains(mask))
            return;
        ans[cur] = Math.min(ans[cur], step);
        int []nsusp = new int[susp.length];
        int max = -1;
        for (int i = 0; i < susp.length; i++) {
            nsusp[i] = Math.max(susp[i], g[cur][i]);
            if (!BitsUtil.checkBit(mask, i)) {
                max = Math.max(max, nsusp[i]);
            }
        }
        for (int i = 0; i < susp.length; i++) {
            if (!BitsUtil.checkBit(mask, i)&& nsusp[i] == max) {
                rec(g, ans, mask | BitsUtil.setBit(mask, i), i, step + 1, nsusp);
            }
        }
        set.add(mask);
    }

    private int[][] parse(String[] s) {
        int [][]g = new int[s.length][s.length];
        int n = s.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = s[i].charAt(j) - '0';
            }
        }
        return g;
    }
}
