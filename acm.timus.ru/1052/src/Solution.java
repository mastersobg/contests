import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  static class Point {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    
    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
  }
  
  int n;
  Point []pts;

	void solve() throws IOException {
    n = ni();
    pts = new Point[n];
    for(int i = 0; i < n; ++i) {
      pts[i] = new Point(ni(), ni());
    }
    
    int theBest = 0;
    for(int basePoint = 0; basePoint < n; ++basePoint) {
      Point base = pts[basePoint];
      HashMap<Point, Integer> map = new HashMap<Point, Integer> ();
      for(int i = 0; i < n; ++i) {
        if(i != basePoint) {
          int nx = pts[i].x - base.x;
          int ny = pts[i].y - base.y;
          int gcd = gcd(nx, ny);
          Point np = new Point(nx / gcd, ny / gcd);
          updateMap(map, np);
        }
      }
      for(Integer value : map.values()) {
        theBest = max(theBest, value);
      }
    }
    out.println(theBest + 1);
	}

  void updateMap(Map<Point, Integer> map, Point p) {
    if(!map.containsKey(p)) {
      map.put(p, 1);
    }
    else {
      map.put(p, map.get(p) + 1);
    }
  }

  int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
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
		new Solution();
	}
}
