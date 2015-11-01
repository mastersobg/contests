import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    static final boolean DEBUG = false;

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String problem = "c";

    void solve() throws IOException {
        Read r = new Read(new FileInputStream("c.in"));
        int n = r.r();
        int m = r.r();
        int [][]v = new int[n][];
        for (int i = 0; i < n; ++i) {
            v[i] = r.r(m);
        }
        while (true) {
            try {
                int le = r.r();
                int ri = r.r();
                out.println(count(v, ri) - count(v, le - 1));
            } catch (IOException e) {
                break;
            }
        }
    }

    int count(int [][]v, int a) {
        int p = 0;
        int ret = 0;
        int n = v.length; int m = v[0].length;
        for (int i = n - 1; i >= 0; --i) {
            while (p < m && v[i][p] <= a) 
                ++p;
            ret += p;
        }
        return ret;
    }

    class Read {
        InputStream r;
        byte []buf = new byte[4 * 10000];

        Read(InputStream br) {
            r = br;
        }

        int []r(int m) throws IOException {
            int read = r.read(buf, 0, 4 * m);
            if (read == -1) {
                throw new IOException();
            }
            int []ret = new int[m];
            int pos = 0;
            for (int i = 0; i < m; ++i) {
                int value = 0;
                for (int j = 0; j < 4; ++j) {
                    value |= (buf[pos++] & 0xFF) << (8 * j);
                }
                ret[i] = value;
            }
            return ret;
        }

        int r() throws IOException {
            int read = r.read(buf, 0, 4);
            if (read == -1) {
                throw new IOException();
            }
            int ret = 0;
            for (int j = 0; j < 4; ++j) {
                ret |= (buf[j] & 0xFF) << (8 * j);
            }
            return ret;
            
        }
    }

    class Write {
        FileOutputStream w;

        Write(FileOutputStream w) {
            this.w = w;
        }

        void w(int a) throws IOException {
            for (int i = 0; i < 4; ++i) {
                int x = a & 0xFF;
                w.write(x);
                a >>= 8;
            }
            w.flush();
        }
    }

    void createFile(BufferedReader in, Write out) throws IOException {
        String s;
        while ((s = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            while (st.hasMoreTokens()) {
                int value = Integer.valueOf(st.nextToken());
                out.w(value);
            }
        }

    }

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


    public void run() throws IOException {
        Locale.setDefault(Locale.US);
//        in = new FileReader(problem + ".in");
        out = new PrintWriter(System.out);
//        createFile(new BufferedReader(new FileReader("a")), new Write(new FileOutputStream(problem + ".in")));
        solve();
//        in.close();
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
}

