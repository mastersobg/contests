package main;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.util.ArraysUtil;
import com.ivangorbachev.util.BitsUtil;
import com.ivangorbachev.util.Dbg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class D {
    final int mod = 1000000000 + 7;
    int[][] dp;
    int[] d;
    int n;

    int rec(int n, int h) {
        if (h == 0) {
            return 1;
        }
        if (dp[h][n] != -1) {
            return dp[h][n];
        }
        int b = n >> 1;
        int ret = 0;
        for (int i = 1; i <= b; ++i) {
            if (i >= (1 << (h - 1))) {
                ret = (ret + rec(i, h - 1)) % mod;
            }
        }
        return dp[h][n] = ret;
    }

    int rec2(int n) {
        if (n == 1)
            return 1;
        if (d[n] != -1)
            return d[n];
        int ret = 1;
        for (int i = 1; i <= (n >> 1); ++i)
            ret = (ret + rec2(i)) % mod;
        return d[n] = ret;
    }

    int calc(int n) {
        if (n == 0 || n == 1)
            return 1;
        if (d[n] != -1)
            return d[n];
        if (BitsUtil.isOdd(n))
            return calc(n - 1);
        return d[n] = (calc(n >> 1) + calc(n - 1)) % mod;
    }

    int go(int n) {
        ArraysUtil.fill(dp, 0);
        for (int i = 0; i < dp.length; ++i)
            dp[i][n] = 1;
        for (int i = n; i > 0; --i) {
            for (int j = 1; j < dp.length; ++j)
                if (dp[j][i] > 0) {
                    for (int k = i >> 1; k > 0; --k) {
                        dp[j - 1][k] = (dp[j - 1][k] + dp[j][i]) % mod;
                    }
                }
        }

        int ret = 0;
        for (int i = 1; i <= n; i++) {
            ret = (ret + dp[0][i]) % mod;
        }
        return ret;
    }

    public void solve(int testNumber, Reader in, PrintWriter out) {
//        int n = in.readInt() - 1;
//        out.println(prec[n]);
//        dp = new int[20][100000 + 1];
//        ArraysUtil.fill(dp, -1);
//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//        for (int t = 1; t <= 100000; ++t) {
//            int ret = 0;
//            for (int i = 0; i <= 17; ++i) {
//                ret = (ret + rec(t, i)) % mod;
//            }
//            if (t > 1) {
//                sb.append(", ");
//            }
//            sb.append(ret);
//            Dbg.dbg(t);
//        }
//        sb.append("}");
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter("out.precalc");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        pw.println(sb.toString());
//        pw.close();
        n = in.readInt();
//        dp = new int[17][n + 1];
        d = new int[n + 1];
//        ArraysUtil.fill(dp, -1);
        Arrays.fill(d, -1);
        out.println(calc(n));
    }

    int slow(int n) {
        dp = new int[20][n + 1];
        ArraysUtil.fill(dp, -1);
        int ret = 0;
        for (int i = 0; i <= 17; ++i) {
            ret = (ret + rec(n, i)) % mod;
        }
        return ret;
    }
}
