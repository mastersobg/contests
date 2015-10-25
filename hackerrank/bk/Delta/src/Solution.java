import java.io.*;
import java.util.*;

public class Solution {

    PrintWriter out;
    Reader in;

    void solve() throws IOException {
        List<Long> list = in.readAll();
        if (list.size() == 0) {
            return ;
        }
        out.print(list.get(0) + " ");
        for (int i = 1; i < list.size(); ++i) {
            long diff = list.get(i) - list.get(i - 1);
            if (-127 <= diff && diff <= 127) {
                out.print(diff + " ");
            } else {
                out.print("-128 " + diff + " ");
            }
        }
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

        List<Long> readAll() throws IOException {
            List<Long> ret = new ArrayList<Long>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            while (st.hasMoreTokens()) {
                ret.add(Long.valueOf(st.nextToken()));
            }
            return ret;
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

