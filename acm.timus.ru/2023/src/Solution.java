import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    HashMap<String, Integer> map = new HashMap<>();

    void init() {
        map.put("Alice", 0);
        map.put("Ariel", 0);
        map.put("Aurora", 0);
        map.put("Phil", 0);
        map.put("Peter", 0);
        map.put("Olaf", 0);
        map.put("Phoebus", 0);
        map.put("Ralph", 0);
        map.put("Robin", 0);

        map.put("Bambi", 1);
        map.put("Belle", 1);
        map.put("Bolt", 1);
        map.put("Mulan", 1);
        map.put("Mowgli", 1);
        map.put("Mickey", 1);
        map.put("Silver", 1);
        map.put("Simba", 1);
        map.put("Stitch", 1);
    }

	void solve() throws IOException {
        init();
        int ret = 0;
        int cur = 0;
        int n = ni();
        for (int i = 0; i < n; ++i) {
            String s = ns();
            Integer a = map.get(s);
            if (a == null) {
                a = 2;
            }

            ret += Math.abs(cur - a);
            cur = a;
        }
        out.println(ret);
	}
	
	public Solution() throws IOException {
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
		new Solution();
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
