import java.io.*;
import java.util.*;
import java.math.BigInteger;

import static java.lang.Math.*;

public class Program {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    class Number {
        int []digits;
        
        Number(long a) {
            int []t = new int[100];
            int idx = 0;
            while (a > 0) {
                t[idx++] = (int) (a % 10L);
                a /= 10L;
            }
            digits = new int[idx];
            for (int i = 0; i < idx; ++i) {
                digits[i] = t[i];
            }
        }

        void substract() {
            int p = 0;
            while (p < digits.length && digits[p] == 0)
                ++p;
            if (p == digits.length) {
                digits = new int [] {0};
                return ;
            }
            int mx = Math.max(1, digits[p]);
            for (int i = p + 1; i < digits.length; ++i) {
                mx = Math.max(mx, digits[i]);
            }
            for (int i = 0; i < digits.length; ++i) {
                if (digits[i] > 0) {
                    digits[i]--;
                    if (i == digits.length - 1 && digits[i] == 0) {
                        int []t = new int[digits.length - 1];
                        for (int j = 0; j < t.length; ++j)
                            t[j] = digits[j];
                        digits = t;
                    }
                    break;
                } else {
                    digits[i] = mx;
                }
            }
        }

        long get() {
            int mx = 0;
            int pos = 0;
            for (int i = 0; i < digits.length; ++i) {
                if (digits[i] >= mx) {
                    mx = digits[i];
                    pos = i;
                }
            }
            long c = 1;
            long count = 0;
            for (int i = 0; i < pos; ++i) {
                count += c * digits[i];
                digits[i] = 0;
                c *= (mx + 1);
            }
            return count;
        }
    }

    void solve() throws IOException {
        long q = nl();
        if (q == 0) {
            out.println(0);
            return ;
        }
        // for (long q = 0; q <= 0; ++q) {
            Number n = new Number(q);
            long ret = 0;
            do {
                // dbg(n.digits);
                long a = n.get();
                // dbg(n.digits);
                // dbg("a", a);
                ret += a;
                n.substract();
                // dbg(n.digits);
                ++ret;
                // dbg("=====");
            } while (n.digits.length > 1 || 
                (n.digits.length == 1 && n.digits[0] != 0));
            // if (ret != brute((int) q)) {
            //     throw new IllegalStateException(ret + " " + q);
            // }
            out.println(ret);
            // dbg("brute", brute((int) q));
        // }
    }

    int brute(int n) {
        String []q = new String[1000000];
        HashMap<String, Integer> d = new HashMap<String, Integer> ();
        d.put("0", 0);
        int b = 0, e = 0;
        for (q[e++] = "0"; b < e; ++b) {
            String cur = q[b];
            int mx = 0;
            for (int i = 0; i < cur.length(); ++i) {
                int c = cur.charAt(i) - '0';
                mx = Math.max(mx, c);
            }
            for (int i = Math.max(mx + 1, 2); i <= 36; ++i) {
                BigInteger w = new BigInteger(cur, i);
                w = w.add(BigInteger.ONE);
                String ret = w.toString(i);
                if (ret.equals(n+"")) {
                    return d.get(cur) + 1;
                }
                if (!d.containsKey(ret)) {
                    d.put(ret, d.get(cur) + 1);
                    q[e++] = ret;
                }
            }
        }
        return -1;
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
        new Program().run();
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

    static boolean DEBUG = true;

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

