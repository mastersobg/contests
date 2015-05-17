import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    int mod = 1000000000;

    class Tree {
        int []t;
        int n;

        Tree(int n) {
            t = new int[n * 4];
            this.n = n;
        }

        int get(int val) {
            return get(0, 0, n, val, n);
        }

        int get(int idx, int l, int r, int lf, int rg) {
            if (l == lf && r == rg) {
                return t[idx];
            } else {
                int mid = (l + r) >> 1;
                if (rg <= mid) {
                    return get(idx * 2 + 1, l, mid, lf, rg);
                } else if (lf > mid) {
                    return get(idx * 2 + 2, mid + 1, r, lf, rg);
                } else {
                    return (get(idx * 2 + 1, l, mid, lf, mid) + 
                        get(idx * 2 + 2, mid + 1, r, mid + 1, rg)) % mod;
                }
            }
        }

        void add(int pos, int val) {
            add(0, 0, n, pos, val);
        }

        void add(int idx, int l, int r, int pos, int val) {
            if (l == r) {
                t[idx] = (t[idx] + val) % mod;
            } else {
                int mid = (l + r) >> 1;
                if (pos <= mid)
                    add(idx * 2 + 1, l, mid, pos, val);
                else 
                    add(idx * 2 + 2, mid + 1, r, pos, val);
                t[idx] = (t[idx * 2 + 1] + t[idx * 2 + 2]) % mod;
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(t);
        }
    }

    Tree []trees;

	void solve() throws IOException {
        int n = ni();
        int k = ni();
        int []v = new int[n];
        for (int i = 0; i < n; ++i)
            v[i] = ni();
        trees = new Tree[k + 1];
        for (int i = 0; i < trees.length; ++i) {
            trees[i] = new Tree(20000 + 10);
        }
        trees[0].add(n + 3, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= k; ++j) {
                if (j > 0) {
                    trees[j].add(v[i], trees[j - 1].get(v[i] + 1));
                }
            }
        }
        out.println(trees[k].get(0));
	}
	
	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		if (args.length > 0 && args[0].equals("LOCAL_DEBUG")) {
			DEBUG = true;
		}
		new Solution();
	}

	class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }
        long time() {
            return System.currentTimeMillis() - time;
        }
        void print() {
            print("Time spent = ");
        } 

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[]) 
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }
}
