import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

  static final boolean DEBUG = true;

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

  int n, m;
  char [][]v;

  int cnt = 0, ret = 0;

	void solve() throws IOException {
    n = ni();
    m = ni();
    v = new char[n][n];
    boolean hasEmpty = false;
    for (int i = 0; i < n; ++i) {
      String s = ns();
      for (int j = 0; j < n; ++j) {
        v[i][j] = s.charAt(j);
        if (s.charAt(j) == '.')
          hasEmpty = true;
      }
    }
    cnt = ret = 0;
    int answer = sol(hasEmpty);
gl:    if (answer == 5) {
	char c = (ret & 1) == 1 ? 'X' : 'O';
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (v[i][j] != '.' && v[i][j] == c) {
            char prev = v[i][j];
            v[i][j] = '.';
            cnt = ret = 0;
            int a = sol(true);
            if (a == 2) {
              answer = c == 'X' ? 0 : 1;
              break gl;
            }
            v[i][j] = prev;
          }
        }
      }
    }
    String ans = "";
    switch (answer) {
      case 0: ans = "X WINS"; break;
      case 1: ans = "O WINS"; break;
      case 2: ans = "IN PROGRESS"; break;
      case 3: ans = "DRAW"; break;
      case 4: ans = "ERROR"; break;
    }
    out.println(ans);
	}
  
  // 0 - X wins
  // 1 - O wins
  // 2 - IN PROGRESS
  // 3 - DRAW
  // 4 - ERROR
  // 5 - possible OK
  int sol(boolean hasEmpty) {
    if (n == 1) {
      if (v[0][0] == 'X') {
        return 0;
      } else if(v[0][0] == '.') {
        return 2;
      } else {
        return 1;
      }
    }
    if (m == 1) {
      int a = 0, b = 0;
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (v[i][j] == 'X')++a;
          else if(v[i][j] == 'O') ++b;
        }
      }
      if ((a > 0 && b > 0) || a > 1 || b > 1) {
        return 4;
      }
      if (a == 0 && b == 0) {
        if (hasEmpty) {
          return 2;
        } else {
          return 3;
        }
      }
      if (a == 1)
        return 0;
      else if (b == 1)
        return 1;
    }
    int ret1 = checkHor();
    int ret2 = checkVer();
    int ret3 = diag(1, 1);
    int ret4 = diag(1, -1);
    if (cnt > 1 && cnt <= 8 && ret != 3) {
    	return 5;
    }
    if (ret1 == 3 || ret2 == 3 || ret3 == 3 || ret4 == 3) {
      return 4;
    }
    if (cnt > 1 || ret == 3) {
      return 4;
    }
    if (cnt == 0) {
      if (hasEmpty) {
        return 2;
      } else {
        return 3;
      }
    }
    return ret == 1 ? 0 : 1;  
  }
  boolean in(char c) {
    return c == 'X' || c == 'O';
  }
  int checkHor() {
    for (int i = 0; i < n; ++i) {
      int len = 0;
      for (int j = 1; j < n; ++j) {
        if (v[i][j] == v[i][j - 1] && in(v[i][j])) {
          ++len;
        } else {
          if (len >= m) {
            return 3;
          }
          if (len == m - 1) {
            ++cnt;
            ret |= (v[i][j - 1] == 'X' ? 1 : 2);
          }
          len = 0;
        }
      }
      if (len == m - 1) {
        ++cnt;
        ret |= (v[i][n - 1] == 'X' ? 1 : 2);
      }
      
    }
    return cnt > 1 ? 3 : ret;
  }

  int checkVer() {
    for (int j = 0; j < n; ++j) {
      int len = 0;
      for (int i = 1; i < n; ++i) {
        if (v[i][j] == v[i - 1][j] && in(v[i][j])) {
          ++len;
        } else {
          if (len >= m) {
            return 3;
          }
          if (len == m - 1) {
            ++cnt;
            ret |= (v[i - 1][j] == 'X' ? 1 : 2);
          }
          len = 0;
        }
      }
      if (len == m - 1) {
        ++cnt;
        ret |= (v[n - 1][j] == 'X' ? 1 : 2);
      }
    }
    return cnt > 1 ? 3 : ret;
  }

  int diag(int dx, int dy) {
    int len = 0;
    for (int i = 0; i < n; ++i) {
      int x = 0 + dx, y = i + dy;
      len = 0;
      while (x >= 0 && y >= 0 && x < n && y < n) {
        if (v[x - dx][y - dy] == v[x][y] && in(v[x][y])) {
          ++len;
        } else {
          if (len >= m) {
            return 3;
          }
          if (len == m - 1) {
            ++cnt;
            ret |= (v[x - dx][y - dy] == 'X' ? 1 : 2);
          }
          len = 0;
        }
        x += dx;
        y += dy;
      }
      if (len == m - 1) {
        ++cnt;
        ret |= (v[x - dx][y - dy] == 'X' ? 1 : 2);
      }

      len = 0;
      if (i > 0) {
        x = i + dx; y = 0 + dy;
        while (x >= 0 && y >= 0 && x < n && y < n) {
          if (v[x - dx][y - dy] == v[x][y] && in(v[x][y])) {
            ++len;
          } else {
            if (len >= m) {
              return 3;
            }
            if (len == m - 1) {
              ++cnt;
              ret |= (v[x - dx][y - dy] == 'X' ? 1 : 2);
            }
            len = 0;
          }
          x += dx;
          y += dy;
        }
        if (len == m - 1) {
          ++cnt;
          ret |= (v[x - dx][y - dy] == 'X' ? 1 : 2);
        }
      }

      len = 0;
      if (i > 0) {
        x = i + dx; y = n - 1 + dy;
        while (x >= 0 && y >= 0 && x < n && y < n) {
          if (v[x - dx][y - dy] == v[x][y] && in(v[x][y])) {
            ++len;
          } else {
            if (len >= m) {
              return 3;
            }
            if (len == m - 1) {
              ++cnt;
              ret |= (v[x - dx][y - dy] == 'X' ? 1 : 2);
            }
            len = 0;
          }
          x += dx;
          y += dy;
        }
        
        if (len == m - 1) {
          ++cnt;
          ret |= (v[x - dx][y - dy] == 'X' ? 1 : 2);
        }
      }
      
    }
    return cnt > 1 ? 3 : ret;
  }

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

