import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class ScotlandYard {
    public int maxMoves(String[] taxi, String[] bus, String[] metro) {
    	return 0;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new ScotlandYard()).maxMoves(new String[] {"NYN",
                "NNY",
                "NNN"}, new String[] {"NNN",
                "NNN",
                "NNN"}, new String[] {"NNN",
                "NNN",
                "NNN"}),2);
            eq(1,(new ScotlandYard()).maxMoves(new String[] {"NYY",
                "NNN",
                "NNN"}, new String[] {"NNN",
                "NNN",
                "NNN"}, new String[] {"NNN",
                "NNN",
                "NNN"}),1);
            eq(2,(new ScotlandYard()).maxMoves(new String[] {"NYYY",
                "YNYY",
                "YYNY",
                "YYYN"}, new String[] {"NNNN",
                "NNNN",
                "NNNN",
                "NNNN"}, new String[] {"NNNN",
                "NNNN",
                "NNNN",
                "NNNN"}),-1);
            eq(3,(new ScotlandYard()).maxMoves(new String[] {"NNY",
                "NNY",
                "NNN"}, new String[] {"NYN",
                "NNY",
                "NNN"}, new String[] {"NNN",
                "NNN",
                "YNN"}),2);
            eq(4,(new ScotlandYard()).maxMoves(new String[] {"NNN",
                "YNY",
                "NNN"}, new String[] {"NNN",
                "YNN",
                "YNN"}, new String[] {"NNN",
                "NNN",
                "YYN"}),-1);
            eq(5,(new ScotlandYard()).maxMoves(new String[] {"NNNNYNNNYY",
                "NNYNNYYYYY",
                "NNNNNNNNNN",
                "YYNNYYNNNY",
                "NNYNNNNNNN",
                "YNYNYNNNYN",
                "NNYNYNNNYN",
                "NNNNNNYNNN",
                "NNNNNNNNNN",
                "NNNNNNYNNN"}, new String[] {"NNYNNNYNNY",
                "YNYNNYYNYY",
                "NNNNNNNNNN",
                "YNYNNYNYNY",
                "NNYNNNNNYN",
                "YNYNYNYNYN",
                "NNYNNNNNNY",
                "YNYNNNNNNN",
                "NNNNNNNNNN",
                "NNYNYNNNNN"}, new String[] {"NNNNNNNYNN",
                "YNYNNNNNYN",
                "NNNNNNNNNN",
                "NYNNYNNNYY",
                "NNYNNNNNNN",
                "YNYNNNNNYY",
                "NNNNYNNNYN",
                "NNYNNNYNNN",
                "NNNNNNNNNY",
                "NNYNYNNNNN"}),21);
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
