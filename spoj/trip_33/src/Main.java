import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	void solve() throws IOException {
        boolean first = true;
        for (int t = ni(); t > 0; --t) {
            if (first)
                out.println();
            first = false;
            char []s1 = ns().toCharArray();
            char []s2 = ns().toCharArray();
            int [][]dp = new int[s1.length + 1][s2.length + 1];
            List<Integer> [][]prev = new List[s1.length + 1][s2.length + 1];
            for (int i = 0; i < dp.length; ++i) {
                Arrays.fill(dp[i], Integer.MIN_VALUE);
                for (int j = 0; j < dp[i].length; ++j) {
                    prev[i][j] = new ArrayList<Integer>();
                }
            }
            dp[0][0] = 0;
            for (int i = 0; i <= s1.length; ++i) {
                for (int j = 0; j <= s2.length; ++j) {
                    relax(dp, prev, i, j, i + 1, j, dp[i][j]);
                    relax(dp, prev, i, j, i, j + 1, dp[i][j]);
                    if (i < s1.length && j < s2.length && s1[i] == s2[j]) {
                        relax(dp, prev, i, j, i + 1, j + 1, dp[i][j] + 1);
                    }
                }
            }
            TreeSet<String> ans = new TreeSet<String>();
            restore(s1, prev, s1.length, s2.length, "", ans);
            for (String s : ans)
                out.println(s);
        }
	}


    static class Entry {

        int i, j;
        String s;

        Entry(int a, int b, String c) {
            i = a;
            j = b;
            s = c;
        }
        @Override
        public boolean equals(Object o) {
            Entry e = (Entry) o;
            return i == e.i && j == e.j && s.equals(e.s);
        }

        @Override
        public int hashCode() {
            return i * 31 * 31 + j * 31 + s.hashCode();
        }

    }

    HashSet<Entry> cache = new HashSet<Entry>();

    void restore(char []s, List<Integer> [][]pred, int i, int j, String word, Set<String> words) {
        if (cache.contains(new Entry(i, j, word))) {
            return;
        }

        if (i == 0 && j == 0) {
            words.add(word);
            return ;
        }
        for (int idx = 0; idx < pred[i][j].size(); idx += 2) {
            int ni = pred[i][j].get(idx);
            int nj = pred[i][j].get(idx + 1);
            if (ni != i && nj != j) {
                restore(s, pred, ni, nj, s[ni] + word, words);
            } else {
                restore(s, pred, ni, nj, word, words);
            }
        }
    }

    void relax(int [][]dp, List<Integer> [][]prev, int i, int j, int ni, int nj, int value) {
        if (ni < dp.length && nj < dp[ni].length) {
            if (dp[ni][nj] <= value) {
                if (dp[ni][nj] < value)
                    prev[ni][nj].clear();    
                dp[ni][nj] = value;
                prev[ni][nj].add(i);
                prev[ni][nj].add(j);
            }
        }
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
