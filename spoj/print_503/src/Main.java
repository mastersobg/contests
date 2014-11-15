import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    int []p;

    void gen() {
        boolean []primes = new boolean[46342];
        primes[0] = primes[1] = true;
        int cnt = 0;
        for (int i = 2; i < primes.length; ++i) {
            if (!primes[i]) {
                ++cnt;
                for (int j = i * i; j < primes.length; j += i) {
                    primes[j] = true;
                }
            }
        }
        p = new int[cnt];
        int size = 0;
        for (int i = 0; i < primes.length; ++i) {
            if (!primes[i]) {
                p[size++] = i;
            }
        }
    }

    boolean []s = new boolean[1000002];

	void solve() throws IOException {
        int l = ni();
        int r = ni();
        Arrays.fill(s, true);
        for (int i = 0; i < p.length; ++i) {
            long j = l / p[i] * p[i];
            if (j < l)
                j += p[i];
            if (j == p[i])
                j += p[i];
            if (j > r)
                break;
            for (; j <= r; j += p[i]) {
                s[(int)(j - l)] = false;
            }
        }
        for (long i = l; i <= r; ++i) {
            if (s[(int)(i - l)])
                out.println(i);
        }
	}
	
	public Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
        gen();
        for (int t = ni(); t > 0; --t) {
    		solve();
        }
        out.flush();
        // runtime error if trying to close on spoj
		// in.close();
		// out.close();
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
		new Main();
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
