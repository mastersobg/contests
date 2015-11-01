package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class UndoHistory {

    int prefix(String a, String b) {
        int len = min(a.length(), b.length());
        int ret = 0;
        for(int i = 0; i < len; ++i, ++ret)
            if(a.charAt(i) != b.charAt(i))
                break;
        return ret;
    }
    public int minPresses(String[] lines) {
        int ret = 0;
        String prev = "";
        HashSet<String> set = new HashSet<String>();
        for(String s : lines) {
            int []arr = calc(prev, s, set);
            int pr = arr[1];
            int add = arr[0];
            dbg("prefix=", pr);
            dbg("set=", set);
            for (int i = pr; i < s.length(); ++i) {
                set.add(s.substring(0, i + 1));
            }
            ret += add;
            dbg("ret=", ret);
            prev = s;
        }
        return ret;
    }

    int []calc(String prev, String cur, HashSet<String> words) {
        int p1 = 0, c1 = 1 << 29, p2 = 0, c2 = 1 << 29;
        int pr = prefix(prev, cur);
        if(pr == prev.length()) {
            p1 = pr;
            c1 = cur.length() - p1 + 1;
        }
        
        c2 = 2;
        pr = 0;
        for(String word : words) {
            pr = max(prefix(word, cur), pr);
        }
        p2 = pr;
        c2 += cur.length() - p2 + 1;
        
        return c1 < c2 ? 
            new int[] {c1, p1} :
            new int[] {c2, p2};
    }

    boolean debug = false;

    void dbg(Object ...args) {
        if(!debug)
            return ;
        for(Object s : args)
            System.err.print(s + " ");
        System.err.println();
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new UndoHistory()).minPresses(new String[] {"absolutely", "abs", "absolute"}),17);
    eq(0,(new UndoHistory()).minPresses(new String[] {"tomorrow", "topcoder"}),18);
            eq(1,(new UndoHistory()).minPresses(new String[] {"a","b"}),6);
            eq(2,(new UndoHistory()).minPresses(new String[] {"a", "ab", "abac", "abacus" }),10);
            eq(3,(new UndoHistory()).minPresses(new String[] {"pyramid", "sphinx", "sphere", "python", "serpent"}),39);
            eq(4,(new UndoHistory()).minPresses(new String[] {"ba","a","a","b","ba"}
               ),13);
               
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
