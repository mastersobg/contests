import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Ivan Gorbachev
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        W solver = new W();
        solver.solve(1, in, out);
        out.close();
    }

    static class W {
        public void solve(int testNumber, Reader in, PrintWriter out) {
            max(1, 2);
        }

    }

    static class Reader {
        private BufferedReader in;

        public Reader(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

    }
}

