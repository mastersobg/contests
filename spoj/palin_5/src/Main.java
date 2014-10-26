import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        gl: for (int k = ni(); k > 0; --k) {
            char []s = ns().toCharArray();
            boolean []palin = isPalin(s);
            int l = 0, r = s.length - 1;
            while (l < r) {
                if (s[r] < s[l] && palin[l + 1]) {
                    for (int i = 0; i <= l; ++i) {
                        s[s.length - i - 1] = s[i];
                    }
                    int number = 0;
                    for (int i = 0; i < s.length; ++i) {
                        out.print(s[i]);
                        number = number * 10 + (s[i] - '0');
                    }
                    out.println();
                    continue gl;
                } 
                ++l;
                --r;
            }
            while (r >= 0) {
                if (s[r] < '9') {
                    s[r]++;
                    for (int i = r + 1; i <= (s.length - 1) >> 1; ++i) {
                        s[i] = '0';
                    }
                    for (int i = 0; i <= (s.length - 1) >> 1; ++i) {
                        s[s.length - 1 - i] = s[i];
                    }
                    int number = 0;
                    for (int i = 0; i < s.length; ++i) {
                        out.print(s[i]);
                        number = number * 10 + (s[i] - '0');
                    }
                    out.println();
                    continue gl;
                }
                --r;
                ++l;
            }

            int number = 1;
            out.print('1');
            for (int i = 1; i < s.length; ++i) {
                out.print("0");
                number = number * 10;
            }
            out.print(1);
            number = number * 10 + 1;
            out.println();
        }
	}

    boolean []isPalin(char []s) {
        int len = (s.length - 1) >> 1;
        int next = s.length >> 1;
        boolean []ret = new boolean[len + 2];
        ret[len + 1] = true;
        for (; len >= 0; --len, ++next) {
            ret[len] = ret[len + 1] & s[len] == s[next];
        }
        return ret;
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
        System.err.flush();
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
