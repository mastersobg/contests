import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    String[][] digits = {
            {
                    "***",
                    "* *",
                    "* *",
                    "* *",
                    "***"
            },
            {
                    "  *",
                    "  *",
                    "  *",
                    "  *",
                    "  *"
            },
            {
                    "***",
                    "  *",
                    "***",
                    "*  ",
                    "***"
            },
            {
                    "***",
                    "  *",
                    "***",
                    "  *",
                    "***"
            },
            {
                    "* *",
                    "* *",
                    "***",
                    "  *",
                    "  *"
            },
            {
                    "***",
                    "*  ",
                    "***",
                    "  *",
                    "***"
            },
            {
                    "***",
                    "*  ",
                    "***",
                    "* *",
                    "***"
            },
            {
                    "***",
                    "  *",
                    "  *",
                    "  *",
                    "  *",
            },
            {
                    "***",
                    "* *",
                    "***",
                    "* *",
                    "***"
            },
            {
                    "***",
                    "* *",
                    "***",
                    "  *",
                    "***"
            }
    };

    class Input {
        String[] lines;

        int read = 0;

        String[] getNext() {
            int p = 3 * read + read;
            if (p >= lines[0].length()) return null;
            String[] ret = new String[5];
            for (int i = 0; i < 5; ++i) {
                ret[i] = lines[i].substring(p, p + 3);
            }
            ++read;
            return ret;
        }
    }

    int test(String[] digit) {
        for (int i = 0; i < digits.length; ++i) {
            if (Arrays.deepEquals(digits[i], digit)) return i;
        }
        return -1;
    }

    void solve() throws IOException {
        Input input = readFull();
        String[] d = null;
        long n = 0;
        while ((d = input.getNext()) != null) {
            int idx = test(d);
            if (idx == -1) {
                out.println("BOOM!!");
                return;
            }
            n = n * 10 + idx;
        }
        if (n % 6 == 0) {
            out.println("BEER!!");
        } else out.println("BOOM!!");
    }

    Input readFull() throws IOException {
        Input in = new Input();
        in.lines = new String[5];
        for (int i = 0; i < 5; ++i)
            in.lines[i] = this.in.readLine();
        return in;
    }

    public void run() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        in.close();
        out.close();
    }

    String ns() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(in.readLine());
        return st.nextToken();
    }

    int ni() throws IOException {
        return Integer.valueOf(ns());
    }

    long nl() throws IOException {
        return Long.valueOf(ns());
    }

    double nd() throws IOException {
        return Double.valueOf(ns());
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

    class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }

        long time() {
            return System.currentTimeMillis() - time;
        }

        void print() {
            print("Time spent = ");
        }

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = false;

    void dbg(Object... objs) {
        if (!DEBUG) {
            return;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[])
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }


}

