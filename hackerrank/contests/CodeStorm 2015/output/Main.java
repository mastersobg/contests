import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.BufferedReader;
import java.util.List;
import java.util.Collections;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Arrays;

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
        GameofReduction solver = new GameofReduction();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class GameofReduction {
        private Outcome[] dp;

        public GameofReduction() {
            dp = new Outcome[1000000 + 1];
            Arrays.fill(dp, Outcome.UNKNOWN);
            for (int i = 0; i < dp.length; i++) {
                dp[i] = rec(i);
            }
        }

        Outcome rec(int n) {
            if (dp[n] == Outcome.UNKNOWN) {
                if (n < 10) {
                    dp[n] = Outcome.LOSE;
                } else {
                    int x = n;
                    List<Integer> list = new ArrayList<>();
                    while (x > 0) {
                        int mod = x % 10;
                        list.add(mod);
                        x /= 10;
                    }
                    Collections.reverse(list);
                    boolean hasLose = false;
                    for (int i = 1; i < list.size(); ++i) {
                        int value = 0;
                        for (int j = 0; j < i - 2; ++j) {
                            value = value * 10 + list.get(j);
                        }
                        value = value * 10 + (list.get(i) + list.get(i - 1)) % 10;
                        for (int j = i + 1; j < list.size(); ++j) {
                            value = value * 10 + list.get(j);
                        }
                        hasLose |= rec(value) == Outcome.LOSE;
                    }
                    dp[n] = hasLose ? Outcome.WIN : Outcome.LOSE;
                }
            }
            return dp[n];
        }

        public void solve(int testNumber, Reader in, PrintWriter out) {
            int n = in.readInt();
            HashSet<Integer> v = new HashSet<>(in.readIntList());
            int wins = 0;
            for (int a : v) {
                if (dp[a] == Outcome.WIN)
                    ++wins;
            }
            final int mod = 1000000000 + 7;
            int loseLeft = 0;
            int winLeft = 0;
            for (int i = 1; i <= n; ++i) {
                if (!v.contains(i)) {
                    if (dp[i] == Outcome.WIN)
                        ++winLeft;
                    else
                        ++loseLeft;
                }
            }
            int[] f = NumbersUtil.factorial(Math.max(loseLeft, winLeft), mod);
            int loseAns = 0;
//        int winAns = 0;
            // n! / (m! * (n - m)!)
            loseAns = NumbersUtil.pow(2, loseLeft, mod);
//        for (int i = 0; i <= loseLeft; ++i) {
//            loseAns = (loseAns + calcC(f, loseLeft, i, mod)) % mod;
//        }
            boolean even = BitsUtil.isEven(wins);
            int winAns;
            if (winLeft == 0) {
                winAns = even ? 1 : 0;
            } else
                winAns = NumbersUtil.pow(2, winLeft - 1, mod);
//        for (int i = 0; i <= winLeft; ++i) {
//            boolean isEvenCur = BitsUtil.isEven(i);
//            if (even == isEvenCur) {
//                winAns = (winAns + calcC(f, winLeft, i, mod)) % mod;
//            }
//        }
            long ans = ((long) winAns * (long) loseAns) % (long) mod;
            if (even) {
                --ans;
            }
            out.println(ans);
        }

        enum Outcome {
            WIN,
            LOSE,
            UNKNOWN,;
        }

    }

    static class NumbersUtil {
        public static int pow(int base, int pow, int mod) {
            int ret = 1;
            while (pow > 0) {
                if (BitsUtil.isOdd(pow))
                    ret = multiply(ret, base, mod);
                base = multiply(base, base, mod);
                pow >>= 1;

            }
            return ret;
        }

        public static int multiply(int a, int b, int mod) {
            return (int) (((long) a * (long) b) % (long) mod);
        }

        public static int[] factorial(int n, int mod) {
            int[] ret = new int[n + 1];
            ret[0] = 1;
            for (int i = 1; i <= n; i++) {
                ret[i] = multiply(ret[i - 1], i, mod);
            }
            return ret;
        }

    }

    static class BitsUtil {
        public static boolean isOdd(int number) {
            return (number & 1) == 1;
        }

        public static boolean isEven(int number) {
            return (number & 1) == 0;
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

        public String next() {
            return readString();
        }

        public int readInt() {
            return Integer.valueOf(readString());
        }

        public List<Integer> readIntList(int size) {
            List<Integer> ret = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                ret.add(readInt());
            }
            return ret;
        }

        public List<Integer> readIntList() {
            int n = readInt();
            return readIntList(n);
        }

    }
}

