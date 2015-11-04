import java.io.*;
import java.util.*;

import static java.lang.Math.*;

import java.util.Arrays;

public class ShopPositions {
    int[][] p;
    int[][][] d;

    public int maxProfit(int n, int m, int[] c) {
        p = getP(n, m, c);
        d = new int[n + 1][m + 1][m + 1];
        ArraysUtil.fill(d, Integer.MIN_VALUE);
        d[0][0][0] = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (d[i][j][k] == Integer.MIN_VALUE)
                        continue;
                    for (int cur = 0; cur <= m; ++cur) {
                        int v = d[i][j][k];
                        if (i > 0) {
                            v -= p[i - 1][j + k] * j;
                            v += p[i - 1][k + j + cur] * j;
                        }
                        v += p[i][j + cur] * cur;
                        d[i + 1][cur][j] = Math.max(
                                v,
                                d[i + 1][cur][j]
                        );
                        if (d[i + 1][cur][j] == 17) {
                            int lol = 1;
                        }
                    }
                }
            }
        }
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i <= m; ++i)
            for (int j = 0; j <= m; ++j) {
                ret = Math.max(ret, d[n][i][j]);
            }
        return ret;
    }

    int[][] getP(int n, int m, int[] c) {
        int[][] ret = new int[n][3 * m + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= 3 * m; ++j) {
                ret[i][j] = c[i * 3 * m + j - 1];
            }
        }
        return ret;
    }

}

class ArraysUtil {
    public static void fill(int[][] v, int value) {
        for (int i = 0; i < v.length; i++) {
            Arrays.fill(v[i], value);
        }
    }

    public static void fill(int[][][] v, int value) {
        for (int i = 0; i < v.length; i++) {
            fill(v[i], value);
        }
    }

}
