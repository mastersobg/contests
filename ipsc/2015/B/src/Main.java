import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    static class Pair {
        String s;
        int v;

        Pair(String a, int b) {
            s = a;
            v = b;
        }
    }

    Pair []v1, v2, v3;
    Map<String, Integer> all = new HashMap<String, Integer>();
    Map<Integer, ArrayList<int[]>> map = new HashMap<>();
    HashSet<Triple> used = new HashSet<>();

    class Triple {
        int a, b, c;

        Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return a * 31 * 31 + b * 31 + c;
        }

        @Override
        public boolean equals(Object q) {
            Triple o = (Triple) q;
            return a == o.a && b == o.b && c == o.c;
        }
    }

    void solve() throws IOException {
        v1 = read();
        v2 = read();
        v3 = read();
        for (int i = 0; i < v1.length; ++i)
            for (int j = 0; j < v2.length; ++j) {
                int sum = v1[i].v + v2[j].v;
                ArrayList<int[]> list = map.get(sum);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(sum, list);
                }
                list.add(new int[] {i, j});
            }
        int n = ni();
        for (int it = 0; it < n; ++it) {
            String s1 = ns(), s2 = ns(), s3 = ns();
            int sum = getSum(s1, s2, s3) + 1;
            gl : for (int i = 0; i < v3.length; ++i) {
                ArrayList<int[]> list = map.get(sum - v3[i].v);
                if (list != null && list.size() > 0) {
                    for (int []a : list) {
                        Triple t = new Triple(a[0], a[1], i);
                        if (!used.contains(new Triple(a[0], a[1], i))) {
                            used.add(t);
                            out.println(v1[a[0]].s + " " + v2[a[1]].s + " " + v3[i].s);
                            break gl;
                        }
                    }
                }
            }
        }

    }

    int getSum(String s1, String s2, String s3) {
        return all.get(s1) + all.get(s2) + all.get(s3);
    }

    Pair [] read() throws IOException {
        int n = ni();
        Pair []v = new Pair[n];
        for (int i = 0; i < n; ++i) {
            v[i] = new Pair(ns(), ni());
            all.put(v[i].s, v[i].v);
        }
        return v;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new FileReader("b2.in"));
        out = new PrintWriter("b2.out");
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
        new Main().run();
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

