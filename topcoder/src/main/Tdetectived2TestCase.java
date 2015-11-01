package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Tdetectived2TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        return Arrays.asList(
                new NewTopCoderTest(
                        new Object[]
                                {new String[] {"003211023320010", "000102333022113", "300122011213031",
                                        "211031033003103", "102302020203333", "122120201323311",
                                        "030002013013032", "231320102030311", "331301320202202", "302023002013100",
                                        "221002130103000", "023333302330330", "010133032103021", "113031310003200",
                                        "031331212000100"}}, 238)
        );
    }
}
