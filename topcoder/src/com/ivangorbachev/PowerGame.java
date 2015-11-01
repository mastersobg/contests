package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class PowerGame {

  static class State {
    
    int l, r;

    State(int a, int b) {
      l = min(a, b);
      r = max(a, b);
    }

    public boolean equals(Object other) {
      State s = (State) other;
      return s.l == l && s.r == r;
    }
  
    public int hashCode() {
      return l * 31 + r;
    }

    public String toString() {
      return "[l=" + l + ", r=" + r + "]";
    }

  }

  HashMap<State, Integer> dp = new HashMap<State, Integer> ();

  int rec(State s) {
    Integer ret = dp.get(s);
    if (ret != null) {
      return ret;
    }

    if (isSquare[s.l] || isSquare[s.r]) {
      ret = 1;
    } else {
      int win = 1 << 30;
      int lose = -1;
      
      for (int i = 0; i < 100; ++i) {
        if (squares[i] > s.l) break;
        int value = rec(new State(s.l - squares[i], s.r - 1)) + 1;
        if ((value & 1) == 1) {
          win = min(win, value);
        } else {
          lose = max(lose, value);
        }
      }

      for (int i = 0; i < 100; ++i) {
        if (squares[i] > s.r) break;
        int value = rec(new State(s.r - squares[i], s.l - 1)) + 1;
        if ((value & 1) == 1) {
          win = min(win, value);
        } else {
          lose = max(lose, value);
        }
      }


/*      for (int i = 0; i < 100; ++i) {
        if (squares[i] > s.l) break;
        for (int j = 0; j < 100; ++j) {
          if (squares[j] > s.r) break;
          int value = rec(new State(s.l - squares[i], s.r - squares[j])) + 1;
          if ((value & 1) == 1) {
            win = min(win, value);
          } else {
            lose = max(lose, value);
          }
        }
      }*/
      if (win != 1 << 30) ret = win;
      else ret = lose;
    }

    dp.put(s, ret);
    return ret;
  }

  boolean []isSquare;
  int []squares = new int[100];

  void gen() {
    isSquare = new boolean[10001];
    for (int i = 1; i <= 100; ++i) {
      isSquare[i * i] = true;
      squares[i - 1] = i * i;
    }
  }

  public String winner(int size0, int size1) {
    gen();
    int ret = rec(new State(size0, size1));
    if ((ret & 1) == 1)
      return "Alan will win after " + ret + " moves";
    else 
      return "Bob will win after " + ret + " moves";
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
//                  eq(0,(new PowerGame()).winner(4, 9),"Alan will win after 1 moves");
//            eq(1,(new PowerGame()).winner(4, 3),"Alan will win after 1 moves");
//            eq(2,(new PowerGame()).winner(2, 3),"Bob will win after 2 moves");
//            eq(3,(new PowerGame()).winner(7, 13),"Bob will win after 4 moves");
            eq(4,(new PowerGame()).winner(2136, 1244),"Alan will win after 7 moves");
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
