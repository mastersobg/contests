package com.ivangorbachev;

import com.ivangorbachev.util.ArraysUtil;

import java.util.Arrays;
import java.util.List;

public class SetPartialOrder {
    final String EQUAL = "EQUAL";
    final String LESS = "LESS";
    final String GREATER = "GREATER";
    final String NONE = "INCOMPARABLE";

    public String compareSets(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i])
                    return NONE;
            }
            return EQUAL;
        } else {
            boolean compare = a.length < b.length ? compare(a, b) : compare(b, a);
            return compare ? (a.length < b.length ? LESS : GREATER) : NONE;
        }
    }

    private boolean compare(int[] a, int[] b) {
        List<Integer> list = ArraysUtil.asList(b);
        for (int x : a) {
            if (!list.remove((Integer) x)) {
                return false;
            }
        }
        return true;
    }
}
