import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int n;
    char[] s;

    int[][] dp;
    int[][] p;

    int rec(int pos, int prev) {
        if (pos == s.length + 1) {
            return 1;
        }
        int ret = dp[pos][prev];
        if (ret == -1) {
            ret = 0;
            if (s[pos - 1] == '=') {
                ret = rec(pos + 1, prev);
                p[pos][prev] = prev;
            } else if (s[pos - 1] == '<') {
                for (int i = prev + 1; i < n; ++i) {
                    int a = rec(pos + 1, i);
                    if (a == 1) {
                        ret = 1;
                        p[pos][prev] = i;
                        break;
                    }
                }
            } else {
                for (int i = prev - 1; i >= 0; --i) {
                    int a = rec(pos + 1, i);
                    if (a == 1) {
                        ret = 1;
                        p[pos][prev] = i;
                        break;
                    }
                }
            }

            dp[pos][prev] = ret;
        }
        return ret;
    }

    void solve() throws IOException {
        n = ni();
        s = ns().toCharArray();
        dp = new int[s.length + 2][26];
        p = new int[s.length + 2][26];
        for (int i = 0; i < dp.length; ++i)
            Arrays.fill(dp[i], -1);
        for (int i = 0; i < n; ++i) {
            if (rec(1, i) == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append((char) (i + 'a'));
                int idx = 1;
                int prev = i;
                for (int it = 0; it < s.length; ++it) {
                    sb.append((char) (p[idx][prev] + 'a'));
                    prev = p[idx][prev];
                    idx++;
                }
                out.println(sb.toString());
                return;
            }
        }
        out.println(-1);

//        for (int i = 0; i < n; ++i) {
//            String s = sol((char) (i + 'a'));
//            if (s != null) {
//                out.println(s);
//                return;
//            }
//        }
//        out.println(-1);
    }

    String sol(char ch) {
        StringBuilder sb = new StringBuilder();
        sb.append(ch);
        for (int i = 0; i < s.length; ++i) {
            char prev = sb.charAt(sb.length() - 1);
            int idx = prev - 'a';
            if (s[i] == '=') {
                sb.append(prev);
            } else if (s[i] == '>') {
                if (idx - 1 >= 0) {
                    sb.append((char) (prev - 1));
                } else return null;
            } else {
                if (idx + 1 < n)
                    sb.append((char) (prev + 1));
                else return null;
            }
        }
        return sb.toString();
    }

    void ass(int cnt, int n) {
        if (cnt > n) {
            out.print(-1);
            System.exit(0);
        }
    }

    int count(char[] s, int pos, char c) {
        int ret = 0;
        for (int i = pos; i < s.length; ++i)
            if (s[i] == c) ++ret;
        return ret;
    }

    public void run() throws IOException {
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
        new Solution().run();
    }

    class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }

        long time() {
            return System.currentTimeMillis() - time;
        }

        void print() {
            print("Time spent = ");
        }

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object... objs) {
        if (!DEBUG) {
            return;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[])
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }


}

