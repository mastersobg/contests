import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n;

    class Event implements Comparable<Event> {
        int x;
        int y1, y2;
        int id;
        // false - open, true - close
        boolean type;

        Event(int x, int y1, int y2, boolean type, int id) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.type = type;
            this.id = id;
        }

        public int compareTo(Event e) {
            if (x != e.x) return x - e.x;
            return Boolean.valueOf(type).compareTo(e.type);
        }

        public String toString() {
            return "[x=" + x + 
                " y1=" + y1 + 
                " y2=" + y2 +
                " id=" + id +
                " type=" + type +
                "]";
        }
    }

    class Node {
        short value;
        // 0 - clear, 1 - push
        byte action;
//        byte action;
//        boolean clear;
//        boolean pushValue;
        Node prev;

        Node() {}
    }

    List<Node> nodes = new ArrayList<Node>(100000);
    int free = 0;

    Node getNode() {
        Node n;
        if (free < nodes.size()) {
            n = nodes.get(free);
        } else{
            n = new Node();
            nodes.add(n);
        }
        n.value = 0;
        n.action = -1;
        n.prev = null;
        ++free;
        return n;
    }

    class SegmentTree {
        Node[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new Node[n * 4];
            for (int i = 0; i < tree.length; ++i) {
                tree[i] = getNode();
                tree[i].value = (short)61000;
            }
        }
        
        int getParent(int x) {
            return getParent(0, 0, n - 1, x);
        }

        void push(int idx) {
            int left = idx * 2 + 1;
            int right = idx * 2 + 2;
            if (left < tree.length) {
                push(idx, left);
            }
            if (right < tree.length)
                push(idx, right);
            tree[idx].action = -1;
        }

        void push(int idx, int left) {
            if (tree[idx].action == 0) {
                tree[idx] = tree[idx].prev;
                if (tree[left].action >= 0)
                    push(left);
                tree[left].action = -1;
                push(idx);
            }
            if (tree[idx].action == 1) {
                if (tree[left].action >= 0)
                    push(left);
                Node newN = getNode();
                newN.value = tree[idx].value;
                newN.action = 1;
                newN.prev = tree[left];
                tree[left] = newN;
            }
        }

        int getParent(int idx, int l, int r, int x) {
//            dbg("idx = " + idx + " l= " + l + " r=" + r + " x = " + x);
//            assert 0 <= l && l <= x && x <= r  && r < n;
            if (tree[idx].action == 1) {
                return tree[idx].value;
            }
            push(idx);
            if (l == r) {
                return tree[idx].value; 
            } else {
                int m = (l + r) >> 1;
                if (x <= m)
                    return getParent(idx * 2 + 1, l, m, x);
                else
                    return getParent(idx * 2 + 2, m + 1, r, x);
            }
        }
        void add(int y1, int y2, int id) {
            add(0, y1, y2, 0, n - 1, id);
        }
        void add(int idx, int lf, int rg, int l, int r, int id) {
            push(idx);
            if (lf == l && rg == r) {
                Node node = getNode();
                node.value = (short) id;
                node.action = 1;
                node.prev = tree[idx];
                tree[idx] = node;
            } else {
                int m = (l + r) >> 1;
                if (rg <= m)
                    add(idx * 2 + 1, lf, rg, l, m, id);
                else if (lf > m) 
                    add(idx * 2 + 2, lf, rg, m + 1, r, id);
                else {
                    add(idx * 2 + 1, lf, m, l, m, id);
                    add(idx * 2 + 2, m + 1, rg, m + 1, r, id);
                }
            }
        }

        void clear(int y1, int y2) {
            clear(0, y1, y2, 0, n - 1);
        }

        void clear(int idx, int lf, int rg, int l, int r) {
            push(idx);
            if (l == lf && r == rg) {
                tree[idx].action = 0;
            } else {
                int m = (l + r) >> 1;
                if (rg <= m)
                    clear(idx * 2 + 1, lf, rg, l, m);
                else if (lf > m)
                    clear(idx * 2 + 2, lf, rg, m + 1, r);
                else {
                    clear(idx * 2 + 1, lf, m, l, m);
                    clear(idx * 2 + 2, m + 1, rg, m + 1, r);
                }
            }
        }
    }

    void solve() throws IOException {
        Timer t = new Timer();
        t.start();
        n = ni();
        int w = ni();
        int h = ni();
        List<Event> events = new ArrayList<Event> (n * 2 + 2);
        events.add(new Event(0, 0, h, false, 0));
        events.add(new Event(w, 0, h, true, 0));
        long []volume = new long[n + 1];
        TreeSet<Integer> set = new TreeSet<Integer>();
        volume[0] = (long) w * (long) h;
        set.add(0);
        set.add(h);
        for (int i = 0; i < n; ++i) {
            int x1 = ni(), y1 = ni();
            int x2 = ni(), y2 = ni();
            set.add(y1);
            set.add(y2);
            int xl = min(x1, x2), xr = max(x1, x2);
            int yl = min(y1, y2), yr = max(y1, y2);
            events.add(new Event(xl, yl, yr, false, i + 1));
            events.add(new Event(xr, yl, yr, true, i + 1));
            volume[i + 1] = (long) abs(xr - xl) * (long)(yr - yl);
        }
        int []arrayY = new int[set.size()];
        int ptr = 0;
        for (int a : set)
            arrayY[ptr++] = a;
        t.start();
        Collections.sort(events);
        t.print();
//        dbg(events);
        SegmentTree tree = new SegmentTree(set.size());
        ArrayList<Integer> []g = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i)
            g[i] = new ArrayList<Integer>();
        t.start();
        for (Event e : events) {
            if (!e.type) {
                int parent = tree.getParent(getId(arrayY, e.y1)) & 0xFFFF;
//                dbg(e + " parent=" + parent);
                if (parent != 61000)
                    g[parent].add(e.id);
                tree.add(getId(arrayY, e.y1), getId(arrayY, e.y2), e.id);
            } else {
                tree.clear(getId(arrayY, e.y1), getId(arrayY, e.y2));
            }
        }
//        dbg(Arrays.deepToString(g));
        List<Long> result = new ArrayList<Long>();
        t.print();
        bfs(g, 0, volume, result);
        Collections.sort(result);
        for (long a : result)
            out.print(a + " ");
    }

    int getId(int []arr, int y) {
        return Arrays.binarySearch(arr, y);
    }

    void bfs(List<Integer> []g, int v, long[] volume, List<Long> result) {
        int []q = new int[g.length];
        int b = 0, e = 0;
        for(q[e++] = v; b < e; ++b) {
            v = q[b];
            long sum = 0;
            for (int u : g[v]) {
                q[e++] = u;
                sum += volume[u];
            }
            result.add(volume[v] - sum);
        }
    }

    void test() throws IOException {
        PrintWriter out = new PrintWriter("test.txt");
        int n = 60000;
        out.println(n);
        out.println("100000000 100000000");
        int x1 = 1, y1 = 1, xn = 99999999, yn = 99999999;
        for (int i = 0; i < n; ++i) {
            out.println(x1 + " " + y1 + " " + xn + " " + yn);
            ++x1; ++y1;
            --xn;
            --yn;
        }
        out.close();
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        //test();
        in = new BufferedReader(new InputStreamReader(System.in));
//        in = new BufferedReader(new FileReader("test.txt"));
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

