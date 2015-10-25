import java.io.*;
import java.util.*;

public class Solution {

    PrintWriter out;
    Reader in;

    void solve() throws IOException {
        int sum = in.nextInt();
        int n = in.nextInt();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            int value = in.nextInt();
            Integer mapValue = map.get(value);
            if (mapValue == null) {
                map.put(value, 1);
            } else {
                map.put(value, mapValue + 1);
            }
        }

        boolean exists = false;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            long other = (long) sum - (long) key;
            if (other == key) {
                exists = value > 1;
            } else {
                Integer mapOther = !checkInt(other) ? null : map.get((int) other);
                exists = mapOther != null && mapOther > 0;
            }
            if (exists) {
                break;
            }
        }
        out.println(exists ? "1" : "0");
    }

    boolean checkInt(long a ) {
        return a >= Integer.MIN_VALUE && a <= Integer.MAX_VALUE;
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

