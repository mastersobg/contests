import java.io.*;
import java.util.*;

public class Solution {

    PrintWriter out;
    Reader in;

    void solve() throws IOException {
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

