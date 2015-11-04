package com.ivangorbachev;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.Collection;
import java.util.Collections;

public class OrderOfOperationsDiv2TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        NewTopCoderTest test = new NewTopCoderTest(
                new Object[] {new String[] {"1000", "1101", "0001", "0100", "0101", "1011", "0000", "0011", "1011", "1111", "0100", "0010", "0000", "0010", "0010", "0000", "0100", "0101", "0001", "1100"}},
                4
        );
        return Collections.singletonList(test);
    }
}
