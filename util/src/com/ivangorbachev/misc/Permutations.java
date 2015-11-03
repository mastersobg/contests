package com.ivangorbachev.misc;

import com.ivangorbachev.util.ArraysUtil;

import static com.ivangorbachev.util.ArraysUtil.reverse;
import static com.ivangorbachev.util.ArraysUtil.swap;

/**
 * @author Ivan Gorbachev
 */
public class Permutations {

    public static int[] initArray(int n) {
        int []ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = i;
        }
        return ret;
    }

    public static boolean nextPermutation(int []arr) {
        if (arr == null || arr.length <= 1) {
            return false;
        }
        int i;
        for (i = arr.length - 2; i >= 0; --i) {
            if (arr[i] < arr[i + 1]) {
                break;
            }
        }
        if (i < 0) {
            return false;
        }
        int j;
        for (j = arr.length - 1; j >= 0; --j) {
            if (arr[j] > arr[i]) {
                break;
            }
        }
        swap(arr, i, j);
        reverse(arr, i + 1, arr.length - 1);
        return true;
    }
}
