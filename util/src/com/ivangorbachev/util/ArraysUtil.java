package com.ivangorbachev.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Gorbachev
 */
public class ArraysUtil {

    public static List<Integer> asList(int[] arr) {
        List<Integer> list = new ArrayList<Integer>(arr.length);
        for (int a : arr)
            list.add(a);
        return list;
    }

    public static List<Long> asList(long[] arr) {
        List<Long> list = new ArrayList<Long>(arr.length);
        for (long a : arr)
            list.add(a);
        return list;
    }

    public static int[] asArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); ++i)
            arr[i] = list.get(i);
        return arr;
    }

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

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void reverse(int[] arr, int i, int j) {
        for (int p0 = i, p1 = j; p0 < p1; ++p0, --p1) {
            swap(arr, p0, p1);
        }
    }
}
