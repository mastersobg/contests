import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    static class Comp implements Comparable<Comp> {
        int t, w, id;

        Comp(int t, int w, int id) {
            this.t = t;
            this.w = w;
            this.id = id;
        }

        @Override
        public int compareTo(Comp o) {
            if (w == o.w) return id - o.id;
            return w - o.w;
        }
    }

    static class Ret implements Comparable<Ret> {
        int w, comp, socket;

        Ret(int w, int comp, int socket) {
            this.w = w;
            this.comp = comp;
            this.socket = socket;
        }

        @Override
        public int compareTo(Ret o) {
            if (w == o.w) return comp - o.comp;
            return w - o.w;
        }
    }

    int n, a, b;
    Comp[] c;


    void solve() throws IOException {
        n = ni();
        a = ni();
        b = ni();
        c = new Comp[n];
        for (int i = 0; i < c.length; ++i)
            c[i] = new Comp(ni(), ni(), i);
        Arrays.sort(c);
        boolean[] used = new boolean[n];

        TreeSet<Ret> berland = new TreeSet<Ret>();
        TreeSet<Ret> beuropean = new TreeSet<Ret>();

        int berlandId = 0;
        for (int i = 0; i < c.length && berland.size() < a; ++i) {
            if (c[i].t == 1) {
                used[i] = true;
                berland.add(new Ret(c[i].w, c[i].id, berlandId++));
            }
        }

        int beuropeanId = a;
        for (int i = 0; i < c.length && beuropean.size() < b; ++i) {
            if (c[i].t == 2) {
                used[i] = true;
                beuropean.add(new Ret(c[i].w, c[i].id, beuropeanId++));
            }
        }

        for (int i = 0; i < c.length && berland.size() < a; ++i) {
            if (c[i].t == 3) {
                used[i] = true;
                berland.add(new Ret(c[i].w, c[i].id, berlandId++));
            }
        }

        for (int i = 0; i < c.length && beuropean.size() < b; ++i) {
            if (c[i].t == 3 && !used[i]) {
                used[i] = true;
                beuropean.add(new Ret(c[i].w, c[i].id, beuropeanId++));
            }
        }

        TreeSet<Comp> unused = new TreeSet<Comp>();
        for (int i = 0; i < c.length; ++i)
            if (c[i].t == 3 && !used[i]) {
                unused.add(c[i]);
            }

        while (true) {
            if (unused.isEmpty()) break;
            Comp comp = unused.first();
            Ret land;
            if (berland.isEmpty()) {
                land = MIN_RET;

            } else {
                land = berland.last();
            }
            Ret pean;
            if (beuropean.isEmpty()) {
                pean = MIN_RET;
            } else {
                pean = beuropean.last();
            }

            if (land == MIN_RET && pean == MIN_RET) break;

            if (max(land, pean).w <= comp.w) break;

            if (land.compareTo(pean) < 0) {
                Ret r = beuropean.pollLast();
                beuropean.add(new Ret(comp.w, comp.id, r.socket));
                unused.pollFirst();
            } else {
                Ret r = berland.pollLast();
                berland.add(new Ret(comp.w, comp.id, r.socket));
                unused.pollFirst();
            }
        }
        out.println((berland.size() + beuropean.size()) + " " + sum(berland, beuropean));
        for (Ret r : berland.descendingSet()) {
            out.println((r.comp + 1) + " " + (r.socket + 1));
        }
        for (Ret r : beuropean.descendingSet()) {
            out.println((r.comp + 1) + " " + (r.socket + 1));
        }

    }

    Ret max(Ret a, Ret b) {
        if (a.compareTo(b) < 0) return b;
        else return a;
    }

    int sum(TreeSet<Ret> a, TreeSet<Ret> b) {
        int ret = 0;
        for (Ret r : a.descendingSet())
            ret += r.w;
        for (Ret r : b.descendingSet())
            ret += r.w;
        return ret;
    }

    Ret MIN_RET = new Ret(-1 << 20, -1, -1);

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

    void dbg(Object... objs) {
        if (!DEBUG) {
            return;
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

