package com.ivangorbachev.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ivan Gorbachev
 */
public class ArraysUtil {

    public static List<Integer> asList(int []arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    public static List<Long> asList(long []arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
}
