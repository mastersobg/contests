import java.io.*;
import java.util.*;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;

public class CombiningSlimes {
    public int maxMascots(int[] a) {
        List<Integer> list = ArraysUtil.asList(a);
        int ret = 0;
        while (list.size() > 1) {
            int x = list.get(0);
            int y = list.get(1);
            list.remove((Integer) x);
            list.remove((Integer) y);
            list.add(x + y);
            ret += x * y;
        }
        return ret;
    }

}

class ArraysUtil {
    public static List<Integer> asList(int[] arr) {
        List<Integer> list = new ArrayList<Integer>(arr.length);
        for (int a : arr)
            list.add(a);
        return list;
    }

}
