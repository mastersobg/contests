package com.ivangorbachev;

import com.ivangorbachev.util.ArraysUtil;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.*;

public class TaskCTestCase {
    @TestCase
    public Collection<Test> createTests() {
        Random rnd = new Random(1231144);
        int n = 10;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; list.size() < 10; ++i) {
            if (rnd.nextInt(10) > 7) {
                list.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (int a : list)
            sb.append(a).append(" ");
        StringBuilder output = new StringBuilder();
        output.append(new TaskC().naive(ArraysUtil.asArray(list)));
        return Collections.singletonList(new Test(sb.toString(), output.toString()));
    }
}
