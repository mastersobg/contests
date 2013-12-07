import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n, m, p;

    class P implements Comparable<P> {
        String name;
        int s, h;
        int score;
        int t;

        public int compareTo(P o) {
            if (s != o.s)
                return o.s - s;
            return o.h - h;
        }

        public String toString() {
            return "[" + name + 
                " " + score + "]";
        }
    }

    void solve() throws IOException {
        n = ni();
        m = ni();
        p = ni();
        P []p = new P[n];
        for (int i = 0; i < n; ++i) {
            P a = new P();
            a.name = ns();
            a.s = ni();
            a.h = ni();
            p[i] = a;
        }
        Arrays.sort(p);
        List<P> t1 = new ArrayList<P>(), t2 = new ArrayList<P>();
        for (int i = 0; i < n; ++i) {
            p[i].score = i + 1;
            if ((p[i].score & 1) == 1)
                t1.add(p[i]);
            else t2.add(p[i]);
        }

        List<P> inGame1 = new ArrayList<P>(), inGame2 = new ArrayList<P>();
        for (int i = 0, j = t1.size() - 1; i < this.p; ++i, --j) {
            inGame1.add(t1.get(j));
            t1.remove(j);
        }
        for (int i = 0, j = t2.size() - 1; i < this.p; ++i, --j) {
            inGame2.add(t2.get(j));
            t2.remove(j);
        }

        for (int it = 0; it < m; ++it) {
            inc(inGame1); 
            inc(inGame2);

            change(t1, inGame1);
            change(t2, inGame2);
        }

        List<String> ans = new ArrayList<String>();
        for (P pt : inGame1)
            ans.add(pt.name);
        for (P pt : inGame2) {
            ans.add(pt.name);
        }
        Collections.sort(ans);
        for (int i = 0; i < ans.size(); ++i) {
            if (i > 0) out.print(" ");
            out.print(ans.get(i));
        }
        out.println();
    }

    void change(List<P> t, List<P> in) {
        if (t.size() == 0) return ;
        int time1 = -1;
        int score1 = -1;
        int idx1 = -1;
        for (int i = 0; i < in.size(); ++i) {
            P p = in.get(i);
            if (p.t > time1) {
                time1 = p.t;
                score1 = p.score;
                idx1 = i;
            } else if (p.t == time1 && p.score > score1) {
                time1 = p.t;
                score1 = p.score;
                idx1 = i;
            }
        }
        P remove = in.get(idx1);
        in.remove(idx1);
        t.add(remove);

        int time2 = 1 << 29;
        int score2 = 1 << 29;
        int idx2 = -1;
        for (int i = 0; i < t.size(); ++i) {
            P p = t.get(i);
            if (p.t < time2) {
                time2 = p.t;
                score2 = p.score;
                idx2 = i;
            } else if (p.t == time2 && p.score < score2) {
                time2 = p.t;
                score2 = p.score;
                idx2 = i;
            }
        }

        P add = t.get(idx2);
        t.remove(idx2);
        in.add(add);
    }

    void inc(List<P> l) {
        for (P p : l)
            p.t++;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
        int t = ni();
        for (int i = 1; i <= t; ++i) {
            out.print("Case #" + i + ": ");
            solve();
        }
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
        if ("true".equals(System.getProperty("LOCAL_DEBUG"))) {
            DEBUG = true;
        }
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

