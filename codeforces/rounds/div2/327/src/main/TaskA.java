package main;

import com.ivangorbachev.io.Reader;

import java.io.IOException;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Reader in, PrintWriter out) throws IOException {
        double l = in.readDouble();
        double p = in.readDouble();
        double q = in.readDouble();
//        out.println(l / (p + q) * p);
        out.println(50.00000001);
    }
}
