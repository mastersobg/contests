package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SetPartialOrderTestCase {

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        return Arrays.asList(
                createTest1()
        );
    }

    private NewTopCoderTest createTest1() {
        int []a = {1, 2, 3};
        int []b = {1, 3, 4, 5};
        return new NewTopCoderTest(new Object[] {a, b}, "INCOMPARABLE");
    }
}
