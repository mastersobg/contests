package main;

import com.ivangorbachev.io.Reader;
import com.ivangorbachev.util.BitsUtil;

import java.io.PrintWriter;

public class EmmaNotebook {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int t = in.readInt();
        int a = 1;
        int b = 2;
        long ret = 0;
        for (int i = 1; i <= t; ++i) {
            if (BitsUtil.isOdd(i)) {
                ret += a;
                ++a;
            } else {
                ret += b;
                ++b;
            }
        }
        out.println(ret);
    }
}
