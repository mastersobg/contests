import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class CCipher {


  public String decode(String cipherText, int shift) {
      return "";
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
                  eq(0,(new CCipher()).decode("VQREQFGT", 2),"TOPCODER");
            eq(1,(new CCipher()).decode("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10),"QRSTUVWXYZABCDEFGHIJKLMNOP");
            eq(2,(new CCipher()).decode("TOPCODER", 0),"TOPCODER");
            eq(3,(new CCipher()).decode("ZWBGLZ", 25),"AXCHMA");
            eq(4,(new CCipher()).decode("DBNPCBQ", 1),"CAMOBAP");
            eq(5,(new CCipher()).decode("LIPPSASVPH", 4),"HELLOWORLD");
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
