import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    class Node {
        Node l, r;

        TreeSet<Integer> idx = new TreeSet<Integer> ();

        Node next(boolean next) {
            return next ? r : l;
        }
        boolean hasNext(boolean bit) {
            return next(bit) != null;
        }

        void create(boolean bit) {
            if (bit)
                r = new Node();
            else
                l = new Node();
        }
    }

    void add(Node root, int value, int idx) {
        for (int i = 14; i >= 0; --i) {
            boolean bit = getBit(value, i);
            if (!root.hasNext(bit)) {
                root.create(bit);
            }
            root = root.next(bit);
            root.idx.add(idx);
        }
    }

    boolean getBit(int value, int bit) {
        return (value & (1 << bit)) != 0;
    }
    int setBit(int value, int bit, boolean b) {
        if (!b)
            return value;
        return value | (1 << bit);
    }
    int calc(Node root, int a, int l, int r) {
        int ret = 0;
        for (int i = 14; i >= 0; --i) {
            boolean bit = getBit(a, i);
            if (!root.hasNext(bit)) {
                ret = setBit(ret, i, !bit);
                root = root.next(!bit);
            } else {
                Node next = root.next(bit);
                Integer p = next.idx.floor(r);
                if (p == null || p < l) {
                    ret = setBit(ret, i, !bit);
                    root = root.next(!bit);
                } else {
                    ret = setBit(ret, i, bit);
                    root = next;
                }
            }
        }
        return ret;
    }

	void solve() throws IOException {
        int n = ni();
        int q = ni();
        Node root = new Node();
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; ++i) {
            int value = ni();
            add(root, value, i);
        }
        System.err.println("build=" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int it = 0; it < q; ++it) {
            int a = ni();
            int l = ni() - 1;
            int r = ni() - 1;
            int ret = calc(root, ~a, l, r);
            out.println(a ^ ret);
        }
        System.err.println("get=" + (System.currentTimeMillis() - start));
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("out");
        long start = System.currentTimeMillis();
        for (int t = ni(); t > 0; --t) {
		    solve();
        }
        System.err.println(System.currentTimeMillis() - start);
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
