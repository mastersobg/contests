package main;

import java.util.*;

import static java.lang.Math.*;

public class MonotoneSequence {


  public int longestMonotoneSequence(int[] seq) {
    int ret = 1;
    for (int i = 0; i < seq.length; ++i) {
      for (int j = i + 1; j < seq.length; ++j) {
        if (ok(seq, i, j)) {
          ret = max(ret, j - i + 1);
        }
      }
    }
    return ret;
  }

  boolean ok(int []s, int a, int b) {
    int diff = norm(s[a + 1] - s[a]);
    if (diff == 0) return false;
    for (int i = a + 1; i <= b; ++i) {
      int d = norm(s[i] - s[i-1]);
      if (d == 0) return false;
      if (diff != d) return false;
    }
    return true;
  }

  int norm(int a) {
    if (a > 0) return 1;
    if (a < 0) return -1;
    return 0;
  }

  
  // BEGIN CUT HERE
  static boolean DEBUG = false;

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

  public static void main(String[] args) {
    try {
      DEBUG = true;
                  eq(0,(new MonotoneSequence()).longestMonotoneSequence(new int[] {1, 7, 7, 8, 3, 6, 7, 2}),3);
            eq(1,(new MonotoneSequence()).longestMonotoneSequence(new int[] {1, 1, 1, 1, 1}),1);
            eq(2,(new MonotoneSequence()).longestMonotoneSequence(new int[] {10, 20, 30, 25, 20, 19, 20, 18, 23}),4);
            eq(3,(new MonotoneSequence()).longestMonotoneSequence(new int[] {3, 2, 1, 4}),3);
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
