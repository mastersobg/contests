import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class GooseInZooDivTwo {

    class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    void bfs(boolean [][]was, int x, int y, int dist, String []f) {
        int n = f.length;
        int m = f[0].length();
        Pair []q = new Pair[n*m];
        int b = 0, e = 0;
        was[x][y] = true;
        for(q[e++] = new Pair(x, y); b < e; ++b) {
            x = q[b].x;
            y = q[b].y;
            for(int i= 0; i < n; ++i)
                for(int j = 0; j < m; ++j)
                    if(f[i].charAt(j) == 'v' && dist(x, y, i, j) <= dist &&
                            was[i][j] == false) {
                        was[i][j] = true;
                        q[e++] = new Pair(i, j);
                    }
        }
    }

    public int count(String[] f, int dist) {
        int n = f.length;
        int m = f[0].length();
        boolean [][]was = new boolean[n][m];
        int tot = 0;
        int birds = 0;
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m; ++j)
                if(f[i].charAt(j) == 'v') {
                    ++birds;
                    if(!was[i][j]) {
                        ++tot;
                        fill(was, i, j, dist, f);
                        dbg("was=", Arrays.deepToString(was));
                    }
                }

        was = new boolean[n][m];        
        int q = 0;
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m; ++j)
                if(f[i].charAt(j) == 'v') {
                    ++birds;
                    if(!was[i][j]) {
                        ++q;
                        bfs(was, i, j, dist, f);
                        dbg("was=", Arrays.deepToString(was));
                    }
                }
        
        dbg("q=", q);
        dbg("birds=", birds);
        for (int i = 0; ; ++i)
            if(pow(i) == 797922654) {
                dbg("pow=", i);
                break;
            }
        dbg(tot);
        return tot == 0 ? 0 : pow(q);
    }
    int pow(int a) {
        long ret = 1;
        long mod = 1000000007L;
        for(int i = 0; i < a; ++i) {
            ret = (ret << 1L) % mod;
        }
        --ret;
        if(ret < 0)
            ret += mod;
        return (int)ret;
    }

    void fill(boolean [][]was, int i, int j, int dist, String []f) {
        int n = was.length;
        int m = was[0].length;
        int [][]q = new int[n * m][];
        int b = 0, e = 0;
        was[i][j] = true;
        for(q[e++] = mp(i, j); b < e; ++b) {
            int curX = q[b][0];
            int curY = q[b][1];
            for(int dx = -dist; dx <= dist; ++dx) {
                int dy = dist - abs(dx);
                int x = curX + dx;
                int y = curY + dy;
                if(check(n, m, x, y) && !was[x][y] && f[x].charAt(y) == 'v') {
                    if (dist(x, y, curX, curY) != dist) {
                        dbg(curX, curY, x, y);
                    }
                    was[x][y] = true;
                    q[e++] = mp(x, y);
                }
                dy = -dy;
                y = curY + dy;
                if(check(n, m, x, y) && !was[x][y] && f[x].charAt(y) == 'v') {
                    assert dist(x, y, curX, curY) == dist;
                    was[x][y] = true;
                    q[e++] = mp(x, y);
                }
            }
        }
    }

    int[] mp(int a, int b) {
        return new int[]{a, b};
    }

    boolean check(int n, int m, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    int dist(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }
    static boolean DEBUG = false;
    void dbg(Object ...args) {
        if(!DEBUG)
            return ;
        for(Object o : args)
            System.err.print(o + " ");
        System.err.println();
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            DEBUG = true;
/*            eq(-1, (new GooseInZooDivTwo()).count(new String[]{
            "v..v", "..v.", ".v.v", "v..."
            }, 3), 3);
            eq(2,(new GooseInZooDivTwo()).count(new String[] {"vvv"}, 1),1);
            eq(0,(new GooseInZooDivTwo()).count(new String[] {"vvv"}, 0),7);
            eq(1,(new GooseInZooDivTwo()).count(new String[] {"."}, 100),0);
*/            
            eq(3,(new GooseInZooDivTwo()).count(new String[] {"v.v..................v............................"
               ,".v......v..................v.....................v"
               ,"..v.....v....v.........v...............v......v..."
               ,".........vvv...vv.v.........v.v..................v"
               ,".....v..........v......v..v...v.......v..........."
               ,"...................vv...............v.v..v.v..v..."
               ,".v.vv.................v..............v............"
               ,"..vv.......v...vv.v............vv.....v.....v....."
               ,"....v..........v....v........v.......v.v.v........"
               ,".v.......v.............v.v..........vv......v....."
               ,"....v.v.......v........v.....v.................v.."
               ,"....v..v..v.v..............v.v.v....v..........v.."
               ,"..........v...v...................v..............v"
               ,"..v........v..........................v....v..v..."
               ,"....................v..v.........vv........v......"
               ,"..v......v...............................v.v......"
               ,"..v.v..............v........v...............vv.vv."
               ,"...vv......v...............v.v..............v....."
               ,"............................v..v.................v"
               ,".v.............v.......v.........................."
               ,"......v...v........................v.............."
               ,".........v.....v..............vv.................."
               ,"................v..v..v.........v....v.......v...."
               ,"........v.....v.............v......v.v............"
               ,"...........v....................v.v....v.v.v...v.."
               ,"...........v......................v...v..........."
               ,"..........vv...........v.v.....................v.."
               ,".....................v......v............v...v...."
               ,".....vv..........................vv.v.....v.v....."
               ,".vv.......v...............v.......v..v.....v......"
               ,"............v................v..........v....v...."
               ,"................vv...v............................"
               ,"................v...........v........v...v....v..."
               ,"..v...v...v.............v...v........v....v..v...."
               ,"......v..v.......v........v..v....vv.............."
               ,"...........v..........v........v.v................"
               ,"v.v......v................v....................v.."
               ,".v........v................................v......"
               ,"............................v...v.......v........."
               ,"........................vv.v..............v...vv.."
               ,".......................vv........v.............v.."
               ,"...v.............v.........................v......"
               ,"....v......vv...........................v........."
               ,"....vv....v................v...vv..............v.."
               ,".................................................."
               ,"vv........v...v..v.....v..v..................v...."
               ,".........v..............v.vv.v.............v......"
               ,".......v.....v......v...............v............."
               ,"..v..................v................v....v......"
               ,".....v.....v.....................v.v......v......."}, 3),797922654);               
        } catch( Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }
    private static void eq( int n, int a, int b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, char a, char b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected '"+b+"', received '"+a+"'.");
    }
    private static void eq( int n, long a, long b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"L, received "+a+"L.");
    }
    private static void eq( int n, boolean a, boolean b ) {
        if ( a==b )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, double a, double b ) {
        if ( eq(a,b) )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected "+b+", received "+a+".");
    }
    private static void eq( int n, String a, String b ) {
        if ( a != null && a.equals(b) )
            System.err.println("Case "+n+" passed.");
        else
            System.err.println("Case "+n+" failed: expected \""+b+"\", received \""+a+"\".");
    }
    private static void eq( int n, int[] a, int[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++)
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, long[] a, long[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if ( a[i] != b[i] ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void eq( int n, double[] a, double[] b ) {
        if ( a.length != b.length ) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if ( !eq(a[i], b[i]) ) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }

    private static void eq( int n, String[] a, String[] b ) {
        if ( a.length != b.length) {
            System.err.println("Case "+n+" failed: returned "+a.length+" elements; expected "+b.length+" elements.");
            return;
        }
        for ( int i= 0; i < a.length; i++ )
            if( !a[i].equals( b[i])) {
                System.err.println("Case "+n+" failed. Expected and returned array differ in position "+i);
                print( b );
                print( a );
                return;
            }
        System.err.println("Case "+n+" passed.");
    }
    private static void print( int a ) {
        System.err.print(a+" ");
    }
    private static void print( long a ) {
        System.err.print(a+"L ");
    }
    private static void print( String s ) {
        System.err.print("\""+s+"\" ");
    }
    private static void print( int[] rs ) {
        if ( rs == null) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( long[] rs) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( double[] rs) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print(rs[i]);
            if ( i != rs.length-1 )
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void print( String[] rs ) {
        if ( rs == null ) return;
        System.err.print('{');
        for ( int i= 0; i < rs.length; i++ ) {
            System.err.print( "\""+rs[i]+"\"" );
            if( i != rs.length-1)
                System.err.print(", ");
        }
        System.err.println('}');
    }
    private static void nl() {
        System.err.println();
    }

    private static double eps=1e-9;

    private static boolean eq(double a, double b ) {
    	return abs(a-b) <= eps;
    }
// END CUT HERE
}
