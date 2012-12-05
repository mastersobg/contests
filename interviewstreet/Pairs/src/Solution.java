import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    void solve() throws IOException {
        int n = ni(), k = ni();
        int[] arr = new int[n];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            arr[i] = ni();
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            if (map.containsKey(arr[i] - k))
                ret += map.get(arr[i] - k);
        }
        out.println(ret);
    }

    public Solution() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        in.close();
        out.close();
    }

    String ns() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(in.readLine());
        return st.nextToken();
    }

    int ni() throws IOException {
        return Integer.valueOf(ns());
    }

    long nl() throws IOException {
        return Long.valueOf(ns());
    }

    double nd() throws IOException {
        return Double.valueOf(ns());
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
