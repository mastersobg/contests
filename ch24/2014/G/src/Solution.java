import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n;
    int []p1, p2;
    int []v1, v2;

    ArrayList<Integer> []g1, g2;
    int []cnt11, cnt21;

    void solve() throws IOException {
        n = ni();
        p1 = new int[n];
        p2 = new int[n];
        v1 = new int[n];
        v2 = new int[n];
        g1 = new ArrayList[n];
        g2 = new ArrayList[n];
        cnt11 = new int[n];
        cnt21 = new int[n];
        for (int i = 0; i < n; ++i) {
            g1[i] = new ArrayList<Integer> ();
            g2[i] = new ArrayList<Integer> ();
        }
        int root1 = -1, root2 = -1;
        Arrays.fill(p1, -1);
        Arrays.fill(p2, -1);
        for (int i = 0; i < n; ++i) {
            int a = ni(), b = ni();
            if (a != i) {
                p1[i] = a;
                g1[a].add(i);
            } else 
                root1 = i;
            if (b != i) {
                p2[i] = b;
                g2[b].add(i);
            } else 
                root2 = i;
            v1[i] = ni();
            v2[i] = ni();
        }

        dfs(root1, g1, cnt11);
        dfs(root2, g2, cnt21);

        // removeDfs(root1, g1);
        ArrayList<Integer> ret = null;
        
        for (int iter = 0;; ++iter) {
            int []c1 = new int[n];
            int []c2 = new int[n];
            System.arraycopy(cnt11, 0, c1, 0, n);
            System.arraycopy(cnt21, 0, c2, 0, n);
            ArrayList<Integer> a = solveIt(c1, c2);
            if (ret == null || ret.size() < a.size()) {
                ret = a;
                out.println(ret.size());
                for (int q : ret)
                    out.print(q + " ");
                out.println();
                out.println("---------------------");
                out.flush();
            }
        }
        
        
    }

    ArrayList<Integer> solveIt(int []cnt1, int []cnt2) {
        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i)
            order.add(i);

        Collections.shuffle(order);

        ArrayList<Integer> ret = new ArrayList<Integer> ();
        for (int idx = 0; idx < n; ++idx) {
            int i = order.get(idx);
            if (can(i, cnt1, cnt2)) {
                ret.add(i);
                remove(i, cnt1, cnt2);
            }
        }
        return ret;
    }

    boolean can(int v, int []cnt1, int []cnt2) {
        return count(v, v1, cnt1, p1, g1) > 0 && count(v, v2, cnt2, p2, g2) > 0;
    }

    int count(int root, int []v, int []cnt, int []p, ArrayList<Integer> []g) {
        int min = Integer.MAX_VALUE;
        while (root != -1) {
            min = min(min, cnt[root] - v[root]);
            root = p[root];
        }
        return min;
    }
    
    void remove(int v, int []cnt1, int []cnt2) {
        update(v, cnt1, p1);
        update(v, cnt2, p2);
    }

    void update(int root, int []cnt, int []p) {
        while (root != -1) {
            cnt[root]--;
            root = p[root];
        }
    }

    int dfs(int v, ArrayList<Integer> []g, int []cnt) {
        int ret = 0;
        for (int u : g[v]) {
            ret += dfs(u, g, cnt);
        }
        cnt[v] = ret + 1;
        return cnt[v];
    }

    public void run() throws IOException {
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
        new Solution().run();
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

