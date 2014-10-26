import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        for (int t = ni(); t > 0; --t) {
            out.println(solve(ns()));
        }
	}

    char []stack = new char[400];

    String solve(String s) {
        StringBuilder ret = new StringBuilder();
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                ret.append(c);
            } else if (c == '(') {
                stack[idx++] = c;
            } else if (c == ')') {
                while (stack[idx - 1] != '(') {
                    ret.append(stack[--idx]);
                }
                --idx;
            } else {
                int p = priority(c);
                while (idx > 0 && priority(stack[idx - 1]) >= p) {
                    ret.append(stack[--idx]);
                }
                stack[idx++] = c;
            }
        }
        while (idx > 0) {
            ret.append(stack[--idx]);
        }
        return ret.toString();
    }

    int priority(char c) {
        if (c == '+' || c == '-')
            return 0;
        if (c == '*' || c == '*')
            return 1;
        if (c == '(')
            return -1;
        return 2;
    }

    public Main() throws IOException {
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
