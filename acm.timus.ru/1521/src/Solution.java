import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;


    class Fenwick {
        int []t;

        Fenwick(int n) {
            t = new int[n];
        }

        void add(int idx) {
            for (; idx < t.length; idx += idx & -idx) {
                t[idx]++;
            }
        }

        void remove(int idx) {
            for (; idx < t.length; idx += idx & -idx) {
                t[idx]--;
            }
        }

        int get(int idx) {
            if (idx <= 0) 
                return 0;
            int ret = 0;
            for (; idx > 0; idx -= idx & -idx) {
                ret += t[idx];
            }
            return ret;
        }

        int get(int l, int r) {
            if (l > r)
                return 0;
            return get(r) - get(l - 1);
        }
    }

    Fenwick tree;
    int n, k;

	void solve() throws IOException {
        n = ni(); k = ni();
        // for (n = 1; n <= 10; ++n) {
        //     for (k = 1; k <= n; ++k) {
                // dbg("------------", n, k);
                StringBuilder sb = new StringBuilder();
                int cur = n;
                tree = new Fenwick(n + 1);
                // boolean []v = new boolean[n + 1];
                for (int i = 1; i <= n; ++i) {
                    tree.add(i);
                }
                for (int i = 0; i < n; ++i) {
                    if (i > 0) {
                        sb.append(" ");
                    }
                    // int des = find(cur, k, v);
                    cur = findNext(cur, k);
                    // if (cur != des) {
                    //     dbg(v);
                    //     dbg(n, k, des, cur);
                    //     throw new IOException();
                    // }
                    tree.remove(cur);
                    sb.append(cur);
                    // dbg("cycle===============", sb);
                }
                out.println(sb.toString());
        //     }
        // }
	}

    int find(int cur, int k, boolean []v) {
        int idx = cur + 1;
        if (idx == v.length)
            idx = 1;
        int taken = 0;
        while (taken < k) {
            if (!v[idx]) {
                ++taken;
            }
            if (taken == k) {
                break;
            }
            ++idx;
            if (idx == v.length)
                idx = 1;
        }
        v[idx] = true;
        return idx;
    }

    int findNext(int cur, int k) {
        int toEnd = tree.get(cur + 1, n);
        int total = tree.get(n);
        if (total < k) {
            if (total == 1)
                k = 1;
            else {
                k %= total;
                if (k == 0)
                    k = total; 
            }
        }
        int l = cur, r = n;
        int left = cur;
        int toAdd = 0;
        if (toEnd < k) {
            l = 0;
            r = cur;
            left = 0;   
            toAdd = toEnd;
        }
        // dbg(cur, l, r, toAdd, toEnd, left, k);
        while (l + 1 < r) {
            int mid = (l + r) >> 1;
            int cnt = tree.get(left, mid);
            // dbg(l, r);  
            if (cnt + toAdd >= k) {
                r = mid;
            } else if (cnt + toAdd < k) {
                l = mid;
            } else {
                return mid;
            }
        }
        if (tree.get(left, l) + toAdd == k)
            return l;
        return r;
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
