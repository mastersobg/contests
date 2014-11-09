import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        int []v = new int[1000010];
        int []pos = new int[10];
        int []cnt = new int[10];
        FastReader r = new FastReader();
        StringBuilder sb = new StringBuilder();
        for (int t = r.ni(); t > 0; --t) {
            int n = r.ni();
            Arrays.fill(pos, -1);
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; ++i)
                v[i] = r.ni();
            boolean ans = false;
            int next = -1;
            gl : for (int i = n - 1; i >= 0; --i) {
                cnt[v[i]]++;
                if (v[i] < next) {
                    for (int d = v[i] + 1; d < 10; ++d) {
                        if (pos[d] != -1) {
                            cnt[d]--;
                            v[pos[d]] = v[i];
                            v[i] = d;

                            int cur = 0;
                            for(int j = i + 1; j < n; ++j) {
                                while (cnt[cur] == 0)
                                    ++cur;
                                v[j] = cur;
                                cnt[cur]--;
                            }
                            ans = true;
                            break gl;
                        }
                    }
                }
                next = v[i];
                if (pos[v[i]] == -1) {
                    pos[v[i]] = i;
                }
            }
            if (ans) {
                for (int i = 0; i < n; ++i)
                    sb.append(v[i]);
            } else
                sb.append(-1);
            sb.append('\n');
        }
        out.print(sb.toString());
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

    class FastReader {

        String cache;
        int idx = 0;

        int ni() throws IOException {
            skip();
            if (readMore())
                read();
            skip();
            int ret = 0;
            while (idx < cache.length() && Character.isDigit(cache.charAt(idx))) {
                ret = ret * 10  + (cache.charAt(idx) - '0');
                ++idx;
            }
            return ret;
        }

        void skip() {
            if (cache == null)
                return ;
            while (idx < cache.length() && !Character.isDigit(cache.charAt(idx)))
                ++idx;
        }

        boolean readMore() {
            return cache == null || idx >= cache.length();
        }

        void read() throws IOException {
            cache = in.readLine();
            idx = 0;
        }
    }

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
        String s = ns();
        int ret = 0;
        for (int i = 0; i < s.length(); ++i)
            ret = ret * 10 + (s.charAt(i) - '0');
        return ret;
		// return Integer.valueOf(ns());
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
