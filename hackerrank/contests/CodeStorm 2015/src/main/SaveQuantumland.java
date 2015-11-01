package main;

import com.ivangorbachev.io.Reader;
import java.io.PrintWriter;

public class SaveQuantumland {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.readInt();
        int []v = in.readIntArray(n);
        int ret = 0;
        for (int i = 0; i < n - 1; i++) {
            if (v[i] == 0 && v[i + 1] == 0) {
                v[i + 1] = 1;
                ++ret;
            }
        }
        out.println(ret);
    }
}
