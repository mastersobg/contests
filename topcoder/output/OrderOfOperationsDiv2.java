import java.io.*;
import java.util.*;

import static java.lang.Math.*;

import java.util.Arrays;

public class OrderOfOperationsDiv2 {
    int[] d;

    public int minTime(String[] s) {
        d = new int[1 << s.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        return rec(0, 0, s);
    }

    int rec(int mask, int used, String[] s) {
        if (Integer.bitCount(mask) == s.length) {
            return 0;
        }
        if (d[mask] != Integer.MAX_VALUE)
            return d[mask];
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < s.length; ++i)
            if (!BitsUtil.checkBit(mask, i)) {
                IntValue v = new IntValue();
                ret = Math.min(ret, calc(s[i], used, v) +
                                rec(BitsUtil.setBit(mask, i), v.getValue(), s)
                );
            }
        return d[mask] = ret;
    }

    private int calc(String instruction, int mask, IntValue v) {
        int cnt = 0;
        for (int j = 0; j < instruction.length(); ++j)
            if (instruction.charAt(j) == '1') {
                if (!BitsUtil.checkBit(mask, j)) {
                    ++cnt;
                    mask = BitsUtil.setBit(mask, j);
                }
            }
        v.setValue(mask);
        return GeomUtil.sqr(cnt);
    }

}

class BitsUtil {
    public static boolean checkBit(int number, int bit) {
        return (number & (1 << bit)) != 0;
    }

    public static int setBit(int number, int bit) {
        return number | (1 << bit);
    }

}

class GeomUtil {
    public static int sqr(int x) {
        return x * x;
    }

}

class IntValue {
    private int value;

    public IntValue() {
    }

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
