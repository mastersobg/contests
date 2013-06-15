import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    int n;
    int []v;

    class El implements Comparable<El> {
        int value;
        int cnt;

        public int compareTo(El e) {
            if (e.cnt != cnt)
                return e.cnt - cnt;
            return Integer.valueOf(value).compareTo(e.value);
        }

		@Override
		public String toString() {
			return "El [value=" + value + ", cnt=" + cnt + "]";
		}
    }

	void solve() throws IOException {
        n = ni();
        v = new int[n];
        for (int i = 0; i < n; ++i)
            v[i] = ni();
        for (int i = 31; i >= 0; --i) {
            if (can(i)) {
                out.println(i);
                return ;
            }
        }
        out.println(-1);
	}

    boolean can(int shift) {
        HashMap<Integer, El> map = new HashMap<Integer, El> ();
        TreeSet<El> set = new TreeSet<El> ();
        for (int i = 0; i < n; ++i) {
            int value = v[i] >> shift;
            El a = null;
            if (map.containsKey(value)) {
                a = map.get(value);
            }
            if (a == null) {
                a = new El();
                a.value = value;
                a.cnt = 1;
                map.put(value, a);
                set.add(a);
            } else {
                set.remove(a);
                a.cnt++;
                set.add(a);
            }
        }
        int first = -1;
        int prev = -1;
        for (int i = 0; i < n; ++i) {
            El e = set.pollFirst();
            if (i == 0) {
                first = e.value;
                e.cnt--;
                prev = first;
                if (e.cnt > 0)
                    set.add(e);
            } else if (i == n - 1) {
                if (e.value == first) {
                    return false;
                }
            } else {
                if (e.value == prev) {
                    El e1 = set.pollFirst();
                    set.add(e);
                    e = e1;
                }
                if (e == null)
                    return false;
                e.cnt--;
                if (e.cnt > 0)
                    set.add(e);
                prev = e.value;
            }
        }
        return true;
    }

	public void run() throws IOException {
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
		new Solution().run();
	}
}
