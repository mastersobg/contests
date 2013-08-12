import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  static class Point implements Comparable<Point> {
    int x, y;
    int idx;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int compareTo(Point p) {
      return x - p.x;
    }

    public String toString() {
      return "[x=" + x + " y=" + y + " idx=" + idx + "]";
    }
  }

  static class SegmentTree {
    int [][] tree;

    SegmentTree(int size) {
      tree = new int[size * 4][];
    }

    void build(int l, int r, int idx, int [][]v) {
      if (l == r) {
        tree[idx] = new int[v[l].length];
        for (int i = 0; i < v[l].length; ++i) {
          tree[idx][i] = v[l][i];
        }
      } else {
        int m = (l + r) >> 1;
        build(l, m, idx * 2 + 1, v);
        build(m + 1, r, idx * 2 + 2, v);
        tree[idx] = merge(tree[idx * 2 + 1], tree[idx * 2 + 2]);
      }
    }
    
    boolean can(int l, int r, int lf, int rg, int idx, int y1, int y2) {
/*      if (!(0 <= l && l <= lf && lf <= rg && rg <= r && r < tree.length / 4)) {
        dbg("", new boolean[] {0 <= l, l <= lf, lf <= rg, rg <= r, r < tree.length});
        dbg("r=", r);
        dbg("rg=", rg);
        dbg("tree.length=", tree.length / 4);
        dbg("idx=", idx);
        System.exit(0);
      }
*/      if (l == lf && r == rg) {
        int []v = tree[idx];
        int pos = Arrays.binarySearch(v, y1);
        if (pos >= 0) {
          return !(pos + 1 < v.length && v[pos + 1] < y2);
        } else {
          pos = -pos - 1;
          return pos == v.length || v[pos] >= y2;
        }
      } else {
        int m = (l + r) >> 1;
        if (rg <= m) {
          return can(l, m, lf, rg, idx * 2 + 1, y1, y2);
        } else if (lf > m) {
          return can(m + 1, r, lf, rg, idx * 2 + 2, y1, y2);
        } else {
          return can(l, m, lf, m, idx * 2 + 1, y1, y2) &
            can(m + 1, r, m + 1, rg, idx * 2 + 2, y1, y2);
        }
      }
    }
    
    int []merge(int []s1, int []s2) {
      int p1 = 0, p2 = 0, p = 0;
      int []dest = new int[s1.length + s2.length];
      while (p1 < s1.length && p2 < s2.length) {
        if (s1[p1] <= s2[p2]) {
          dest[p++] = s1[p1++];
        } else {
          dest[p++] = s2[p2++]; 
        }
      }
      while (p1 < s1.length) {
        dest[p++] = s1[p1++];
      }
      while (p2 < s2.length) {
        dest[p++] = s2[p2++];
      }
      return dest;
    }

  }


  int w, h, n, q;
  Point []pts;

	void solve() throws IOException {
    w = ni();
    h = ni();
    n = ni();
    q = ni();
    pts = new Point[n];
    for (int i = 0; i < n; ++i) {
      pts[i] = new Point(ni(), ni());
    }
    int [][]p = scale();
    removeDuplicates(p);
//    dbg("", pts);
//    dbg("p=", p);
    SegmentTree tree = new SegmentTree(p.length);
    tree.build(0, p.length - 1, 0, p);
    for (int it = 0; it < q; ++it) {
      int x = ni();
      int y = ni();
      int l = 1, r = 1000000000;
      while (l + 1 < r) {
        int m = (l + r) >> 1;
        if (can(tree, p.length - 1, x, y, m)) {
          l = m;
        } else {
          r = m;
        }
      }
      int ret;
      if (can(tree, p.length - 1, x, y, r)) {
        ret = r;
      } else if (can(tree, p.length - 1, x, y, l)) {
        ret = l;
      } else {
        ret = 0;
      }
      out.println(ret * 2);
      if (!check(x, y, ret)) {
        dbg("fail");
        System.exit(0);
      }
    }

	}

  boolean check(int x, int y, int d) {
    int x1 = x - d;
    int x2 = x + d;
    int y1 = y - d;
    int y2 = y + d;
    for (int i = 0; i < n; ++i) {
      x = pts[i].x;
      y = pts[i].y;
      if (x > x1 && x < x2 && y > y1 && y < y2) {
        return false;
      }
    }
    return true;
  }

  void removeDuplicates(int [][]p) {
    for (int it = 0; it < p.length; ++it) {
      int []v = p[it];
      HashSet<Integer> set = new HashSet<Integer> ();
      for (int i = 0; i < v.length; ++i) {
        set.add(v[i]);
      }
      int []nv = new int[set.size()];
      int idx = 0;
      for (int a : set) {
        nv[idx++] = a;
      }
      Arrays.sort(nv);
      p[it] = nv;
    }
  }

  Point POINT = new Point(0, 0);

  boolean can(SegmentTree tree, int r, int x, int y, int d) {
    if (x - d < 0 || x + d > w || y - d < 0 || y + d > h) {
      return false;
    }
    POINT.x = x - d;
    int lf = Arrays.binarySearch(pts, POINT);
    if (lf >= 0) {
      lf = pts[lf].idx + 1;
    } else {
      lf = -lf - 1;
      if (lf < pts.length) {
        lf = pts[lf].idx;
      }
    }
    POINT.x = x + d;
    int rg = Arrays.binarySearch(pts, POINT);
    if (rg >= 0) {
      rg = pts[rg].idx - 1;
    } else {
      rg = -rg - 2;
      if (rg >= 0) {
        rg = pts[rg].idx;
      }
    }
    if (lf > r || rg < 0 || lf > rg) {
      return true;
    }
    return tree.can(0, r, lf, rg, 0, y - d, y + d);
  }

  int [][] scale() {
    Arrays.sort(pts);
    int []x = new int [n];
    x[0] = 0;
    for (int i = 1; i < n; ++i) {
      x[i] = x[i - 1] + (pts[i].x == pts[i - 1].x ? 0 : 1);
    }
    int [][]ret = new int[x[n - 1] + 1][];
    int p = 0;
    int idx = 0;
    while (p < n) {
      int cnt = 0;
      while (p + cnt < n && x[p + cnt] == x[p]) {
        ++cnt;
      }
      ret[idx] = new int[cnt];
      for (int i = 0; i < cnt; ++i) {
        ret[idx][i] = pts[p + i].y;
        pts[p + i].idx = idx;
      }
      p += cnt;
      ++idx;
    }
    return ret;
  }

  static void dbg(Object ... objs) {
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

  static String arrayToString(Object o) {
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
  

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
    generate();
    in = new BufferedReader(new FileReader("test.txt"));
//		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

  void generate() throws IOException {
    PrintWriter out = new PrintWriter("test.txt");
    out.println("1000000 2500 100000 100000");
    Random rnd = new Random();
    for (int i = 0; i < 200000; ++i) {
      out.println((rnd.nextInt(999999) + 1) + " " + (rnd.nextInt(2499) + 1));
    }
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
    while (true) {
      dbg("new");
		  new Solution().run();
    }
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
}

