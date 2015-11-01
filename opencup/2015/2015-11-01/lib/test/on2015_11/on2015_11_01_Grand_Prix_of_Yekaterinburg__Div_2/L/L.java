package on2015_11.on2015_11_01_Grand_Prix_of_Yekaterinburg__Div_2.L;



import com.ivangorbachev.io.Reader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class L {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> list = in.readIntList(5);
            Collections.sort(list);
            set.add(list);
        }
        out.println(set.size());
    }
}
