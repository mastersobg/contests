package on2015_11.on2015_11_01_Grand_Prix_of_Yekaterinburg__Div_2.D;



import com.ivangorbachev.io.Reader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class DTestCase {
    @TestCase
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        D d = new D();
        Reader r = null;
        try {
            r = new Reader(new FileInputStream("out.precalc"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringTokenizer st = new StringTokenizer(r.readLine(), ",");
        for (int i = 1; i <= 100000; i++) {
            ret.add(new Test("" + i, st.nextToken()));
        }
        return ret;
    }
}