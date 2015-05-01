import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    Random rnd = new Random();

    int n, m;
    int [][]edges;
    int []q;

    int []p;
    int components = 0;

    void init() {
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            ++components;
        }
    }

    int find(int v) {
        if (v == p[v]) {
            return v;
        }
        return p[v] = find(p[v]);
    }

    void unite(int v, int u) {
        int p1 = find(p[v]);
        int p2 = find(p[u]);
        if (p1 == p2) {
            return;
        }
        --components;
        if (rnd.nextBoolean()) {
            p[p1] = p2;
        } else {
            p[p2] = p1;
        }

        dbg("p=", p);

    }

	void solve() throws IOException {
        n = ni();
        m = ni();
        edges = new int[2][m];
        for (int i = 0; i < m; ++i) {
            edges[0][i] = ni() - 1;
            edges[1][i] = ni() - 1;
        }
        q = new int[ni()];
        HashSet<Integer> qSet = new HashSet<>();
        for (int i = 0; i < q.length; ++i) {
            q[i] = ni() - 1;
            qSet.add(q[i]);
        }
        p = new int[n];
        init();
        dbg("p=", p);
        for (int i = 0; i < m; ++i) {
            if (!qSet.contains(i)) {
                unite(edges[0][i], edges[1][i]);
            }
        }
        int size = q.length;
        int []ans = new int[size];
        ans[--size] = components;
        for (int i = q.length - 1; i >= 0; --i) {
            int edge = q[i];
            unite(edges[0][edge], edges[1][edge]);
            if (size > 0) {
                ans[--size] = components;
            }
        }
        for (int i : ans)
            out.print(i + " ");

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
