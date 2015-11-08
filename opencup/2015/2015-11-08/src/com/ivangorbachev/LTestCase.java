package com.ivangorbachev;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.Collection;
import java.util.Collections;

public class LTestCase {
    @TestCase
    public Collection<Test> createTests() {
        StringBuilder input = new StringBuilder();
        input.append("100000 1 99999\n");
        input.append("1\n");
        for (int i = 0; i < 99999; i++) {
            input.append(i + 2).append(" ").append(i + 1).append("\n");
        }
        Test test = new Test(input.toString());
        return Collections.singletonList(test);
    }
}
