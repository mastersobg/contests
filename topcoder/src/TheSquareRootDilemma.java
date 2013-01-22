import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class TheSquareRootDilemma {

    List<Integer> primes(int n) {
      boolean []primes = new boolean[n + 1];
      primes[0] = primes[1] = true;
      for(int i = 2; i < primes.length; ++i) {
        if(primes[i] == false) {
          for(int j = i + i; j < primes.length; j += i) {
            primes[j] = true;
          }
        }
      }
      List<Integer> ret = new ArrayList<Integer>();
      for(int i = 0; i < primes.length; ++i) {
        if(primes[i] == false) {
          ret.add(i);
        }
      }
      return ret;
    }

    List<Integer> factorize(int n, List<Integer> primes) {
      List<Integer> ret = new ArrayList<Integer>();
      for(int i = 0; i < primes.size(); ++i) {
        int p = primes.get(i);
        if(p * p > n) {
          break;
        }
        if(n % p == 0) {
          int cnt = 0;
          while(n % p == 0) {
            n /= p;
            ++cnt;
          }
          if((cnt & 1) == 1) {
            ret.add(p);
          }
        }
      }
      if(n != 1) {
        ret.add(n);
      }
      return ret;
    }

    public int countPairs(int N, int M) {
      int min = min(N, M);
      int max = max(N, M);
      List<Integer> primes = primes(max);
      HashMap<List<Integer>, Integer> smallMap = new HashMap<List<Integer>, Integer> ();
      for(int i = 1; i <= min; ++i) {
        List<Integer> factors = factorize(i, primes);
        Integer value = smallMap.get(factors);
        if(value == null) {
          smallMap.put(factors, 1);
        }
        else {
          smallMap.put(factors, value + 1);
        }
      }
      int ret = 0;
      for(int i = 1; i <= max; ++i) {
        List<Integer> factors = factorize(i, primes);
        Integer value = smallMap.get(factors);
        if(value != null) {
          ret += value;
        }
      }
      return ret;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0,(new TheSquareRootDilemma()).countPairs(2, 2),2);
            eq(1,(new TheSquareRootDilemma()).countPairs(10, 1),3);
            eq(2,(new TheSquareRootDilemma()).countPairs(3, 8),5);
            eq(3,(new TheSquareRootDilemma()).countPairs(100, 100),310);
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
