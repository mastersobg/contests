package com.ivangorbachev.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Gorbachev
 */
public class ArraysUtil {

//    public static List<Integer> asList(int []arr) {
//        return Arrays.stream(arr).boxed().collect(Collectors.toList());
//    }
//
//    public static List<Long> asList(long []arr) {
//        return Arrays.stream(arr).boxed().collect(Collectors.toList());
//    }

    public static void fill(int [][]v, int value) {
        for (int i = 0; i < v.length; i++) {
            Arrays.fill(v[i], value);
        }
    }
}
