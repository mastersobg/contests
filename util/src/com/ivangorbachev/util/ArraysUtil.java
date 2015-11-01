package com.ivangorbachev.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Gorbachev
 */
public class ArraysUtil {

    public static List<Integer> asList(int []arr) {
        List<Integer> list = new ArrayList<Integer>(arr.length);
        for (int a : arr)
            list.add(a);
        return list;
    }

    public static List<Long> asList(long []arr) {
        List<Long> list = new ArrayList<Long>(arr.length);
        for (long a : arr)
            list.add(a);
        return list;
    }

    public static void fill(int [][]v, int value) {
        for (int i = 0; i < v.length; i++) {
            Arrays.fill(v[i], value);
        }
    }
}
