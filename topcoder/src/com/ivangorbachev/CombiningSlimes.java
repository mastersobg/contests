package com.ivangorbachev;

import com.ivangorbachev.util.ArraysUtil;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class CombiningSlimes {
    public int maxMascots(int[] a) {
        List<Integer> list = ArraysUtil.asList(a);
        int ret = 0;
        while (list.size() > 1) {
            int x = list.get(0);
            int y = list.get(1);
            list.remove((Integer)x);
            list.remove((Integer)y);
            list.add(x + y);
            ret += x * y;
        }
        return ret;
    }
}
