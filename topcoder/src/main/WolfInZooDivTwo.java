package main;

import java.util.*;

import static java.lang.Math.*;

public class WolfInZooDivTwo {


    ArrayList<Integer> []s;
    int mod = 1000000007;
    int [][]dp;
    int n;

    int rec(int cur, int prev) {
        if(cur == n)
           return 1;
        int ret = dp[cur][prev];
        if(ret == -1) {
            ret = rec(cur + 1, cur + 1);
            boolean ok = true;
            for(int a : s[cur]) {
                if(a >= prev) {
                    ok = false;
                    break;
                }
            }
            if(ok)
                ret = (ret + rec(cur + 1, prev)) % mod;
            dp[cur][prev] = ret;
        }
        return ret;
    }

    public int count(int N, String[] L, String[] R) {
        this.n = N;
        StringTokenizer lf = new StringTokenizer(join(L));
        StringTokenizer rg = new StringTokenizer(join(R));
        s = new ArrayList[N];
        for(int i = 0; i < s.length; ++i)
            s[i] = new ArrayList<Integer>();
        while(lf.hasMoreTokens()) {
            int l = Integer.valueOf(lf.nextToken());
            int r = Integer.valueOf(rg.nextToken());
            s[r].add(l);
        }
        dp = new int[N][N + 1];
        for(int i = 0; i < dp.length; ++i)
            Arrays.fill(dp[i], -1);
        return rec(0, 0);
    }

    String join(String []v) {
        StringBuilder sb = new StringBuilder();
        for(String s : v)
            sb.append(s);
        return sb.toString();
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
            eq(0,(new WolfInZooDivTwo()).count(5, new String[] {"0 1"}, new String[] {"2 4"}),27);
            eq(1,(new WolfInZooDivTwo()).count(10, new String[] {"0 4 2 7"}, new String[] {"3 9 5 9"}),798);
            eq(2,(new WolfInZooDivTwo()).count(100, new String[] {"0 2 2 7 10 1","3 16 22 30 33 38"," 42 44 49 51 57 60 62"," 65 69 72 74 77 7","8 81 84 88 91 93 96"}, new String[] {"41 5 13 22 12 13 ","33 41 80 47 40 ","4","8 96 57 66 ","80 60 71 79"," 70 77 ","99"," 83 85 93 88 89 97 97 98"}),250671525);
            
            eq(3,(new WolfInZooDivTwo()).count(3, new String[] {"1"}, new String[] {"2"}),6);
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
