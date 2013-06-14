import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    class Node {
        Node l, r;
        int []idx = new int[16];
        int len;

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
        void add(int value) {
            if (len == idx.length) {
                int []newIdx = new int[len * 2];
                System.arraycopy(idx, 0, newIdx, 0, len);
                idx = newIdx;
            }
            idx[len++] = value;
        }

        void sort() {
            Arrays.sort(idx, 0, len);
        }
    }

    void add(Node root, int value, int idx) {
        for (int i = 14; i >= 0; --i) {
            boolean bit = getBit(value, i);
            if (!root.hasNext(bit)) {
                root.create(bit);
            }
            root = root.next(bit);
            root.add(idx);
        }
    }

    void dfs(Node root) {
        if (root.l != null)
            dfs(root.l);
        if (root.r != null)
            dfs(root.r);
        Arrays.sort(root.idx, 0, root.len);
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
                int pos = pos(next, r);
                if (pos < 0 || next.idx[pos] < l) {
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

    int pos(Node node, int v) {
        int p = Arrays.binarySearch(node.idx, 0, node.len, v);
        return p >= 0 ? p : (-p - 2);
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
        dfs(root);
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
//		in = new BufferedReader(new FileReader("input.txt"));
//		out = new PrintWriter("out");
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
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
