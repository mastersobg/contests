import java.io.*;
import java.util.*;

public class Solution {

    PrintWriter out;
    Reader in;

    void solve() throws IOException {
        int n = in.nextInt();
        int square = 0, rectangle = 0, neither = 0;
        for (int i = 0; i < n; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            boolean isAllPositive = isAllPositive(a, b, c, d);
            if (isAllPositive && isSquare(a, b, c, d)) {
                ++square;
            } else if (isAllPositive && isRectangle(a, b, c, d)) {
                ++rectangle;
            } else {
                ++neither;
            }
        }
        out.print(square + " " + rectangle + " " + neither);
    }

    private boolean isRectangle(int a, int b, int c, int d) {
        return a == c && b == d;
    }

    private boolean isSquare(int a, int b, int c, int d) {
        return a == c && b == d && a == b;
    }

    private boolean isAllPositive(int a, int b, int c, int d) {
        return a > 0 && b > 0 && c > 0 && d > 0;
    }


    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new Reader();
        out = new PrintWriter(System.out);
        solve();
        in.close();
        out.close();
    }


    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    private static class Reader {

        private BufferedReader in;
        private StringTokenizer st;

        private Reader() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextString() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(in.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.valueOf(nextString());
        }

        long nextLong() throws IOException {
            return Long.valueOf(nextString());
        }

        double nextDouble() throws IOException {
            return Double.valueOf(nextString());
        }

        void close() throws IOException {
            in.close();
        }

    }
}

