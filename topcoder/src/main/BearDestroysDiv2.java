package main;

import com.ivangorbachev.util.BitsUtil;
import com.ivangorbachev.util.Dbg;

public class BearDestroysDiv2 {

    int [][][]dp;
    int mod, H, W;

    public int sumUp(int W, int H, int MOD) {
        mod = MOD;
        this.H = H;
        this.W = W;
        int S = W * H / 2;
        dp = new int[S + 1][H + 1][1 << W];

        dp[0][0][0] = 1;
        for (int s = 0; s <= S; ++s) {
            for (int i = 0; i < H; ++i) {
                for (int mask = 0; mask < (1 << W); ++mask) {
                    if (dp[s][i][mask] != 0) {
                        for (int lineMask = 0; lineMask < (1 << W); ++lineMask)
                            go(s, i, mask, 0, 0, 0, lineMask);
                    }
                }
            }
        }
        int ret = 0;
        for (int i = 0; i <= S; ++i) {
            long x = ((long) i * dp[i][H][0]) % (long) mod;
            ret = (ret + (int) x) % mod;
        }
        return ret;
    }

    private void go(int s, int x, int mask, int y, int nmask, int added, int lineMask) {
        if (y == W) {
            dp[s + added][x + 1][nmask] = (dp[s + added][x + 1][nmask] + dp[s][x][mask]) % mod;
            return ;
        }
        if (BitsUtil.checkBit(mask, y)) {
            go(s, x, mask, y + 1, nmask, added, lineMask);
        } else {
            if (BitsUtil.checkBit(lineMask, y)) {
                if (x + 1 < H)
                    go(s, x, mask, y + 1, BitsUtil.setBit(nmask, y), added + 1, lineMask);
                else if (y + 1 < W && !BitsUtil.checkBit(mask, y + 1)) {
                    go(s, x, mask, y + 2, nmask, added + 1, lineMask);
                } else {
                    go(s, x, mask, y + 1, nmask, added, lineMask);
                }
            } else {
                if (y + 1 < W && !BitsUtil.checkBit(mask, y + 1)) {
                    go(s, x, mask, y + 2, nmask, added + 1, lineMask);
                } else if (x + 1 < H)
                    go(s, x, mask, y + 1, BitsUtil.setBit(nmask, y), added + 1, lineMask);
                else {
                    go(s, x, mask, y + 1, nmask, added, lineMask);
                }
            }
        }
    }
}
