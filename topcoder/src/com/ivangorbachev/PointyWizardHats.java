package com.ivangorbachev;

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class PointyWizardHats {

	private boolean checkHat(int th, int tr, int bh, int br) {
		int x = bh * tr;
		return x < th * br && bh * br > x;
	}
	
	ArrayList<Integer> []g;
	int []mt;
	boolean []was;
	
	boolean dfs(int v) {
		if(was[v])
			return false;
		was[v] = true;
		for(int u : g[v]) 
			if(mt[u] == -1) {
				mt[u] = v;
				return true;
			}
		for(int u : g[v]) 
			if(dfs(mt[u])) {
				mt[u] = v;
				return true;
			}
		return false;
	}
    public int getNumHats(int[] th, int[] tr, int[] bh, int[] br) {
    	int n = th.length;
    	int m = bh.length;
    	g = new ArrayList[n];
    	mt = new int[m];
    	Arrays.fill(mt, -1);
    	for(int i = 0; i < n; ++i)
    		g[i] = new ArrayList<Integer>();
    	for(int i = 0; i < n; ++i)
    		for(int j = 0; j < m; ++j)
    			if(checkHat(th[i], tr[i], bh[j], br[j])) {
    				g[i].add(j);
    			}
    	//printGraph();
    	int ret = 0;
    	was = new boolean[n];
    	for(int i = 0; i < n; ++i) {
    		Arrays.fill(was, false);
    		ret += (dfs(i) == true ? 1 : 0);
    	}
    	return ret;
    }
		
	void printGraph() {
		for(int i = 0; i < g.length; ++i) {
			System.err.print("#" + i + ": ");
			for(int u : g[i])
				System.err.print(u + " ");
			System.err.println();
		}
	}
}

