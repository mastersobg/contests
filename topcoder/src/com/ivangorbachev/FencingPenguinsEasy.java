package com.ivangorbachev;

import java.util.*;

import static java.lang.Math.*;

public class FencingPenguinsEasy {


    static class Point {
      double x, y;

      Point(double x, double y) {
        this.x = x;
        this.y = y;
      }

      public String toString() {
        return "x = " + x + " y = " + y;
      }
    }

    double s(Point p1, Point p2) {
      return (p1.y + p2.y) * (p2.x - p1.x) / 2.0;
    }

    boolean ccw(Point p1, Point p2, Point p3) {
      return s(p1, p2) + s(p2, p3) + s(p3, p1) < 0.0;
    }

    Point []penguins;
    Point []pts;

    double [][]dp;
    boolean [][]ok;
    double [][]precalc;

    double calc(int i, int j) {
      if(precalc == null) {
        precalc = new double [pts.length][pts.length];
        for(int q = 0; q < pts.length; ++q)
          Arrays.fill(precalc[q], -1.0);
      }
      if(precalc[i][j] < 0.0) {
        double ret = 0.0;
        int q = i;
        while(q != j) {
          int next = (q + 1) % pts.length;
          ret += s(pts[q], pts[next]);
          ++q;
          if(q == pts.length)
            q = 0;
        }
        ret += s(pts[j], pts[i]);
        precalc[i][j] = abs(ret);
      }
      return precalc[i][j];
    }

    double rec(int l, int r) {
      if(l == r)
        return 0.0;
      if(dp[l][r] < 0.0) {
        double ret = 0.0;
        for(int i = l + 1; i <= r; ++i) {
          if(ok[l][i])
            ret = max(ret, rec(i, r) + calc(l, i));
        }
        dp[l][r] = ret;
      }
      return dp[l][r];
    }
    public double calculateMinArea(int numPosts, int radius, int[] x, int[] y) {
      penguins = new Point[x.length];
      for(int i = 0; i < x.length; ++i)
        penguins[i] = new Point(x[i], y[i]);
      double angle = 2 * PI / (double)numPosts;
      pts = new Point[numPosts];
      pts[0] = new Point(radius, 0);
      for(int i = 1; i < numPosts; ++i) {
        double a = i * angle;
        double X = radius * cos(a);
        double Y = radius * sin(a);
        pts[i] = new Point(X, Y);
      }
      //System.out.println(angle);
  //    System.out.println(Arrays.deepToString(penguins));
    //  System.out.println(Arrays.deepToString(pts));
      int n = numPosts;
      ok = new boolean[n][n];
      for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j) {
          boolean a = true;
          for(int k = 0; k < penguins.length; ++k) {
            a &= ccw(pts[i], pts[j], penguins[k]);
          }
          ok[i][j] = a;
        }
//      System.out.println(Arrays.deepToString(ok));
      boolean check = true;
      for(int i = 0; i < pts.length; ++i) {
        check &= ok[i][(i + 1) % pts.length];
      }
      if(!check)
        return -1;
      dp = new double[numPosts][numPosts];
      for(int i = 0; i < dp.length; ++i)
        Arrays.fill(dp[i], -1);
      double ret = 0.0;
      for(int i = 0; i < n; ++i)
        for(int j = i + 2; j < n; ++j) 
          if(ok[j][i])
            ret = max(ret, rec(i, j) + calc(j, i));
//      System.out.println("ret=" + ret);
      double total = calc(0, n - 1);
//      System.out.println("total=" + total);
      return total - ret;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new FencingPenguinsEasy()).calculateMinArea(3, 5, new int[] {-1}, new int[] {0}),32.47595264191645);
            eq(1,(new FencingPenguinsEasy()).calculateMinArea(30, 5, new int[] {6}, new int[] {0}),-1.0);
            eq(2,(new FencingPenguinsEasy()).calculateMinArea(4, 5, new int[] {2}, new int[] {1}),25.0);
            eq(3,(new FencingPenguinsEasy()).calculateMinArea(4, 5, new int[] {2,-2}, new int[] {1,-1}),50.0);
            eq(4,(new FencingPenguinsEasy()).calculateMinArea(8, 5, new int[] {8}, new int[] {8}),-1.0);
            eq(5,(new FencingPenguinsEasy()).calculateMinArea(7, 10, new int[] {9}, new int[] {1}),29.436752637711805);
            eq(6,(new FencingPenguinsEasy()).calculateMinArea(30, 800, new int[] {8,2,100,120,52,67,19,-36}, new int[] {-19,12,88,-22,53,66,-140,99}
               ),384778.74757953023);
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
