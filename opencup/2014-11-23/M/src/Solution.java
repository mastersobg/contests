import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;
    String filename = "";

    String []cmds;
    int pos = 0;

    void solve() throws IOException {
        int n = ni();
        cmds = new String[n];
        for (int i = 0; i < n; ++i) {
            cmds[i] = in.readLine();
        }
        go(new HashMap<String, Integer>(), new HashMap<String, Integer>());
    }

    HashMap<String, Integer> go(HashMap<String, Integer> global, HashMap<String, Integer> local) {
        while (pos < cmds.length) {
            String s = cmds[pos++];
            if ("{".equals(s)) {
                HashMap<String, Integer> next = new HashMap<String, Integer>(global);
                next.putAll(local);
                HashMap<String, Integer> q = go(next, new HashMap<String, Integer>());
                for (Map.Entry<String, Integer> e : q.entrySet()) {
                    if (local.containsKey(e.getKey()))
                        local.put(e.getKey(), e.getValue());
                    else global.put(e.getKey(), e.getValue());
                }
            } else if ("}".equals(s)) {
                return global;
            } else if (s.startsWith("int")) {
                StringTokenizer st = new StringTokenizer(s, " ");
                st.nextToken();
                local.put(st.nextToken(), 0);
            } else if (s.startsWith("print")) {
                StringTokenizer st = new StringTokenizer(s, " ");
                st.nextToken();
                String var = st.nextToken();
                if (local.containsKey(var))
                    out.println(local.get(var));
                else out.println(global.get(var));
            } else if (Character.isDigit(s.charAt(s.length() - 1))) {
                StringTokenizer st = new StringTokenizer(s, "=");
                String var = st.nextToken();
                int val = Integer.valueOf(st.nextToken());
                if (local.containsKey(var))
                    local.put(var, val);
                else global.put(var, val);
            } else {
                StringTokenizer st = new StringTokenizer(s, "=");
                String var1 = st.nextToken();
                String var2 = st.nextToken();
                int val;
                if (local.containsKey(var2))
                    val = local.get(var2);
                else val = global.get(var2);
                if (local.containsKey(var1))
                    local.put(var1, val);
                else global.put(var1, val);
            }
            // dbg(s);
            // dbg(global);
            // dbg(local);
            // dbg("=================");
        }
        return global;
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

    static boolean DEBUG = true;

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
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

