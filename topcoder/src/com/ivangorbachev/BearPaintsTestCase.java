package com.ivangorbachev;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class BearPaintsTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        return Arrays.asList(
                new NewTopCoderTest(new Object[] {5, 200, 969}, 965L),
                new NewTopCoderTest(new Object[] {60123, 40444, 1718712911}, 1718712910L)
        );
    }
}
