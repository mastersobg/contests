import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    class Tree {
        long []pos;
        long []neg;
        int n;

        Tree (int n) {
            pos = new long[4 * n];
            neg = new long[4 * n];
            this.n = n;
        }

        void update(int pos, int val) {
            upd(0, 0, n, pos, val);
        }

        void upd(int idx, int l, int r, int pst, int val) {
            if (l == r) {
                if (val > 0)
                    pos[idx] = (long)val;
                else neg[idx] = (long)-val;
            } else {
                int mid = (l + r) >> 1;
                if (pst <= mid) {
                    upd(idx * 2 + 1, l, mid, pst, val);
                } else {
                    upd(idx * 2 + 2, mid + 1, r, pst, val);
                }
                long min = Math.min(pos[idx * 2 + 1], neg[idx * 2 + 2]);
                pos[idx] = pos[idx * 2 + 1] - min + pos[idx * 2 + 2];
                neg[idx] = neg[idx * 2 + 1] + neg[idx * 2 + 2] - min;
            }
        }

        long get() {
            return neg[0];
        }
    }


	void solve() throws IOException {
        int n = ni();
        Tree t = new Tree(532800);
        for (int i = 0; i < n; ++i) {
            int value = ni();
            int day = convert(ns(), ns());
            t.update(day, value);
            out.println(-t.get());
        }
	}

    int convert(String date, String time) {
        int []a1 = convert1(date, ".");
        int []a2 = convert1(time, ":");
        int d = a1[0], m = a1[1];
        int h = a2[0], min = a2[1];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, m - 1);
        c.set(Calendar.DAY_OF_MONTH, d);
        c.set(Calendar.YEAR, 2001);
        int day = c.get(Calendar.DAY_OF_YEAR);
        return day * 24 * 60 + h * 60 + min;
    }

    int []convert1(String s, String delim) {
        StringTokenizer st = new StringTokenizer(s, delim);
        return new int[] {Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())};
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
