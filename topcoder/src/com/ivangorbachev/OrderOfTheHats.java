package com.ivangorbachev;

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class OrderOfTheHats {

    char [][]g;
    int []dp;
    int n;
    int []gmask;

    int rec(int mask) {
        if(mask == (1 << n) - 1)
            return 0;
        if(dp[mask] != -1)
            return dp[mask];
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++i)
            if(!in(mask, i)) {
            	int next = mask | (1 << i);
                int a = rec(next) + Integer.bitCount(next & gmask[i]);
                ret = Math.min(ret, a);
            }
        dp[mask] = ret;
        return ret;
    }

    boolean in(int mask, int bit) {
        return (mask & (1 << bit)) != 0;
    }
    public int minChanged(String[] spellChart) {
        g = new char[spellChart.length][];
        for(int i = 0; i < g.length; ++i)
            g[i] = spellChart[i].toCharArray();
        n = g.length;
        gmask = new int[n];
        for(int i = 0; i < n; ++i) {
        	for(int j = 0; j < n; ++j)
        		if(g[i][j] == 'Y')
        			gmask[i] |= (1 << j);
        }
        dp = new int[1 << n];
        Arrays.fill(dp, -1);
        return rec(0);
    }

}

