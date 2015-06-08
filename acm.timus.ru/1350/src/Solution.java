import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    Map<String, Integer> idMap = new HashMap<String, Integer>();

    int getId(String s) {
        Integer id = idMap.get(s);
        if (id == null) {
            idMap.put(s, idMap.size());
            return idMap.size() - 1;
        }
        return id;
    }

	void solve() throws IOException {
        int n = ni(); 
        Set<Integer> all = read(n);
        
        int k = ni();
        int cnt = ni();
        Set<Integer> safe = read(cnt);
        Set<Integer> []v = new HashSet[k];
        for (int i = 0; i < k; ++i) {
            v[i] = read(ni());
        }
        int m = ni();

        for (int i = 0; i < k; ++i) {
            if (subSet(safe, v[i])) {
                out.println("YES");
            }
        }

	}

    boolean subSet(Set<Integer> master, Set<Integer> slave) {
        return master.containsAll(slave);
    }

    Set<Integer> read(int n) throws IOException {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            set.add(getId(ns()));
        }
        return set;
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
