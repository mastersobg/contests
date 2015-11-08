import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.io.OutputStream;

import static java.lang.Math.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        final int mod = 1000000000 + 7;

        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            int k = in.readInt();
            int[] a = in.readIntArray(n / k);
            int[] b = in.readIntArray(n / k);
            int ret = 1;
            for (int i = 0; i < a.length; ++i) {
                ret = NumbersUtil.multiply(ret, go(k, a[i], b[i]), mod);
            }
            out.println(ret);
        }

        private int go(int len, int div, int digit) {
            long ret = 0;
            long pow = 1;
            for (int i = 1; i < len; ++i) {
                pow *= 10L;
            }
            for (int i = 0; i < 10; ++i)
                if (i != digit) {
                    long x = i * pow % div;
                    x = (div - x) % div;
                    long y = pow - x;
                    if (y < 0)
                        continue;
                    long cnt = y / div + 1;
                    if (y % div == 0)
                        --cnt;
                    ret = (cnt + ret) % mod;
                }
            return (int) ret;
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
            return Integer.parseInt(readString());
        }

        public int[] readIntArray(int size) {
            int[] ret = new int[size];
            for (int i = 0; i < size; i++) {
                ret[i] = readInt();
            }
            return ret;
        }

    }

    static class NumbersUtil {
        public static int multiply(int a, int b, int mod) {
            return (int) (((long) a * (long) b) % (long) mod);
        }

    }
}

