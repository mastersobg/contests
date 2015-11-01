import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;
 
public class Solution {
        BufferedReader in;
        PrintWriter out;
        StringTokenizer st;
 
        long n = 2;
        long calc(long s) {
                for (n = 2; n <= 44750; n++) {
                        long s2 = s * 2;
                        long nn = n * n;
                        long n2 = n * 2;
                        long left = s2 - (nn - n);
                        if (left / n2 <= 0)
                            return -1;
                        if (left % n2 == 0) return left / n2;
                }
                return -1;
        }
       
        void solve() throws IOException {
                int t = ni();
                while (t-- > 0) {
                        long s = nl();
                        long ans = calc(s);
                        if (ans < 1) {
                                out.println("IMPOSSIBLE");
                                continue;
                        } else {
                                out.print(s + " = ");
                                long r = ans + n;
                                for (long i = ans; i < r; i++) {
                                        out.print(i + ((i < r - 1) ? " + " : ""));
                                }
                        }
                        out.println();
                }
        }
 
        String ns() throws IOException {
                while (st == null || !st.hasMoreTokens()) {
                        st = new StringTokenizer(in.readLine());
                }
                return st.nextToken();
        }
 
        int ni() throws IOException {
                return Integer.parseInt(ns());
        }
 
        double nd() throws IOException {
                return Double.parseDouble(ns());
        }
 
        long nl() throws IOException {
                return Long.parseLong(ns());
        }
 
        Solution() throws IOException {
                Locale.setDefault(Locale.US);
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(new OutputStreamWriter(System.out));
                solve();
                in.close();
                out.close();
        }
 
        public static void main(String[] args) throws IOException {
                new Solution();
        }
 
}