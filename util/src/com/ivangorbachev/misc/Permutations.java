package com.ivangorbachev.misc;

import com.ivangorbachev.util.BitsUtil;

/**
 * @author Ivan Gorbachev
 */
public class Permutations<T> {

    private BooleanValue terminate;
    private int n;
    private T result;

    public Permutations(int n, T defaultResult) {
        this.n = n;
        this.result = defaultResult;
        terminate = new BooleanValue(false);
    }
    public T generate(Callback<T> f) {
        go(0, n, 0, new int[n], f);
        return result;
    }

    private boolean go(int idx, int n, int mask,
                              int []order, Callback<T> f) {
        if (idx == n) {
            result = f.test(order, result, terminate);
            return terminate.isValue();
        }
        for (int i = 0; i < n; i++) {
            if (!BitsUtil.checkBit(mask, i)) {
                order[idx] = i;
                if (go(idx + 1, n, BitsUtil.setBit(mask, i), order, f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public interface Callback<T> {
        T test(int []order, T ret, BooleanValue terminate);
    }
}
