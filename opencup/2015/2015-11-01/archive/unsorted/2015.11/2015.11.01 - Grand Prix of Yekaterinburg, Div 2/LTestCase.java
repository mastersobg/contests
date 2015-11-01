package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.Collection;
import java.util.Collections;

public class LTestCase {
    @TestCase
    public Collection<Test> createTests() {
        return Collections.emptyList();
    }
}
