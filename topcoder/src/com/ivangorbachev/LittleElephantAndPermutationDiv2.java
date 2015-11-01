package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class LittleElephantAndPermutationDiv2 {


  long fact;
  int N, K;

  public long getNumber(int N, int K) {
    this.N = N;
    this.K = K;
    fact = 1l;
    for (int i = 1; i <= N; ++i) {
      fact = fact * i;
    }
    return rec(0, new int[N], new boolean[N]);
  }


  long rec(int idx, int []v, boolean []was) {
    if (idx == v.length) {
      return check(v) ? fact : 0 ;
    }
    long ret = 0;
    for (int i = 0; i < v.length; ++i) {
      if (!was[i]) {
        v[idx] = i;
        was[i] = true;
        ret += rec(idx + 1, v, was);
        was[i] = false;
      }
    }
    return ret;
  }

  boolean check(int []v) {
    int ret = 0;
    for (int i = 0; i < v.length; ++i) {
      ret += max(i + 1, v[i] + 1);
    }
    return ret >= K;
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
            eq(0,(new LittleElephantAndPermutationDiv2()).getNumber(1, 1),1L);
            eq(1,(new LittleElephantAndPermutationDiv2()).getNumber(2, 1),4L);
            eq(2,(new LittleElephantAndPermutationDiv2()).getNumber(3, 8),18L);
            eq(3,(new LittleElephantAndPermutationDiv2()).getNumber(10, 47),13168189440000L);
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
