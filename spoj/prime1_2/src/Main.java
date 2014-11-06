import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        List<Integer> primes = getPrimes((int) sqrt(1000000000.0) + 1);
        dbg(primes);
        int tests = ni();
        for (int t = 1; t <= tests; ++t) {
            int l = max(2, ni());
            int r = ni();
            if (t > 1) {
                out.println();
            }
            for (int i = l; i <= r; ++i) {
                boolean prime = true;
                for (int j = 0; j < primes.size(); ++j) {
                    int p = primes.get(j);
                    if (p * p > i)
                        break;
                    if (i % p == 0) {
                        prime = false;
                        break;
                    }
                }
                if (prime)
                    out.println(i);
            }
        }
	}

    List<Integer> getPrimes(int n) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 2; i <= n; ++i) {
            boolean prime = true;
            for (int j = 2; j * j <= i; ++j) 
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            if (prime)
                list.add(i);
        }
        return list;
    }
	
	public Main() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
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
