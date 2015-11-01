import java.io.OutputStream;

import static java.lang.Math.*;

import java.util.*;
import java.util.Locale;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        D solver = new D();
        solver.solve(1, in, out);
        out.close();
    }

    static class D {
        final int mod = 1000000000 + 7;
        int[] d;
        int n;

        int calc(int n) {
            if (n == 0 || n == 1)
                return 1;
            if (d[n] != -1)
                return d[n];
            if (BitsUtil.isOdd(n))
                return calc(n - 1);
            return d[n] = (calc(n >> 1) + calc(n - 1)) % mod;
        }

        public void solve(int testNumber, Reader in, PrintWriter out) {
            // ahaha
            // ahaha
            // 123
            // ahah
//        int n = in.readInt() - 1;
//        out.println(prec[n]);
//        dp = new int[20][100000 + 1];
//        ArraysUtil.fill(dp, -1);
//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//        for (int t = 1; t <= 100000; ++t) {
//            int ret = 0;
//            for (int i = 0; i <= 17; ++i) {
//                ret = (ret + rec(t, i)) % mod;
//            }
//            if (t > 1) {
//                sb.append(", ");
//            }
//            sb.append(ret);
//            Dbg.dbg(t);
//        }
//        sb.append("}");
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter("out.precalc");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        pw.println(sb.toString());
//        pw.close();
            n = in.readInt();
//        dp = new int[17][n + 1];
            d = new int[n + 1];
//        ArraysUtil.fill(dp, -1);
            Arrays.fill(d, -1);
            out.println(calc(n));
        }

    }

    static class Reader {
        private BufferedReader in;
        private StringTokenizer st;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

        public String readString() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            return st.nextToken();
        }

        public int readInt() {
            return Integer.valueOf(readString());
        }

    }

    static class BitsUtil {
        public static boolean isOdd(int number) {
            return (number & 1) == 1;
        }

    }
}

