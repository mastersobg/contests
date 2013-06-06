import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

    static class Map {

        final String RIGHT = "RIGHT";
        final String UP = "UP";
        final String DOWN = "DOWN";
        final String LEFT = "LEFT";
        List<String> moves = new ArrayList<String>();

        int [][]v;
        int x, y;

        Map(int n) {
            v = new int[n][n];
        }

        void upd(int dx, int dy) {
            assert dx == 0 || dy == 0 : "" + dx + " " + dy;
            assert abs(dx) == 1 || abs(dy) == 1 : "" + dx + " " + dy;
            x += dx;
            y += dy;
            if(dx == 1)
                moves.add(DOWN);
            if(dx == -1)
                moves.add(UP);
            if(dy == 1)
                moves.add(RIGHT);
            if(dy == -1)
                moves.add(LEFT);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < v.length; ++i) {
                for(int j = 0; j < v[i].length; ++j) {
                    if(v[i][j] < 10)
                        sb.append(' ');
                    sb.append(v[i][j] + " ");
                }
                sb.append('\n');
            }
            return sb.toString();
        }

        void moveTo(int i, int j) {
            while(x > i) {
                v[x][y] = v[x - 1][y];
                v[x - 1][y] = 0;
                upd(-1, 0);
            }
            while(x < i) {
                v[x][y] = v[x + 1][y];
                v[x + 1][y] = 0;
                upd(1, 0);
            }
            while(y > j) {
                v[x][y] = v[x][y - 1];
                v[x][y - 1] = 0;
                upd(0, -1);
            }
            while(y < j) {
                v[x][y] = v[x][y + 1];
                v[x][y + 1] = 0;
                upd(0, 1);
            }
        }

        void shift(int dx, int dy) {
            moveTo(x + dx, y + dy);
        }
    }
    
    void moveRightDown(Map map, int x, int y) {
        map.moveTo(x, y + 1);
        map.shift(0, -1);
        for(int it = 0; it < (map.v.length - y - 2); ++it) {
            
        }
        dbg(map);
    }
	void solve() throws IOException {
        Map map = new Map(5);
        for(int i = 0; i < 5; ++i)
            for(int j = 0; j < 5; ++j)
                map.v[i][j] = i * 5 + j;
        map.x = map.y = 0;
        dbg(map);
        moveRightDown(map, 1, 1);
	}

    boolean DEBUG = true;
    
    void dbg(Object ...args) {
      if(!DEBUG)
        return;
      for(Object o : args)
        System.err.print(o + " ");
      System.err.println();
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
