package main;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.numbers.NumbersUtil;
import com.ivangorbachev.util.BitsUtil;
import com.ivangorbachev.util.Dbg;

import java.io.PrintWriter;
import java.util.*;

public class GameofReduction {

    enum Outcome {
        WIN, LOSE, UNKNOWN
    }

    private Outcome []dp;

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
        int []f = NumbersUtil.factorial(Math.max(loseLeft, winLeft), mod);
        int loseAns = 0;
        int winAns = 0;
        // n! / (m! * (n - m)!)
        for (int i = 0; i <= loseLeft; ++i) {
            loseAns = (loseAns + calcC(f, loseLeft, i, mod)) % mod;
        }
        boolean even = BitsUtil.isEven(wins);
        for (int i = 0; i <= winLeft; ++i) {
            boolean isEvenCur = BitsUtil.isEven(i);
            if (even == isEvenCur) {
                winAns = (winAns + calcC(f, winLeft, i, mod)) % mod;
            }
        }
        long ans = ((long) winAns * (long) loseAns) % (long) mod;
        if (even) {
            --ans;
        }
        out.println(ans);
    }

    private int calcC(int []f, int n, int m, int mod) {
        int a = NumbersUtil.multiply(f[m], f[n - m], mod);
        a = NumbersUtil.revElementPrimeMod(a, mod);
        return NumbersUtil.multiply(a, f[n], mod);
    }
}
