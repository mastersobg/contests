package main;





import com.ivangorbachev.io.Reader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class B {

    void factor(int n, Set<Integer> set) {
        for (long i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                set.add((int) i);
                set.add((int)(n / i));
            }
        }
    }

    public void solve(int testNumber, Reader in, PrintWriter out) {
        int []v = in.readIntArray();
        // hahahhahah
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < v.length; i++) {
            factor(v[i], set);
        }
        for (int i = 1;; ++i)
            if (!set.contains(i)) {
                out.println(i);
                return;
            }
    }
}
