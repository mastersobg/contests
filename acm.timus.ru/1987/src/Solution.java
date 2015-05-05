import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    static class Event implements Comparable<Event> {
        int x;
        byte type; // 0 - open, 1 - point, 2 - close
        int idx;

        Event(int x, byte type, int idx) {
            this.x = x;
            this.type = type;
            this.idx = idx;
        }

        public int compareTo(Event e) {
            if (x == e.x) {
                return (int) (type - e.type);
            }
            return x - e.x;
        }

        @Override
        public String toString() {
            return "[x=" + x + ", type=" + type + ", idx=" + idx + "]";
        }
    }

	void solve() throws IOException {
        int n = ni();
        List<Event> list = new ArrayList<Event> ();
        for (int i = 0; i < n; ++i) {
            int a = ni(), b = ni();
            list.add(new Event(a, (byte) 0, i + 1));
            list.add(new Event(b, (byte) 2, i + 1));
        }
        int m = ni();
        for (int i = 0; i < m; ++i) {
            list.add(new Event(ni(), (byte) 1, i + 1));
        }
        Collections.sort(list);
        int []stack = new int[list.size()];
        int top = 0;
        dbg(list);
        for (Event e : list) {
            if (e.type == 0) {
                stack[top++] = e.idx;
            } else if (e.type == 2) {
                --top;
            } else {
                if (top == 0)
                    out.println(-1);
                else 
                    out.println(stack[top - 1]);
            }
        }
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
